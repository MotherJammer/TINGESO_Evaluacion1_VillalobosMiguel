package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class OficinaRRHH {
    //Fórmula general: Sueldo bruto = Sueldo fijo mensual + Bonificación años de servicio + Monto horas extra - ...
    //... Descuento atrasos e inasistencias

    //Calcula el sueldo fijo mensual del empleado
    //Definido por la categoría a la que pertenecen
    //Categoria "A": 1.700.000 pesos
    //Categoria "B": 1.200.000 pesos
    //Categoria "C": 800.000 pesos
    public double calcularSueldoFijoMensual(Empleado empleado){
        double sueldoMensual = 0;
        
        if(empleado.getCategoria().getLetra() == 'A'){
            sueldoMensual = 1700000;
        } else if (empleado.getCategoria().getLetra() == 'B') {
            sueldoMensual = 1200000;
        } else { //La categoría del empleado es 'C'
            sueldoMensual = 800000;
        }

        return sueldoMensual;
    }

    //Calcula bonificación según los años de servicio del empleado
    //Aplica para todas las categorías de empleado, y es en referencia al sueldo fijo mensual
    //< 5 años = 0% / >= 5 años = 5% / >= 10 años = 8% / >= 15 años = 11% / >= 20 años = 14% / >= 25 años = 17%
    public double calcularBonificacionTiempoServicio(Empleado empleado){
        double bonificacionTiempoServicio = 0;
        double sueldoFijoMensual = calcularSueldoFijoMensual(empleado);

        //Se transforman los datos Date a LocalDate para el análisis de periodos de tiempo
        LocalDate fechaIngreso = new java.sql.Date(empleado.getFecha_ing().getTime()).toLocalDate();

        LocalDate fechaActual = LocalDate.now();
        Period tiempoServicio = fechaIngreso.until(fechaActual);
        int tiempoDeServicio = tiempoServicio.getYears();

        if (tiempoDeServicio >= 25){
            bonificacionTiempoServicio = sueldoFijoMensual * 0.17;
        } else if (tiempoDeServicio >= 20) {
            bonificacionTiempoServicio = sueldoFijoMensual * 0.14;
        } else if (tiempoDeServicio >= 15) {
            bonificacionTiempoServicio = sueldoFijoMensual * 0.11;
        } else if (tiempoDeServicio >= 10) {
            bonificacionTiempoServicio = sueldoFijoMensual * 0.08;
        } else if (tiempoDeServicio >= 5) {
            bonificacionTiempoServicio = sueldoFijoMensual * 0.05;
        }

        //Truncado a precisión de 3 decimales
        double precision = Math.pow(10, 3);
        bonificacionTiempoServicio = Math.round(bonificacionTiempoServicio * precision) / precision;

        return bonificacionTiempoServicio;
    }

    //Calcula el monto de las horas extra
    //Se pagan por hora extra autorizada y se considera la categoría del empleado para su valor
    //Categoria "A": 25.000 pesos por hora
    //Categoria "B": 20.000 pesos por hora
    //Categoria "C": 10.000 pesos por hora

    //Método auxiliar para validar las autorizaciones con los marcajes
    public List<Autorizacion> autorizacionesHorasExtraValidas(Empleado empleado){
        List<MarcaReloj> marcajes = empleado.getMarcas_reloj();
        MarcaReloj marca = new MarcaReloj();
        LocalDate fechaMarcaje;
        int horaMarcaje = 0;
        int cantidadHorasExtra = 0;
        Calendar calendar = Calendar.getInstance(); //Auxiliar para obtener la hora de la marca
        List<Autorizacion> autorizaciones = empleado.getAutorizaciones();
        Autorizacion autorizacion = new Autorizacion();
        LocalDate fechaAutorizacion;
        int indexAutorizacion = 0;
        int horaSalida = 18; //Hora de salida del trabajo
        List<Autorizacion> autorizacionesValidas = new ArrayList<>();

        for (MarcaReloj marcaje : marcajes) {
            autorizacion = autorizaciones.get(indexAutorizacion);
            fechaAutorizacion = new java.sql.Date(autorizacion.getFecha_auth().getTime()).toLocalDate();
            marca = marcaje;
            fechaMarcaje = new java.sql.Date(marca.getTiempo().getTime()).toLocalDate();
            //Se verifica que la fecha del marcaje coincida con la de la autorización
            if (fechaMarcaje.equals(fechaAutorizacion)) {
                calendar.setTime(marca.getTiempo());
                horaMarcaje = calendar.get(Calendar.HOUR_OF_DAY);
                cantidadHorasExtra = horaMarcaje - horaSalida;
                //Se verifica que la hora del marcaje sea mayor que la hora de salida del trabajo y
                //que la cantidad de horas extra detectados del marcaje sea igual a la cantidad de horas aprobadas
                //DE NO CUMPLIRSE, SE ANULA LA AUTORIZACIÓN POR IRRESPONSABILIDAD DEL EMPLEADO
                if (horaMarcaje >= horaSalida && cantidadHorasExtra == autorizacion.getHoras_aprobadas()) {
                    autorizacionesValidas.add(autorizacion); //Se considera como marcaje válido y autorizado
                    indexAutorizacion++;
                }
            }
        }

        return autorizacionesValidas;
    }
    public double calcularMontoHorasExtra(Empleado empleado, List<Autorizacion> autorizacionesValidadas){
        double montoHorasExtra = 0;
        char categoria = empleado.getCategoria().getLetra();
        int cantidadHorasAutorizadas = 0;

        for (Autorizacion autorizacionValidada : autorizacionesValidadas) {
            cantidadHorasAutorizadas = autorizacionValidada.getHoras_aprobadas();
            if (categoria == 'A') {
                montoHorasExtra = montoHorasExtra + (cantidadHorasAutorizadas * 25000);
            } else if (categoria == 'B') {
                montoHorasExtra = montoHorasExtra + (cantidadHorasAutorizadas * 20000);
            } else if (categoria == 'C') {
                montoHorasExtra = montoHorasExtra + (cantidadHorasAutorizadas * 10000);
            } else {
                montoHorasExtra = montoHorasExtra + 0;
            }
        }

        return montoHorasExtra;
    }

    //TODO: Calcula el descuento por los atrasos e inasistencias
    //Aplica de acuerdo a la cantidad de minutos de atraso en el marcaje, y es en referencia al sueldo fijo mensual
    //> 10 min = 1% / > 25 min = 3% / > 45 min = 6% / > 70 min = Se considera inasistencia
    //Para las inasistencias, si no presenta justificativo aplica un descuento del 15% del salario fijo mensual
    //por cada día que no vino a trabajar

    //Método auxiliar para obtener la lista de días laborales dentro de un periodo dado
    public List<LocalDate> obtenerDiasLaborales(LocalDate inicio, LocalDate fin){
        Predicate<LocalDate> esFinDeSemana = dia -> dia.getDayOfWeek() == DayOfWeek.SATURDAY
                || dia.getDayOfWeek() == DayOfWeek.SUNDAY;

        List<LocalDate> diasLaborales = inicio.datesUntil(fin.plusDays(1)).filter(esFinDeSemana.negate())
                .collect(Collectors.toList());

        return diasLaborales;
    }

    //Método auxiliar para detectar las inasistencias en el marcaje (es decir, no existe el día en la marca)
    //La fecha que se entregue corresponde al inicio del periodo mensual para el sueldo
    public List<MarcaReloj> obtenerMarcajesInexistentes(Empleado empleado, LocalDate inicio){
        List<MarcaReloj> marcajes = empleado.getMarcas_reloj();
        List<MarcaReloj> marcajesAusentes = new ArrayList<>();

        //TODO: DESARROLLAR MÉTODO

        return marcajesAusentes;
    }

    //TODO: Calcula el descuento por cotización previsional
    //Aplica en referencia al sueldo bruto, y es de un 10%

    //TODO: Calcula el descuento por cotización del plan de salud
    //Aplica en referencia al sueldo bruto, y es de un 8%

}
