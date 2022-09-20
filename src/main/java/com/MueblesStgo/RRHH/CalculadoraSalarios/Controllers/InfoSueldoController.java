package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.InfoSueldo;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.EmpleadoService;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.InfoSueldoService;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.MarcaRelojService;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.OficinaRRHH;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class InfoSueldoController {

    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    InfoSueldoService infoSueldoService;

    @Autowired
    MarcaRelojService marcaRelojService;

    @Autowired
    OficinaRRHH oficinaRRHH;

    @GetMapping("/sueldos")
    public String calcularSueldos(Model model) {
        List<Empleado> empleados = empleadoService.findAll();
        List<InfoSueldo> sueldos = new ArrayList<>();

        //Se elaborará la información a desplegar por cada empleado, y se guardará en la lista sueldos
        //Esta última lista es la que será desplegada para la página
        for (Empleado empleado : empleados){
            InfoSueldo sueldo = new InfoSueldo();
            //---Información del empleado---
            sueldo.setRut(empleado.getRut());
            sueldo.setApellidos(empleado.getApellidos());
            sueldo.setNombre(empleado.getNombre());
            sueldo.setCategoria(empleado.getCategoria().getLetra());

            //---Tiempo de servicio en años---
            LocalDate fechaIngreso = new java.sql.Date(empleado.getFecha_ing().getTime()).toLocalDate();
            LocalDate fechaActual = LocalDate.now();
            Period tiempoServicio = fechaIngreso.until(fechaActual);
            sueldo.setTiempoServicio(tiempoServicio.getYears());

            //---Sueldo fijo mensual---
            sueldo.setSueldoFijoMensual(oficinaRRHH.calcularSueldoFijoMensual(empleado));

            //---Bonificación tiempo de servicio---
            sueldo.setBonificacionTiempoServicio(oficinaRRHH.calcularBonificacionTiempoServicio(empleado));

            //---Bonificación horas extra---
            List<Autorizacion> horasExtraValidas = oficinaRRHH.autorizacionesHorasExtraValidas(empleado);
            sueldo.setBonificacionHorasExtra(oficinaRRHH.calcularMontoHorasExtra(empleado, horasExtraValidas));

            //---Descuento de atrasos e inasistencias---
            //Se determina el periodo en que se encuentran las marcas ingresadas
            MarcaReloj primeraMarca = marcaRelojService.obtenerPrimeraMarca();
            MarcaReloj ultimaMarca = marcaRelojService.obtenerUltimaMarca();
            LocalDate fechaInicio = new java.sql.Date(primeraMarca.getTiempo().getTime()).toLocalDate();
            LocalDate fechaFinal = new java.sql.Date(ultimaMarca.getTiempo().getTime()).toLocalDate();
            List<LocalDate> diasLaborales = oficinaRRHH.obtenerDiasLaborales(fechaInicio, fechaFinal);

            //Se recolectan los marcajes inexistentes dentro del periodo
            List<LocalDate> marcajesInexistentes = oficinaRRHH.obtenerMarcajesInexistentes(empleado, diasLaborales);

            //Se justifican las ausencias detectadas, quedando a las que se aplica descuento si o si
            List<LocalDate> ausenciasSinJustificar = oficinaRRHH.justificarAusencias(empleado, marcajesInexistentes);

            //Se ejecuta el método final
            sueldo.setDescuentosAtrasoInasistencias(oficinaRRHH.calcularDescuentoAtrasosInasistencias(empleado, ausenciasSinJustificar));

            //---Sueldo bruto---
            double sueldoBruto = sueldo.getSueldoFijoMensual() + sueldo.getBonificacionTiempoServicio() +
                    sueldo.getBonificacionHorasExtra() - sueldo.getDescuentosAtrasoInasistencias();

            sueldo.setSueldoBruto(sueldoBruto);

            //---Cotización previsional---
            sueldo.setCotizacionPrevisional(oficinaRRHH.calcularCotizacionPrevisional(sueldoBruto));

            //---Cotización salud---
            sueldo.setCotizacionSalud(oficinaRRHH.calcularCotizacionSalud(sueldoBruto));

            //---Sueldo final---
            double sueldoFinal = sueldoBruto - sueldo.getCotizacionPrevisional() - sueldo.getCotizacionSalud();
            sueldo.setSueldoFinal(sueldoFinal);

            //Se almacena en la lista de sueldos
            sueldos.add(sueldo);
        }

        model.addAttribute("sueldos", sueldos);
        return "panelSueldos";
    }
}
