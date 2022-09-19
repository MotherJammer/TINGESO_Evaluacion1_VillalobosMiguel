package com.MueblesStgo.RRHH.CalculadoraSalarios;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Categoria;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.OficinaRRHH;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OficinaRRHHTest {

	OficinaRRHH oficinaRRHH = new OficinaRRHH();
    Empleado empleado = new Empleado();
    Categoria categoria = new Categoria();

    @Test
    void calcularSueldoFijoMensualCategoriaA() {
        categoria.setLetra('A');
        empleado.setCategoria(categoria);

        double sueldoFijo = oficinaRRHH.calcularSueldoFijoMensual(empleado);
        assertEquals(1700000, sueldoFijo);
    }

    @Test
    void calcularSueldoFijoMensualCategoriaB() {
        categoria.setLetra('B');
        empleado.setCategoria(categoria);

        double sueldoFijo = oficinaRRHH.calcularSueldoFijoMensual(empleado);
        assertEquals(1200000, sueldoFijo);
    }

    @Test
    void calcularSueldoFijoMensualCategoriaC() {
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double sueldoFijo = oficinaRRHH.calcularSueldoFijoMensual(empleado);
        assertEquals(800000, sueldoFijo);
    }

    @Test
    void calcularBonificacionTiempoServicioPeriodo25() throws ParseException {
        String fecha_en_string = "1997-09-10";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formato.parse(fecha_en_string);
        empleado.setFecha_ing(fechaIngreso);
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double bonificacionTiempoServicio = oficinaRRHH.calcularBonificacionTiempoServicio(empleado);
        assertEquals(136000, bonificacionTiempoServicio);
    }

    @Test
    void calcularBonificacionTiempoServicioPeriodo20() throws ParseException {
        String fecha_en_string = "2002-09-10";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formato.parse(fecha_en_string);
        empleado.setFecha_ing(fechaIngreso);
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double bonificacionTiempoServicio = oficinaRRHH.calcularBonificacionTiempoServicio(empleado);
        assertEquals(112000, bonificacionTiempoServicio);
    }

    @Test
    void calcularBonificacionTiempoServicioPeriodo15() throws ParseException {
        String fecha_en_string = "2007-09-10";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formato.parse(fecha_en_string);
        empleado.setFecha_ing(fechaIngreso);
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double bonificacionTiempoServicio = oficinaRRHH.calcularBonificacionTiempoServicio(empleado);
        assertEquals(88000, bonificacionTiempoServicio);
    }

    @Test
    void calcularBonificacionTiempoServicioPeriodo10() throws ParseException {
        String fecha_en_string = "2012-09-10";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formato.parse(fecha_en_string);
        empleado.setFecha_ing(fechaIngreso);
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double bonificacionTiempoServicio = oficinaRRHH.calcularBonificacionTiempoServicio(empleado);
        assertEquals(64000, bonificacionTiempoServicio);
    }

    @Test
    void calcularBonificacionTiempoServicioPeriodo5() throws ParseException {
        String fecha_en_string = "2017-09-10";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formato.parse(fecha_en_string);
        empleado.setFecha_ing(fechaIngreso);
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double bonificacionTiempoServicio = oficinaRRHH.calcularBonificacionTiempoServicio(empleado);
        assertEquals(40000, bonificacionTiempoServicio);
    }

    @Test
    void calcularBonificacionTiempoServicioNoAplica() throws ParseException {
        String fecha_en_string = "2020-09-10";
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaIngreso = formato.parse(fecha_en_string);
        empleado.setFecha_ing(fechaIngreso);
        categoria.setLetra('C');
        empleado.setCategoria(categoria);

        double bonificacionTiempoServicio = oficinaRRHH.calcularBonificacionTiempoServicio(empleado);
        assertEquals(0, bonificacionTiempoServicio);
    }

    @Test
    void calcularMontoHorasExtra() {
        //Este test hace prueba de los dos métodos involucrados:
        //1) La validación de las autorizaciones respecto a los marcajes ingresados
        //2) El cálculo del monto de las horas extra, con las autorizaciones comprobadas

        //Setup marcajes empleado
        List<MarcaReloj> marcajes = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        //Marcaje normal, deberia ser descartado
        MarcaReloj marcaReloj1 = new MarcaReloj();
        cal.set(2022, 9, 1, 18,0);
        Date marca = cal.getTime();
        marcaReloj1.setTiempo(marca);
        marcajes.add(marcaReloj1);
        //Marcaje con la hora extra autorizada, en la fecha correcta. Debe considerarse
        MarcaReloj marcaReloj2 = new MarcaReloj();
        cal.set(2022,9,2,19,0);
        marca = cal.getTime();
        marcaReloj2.setTiempo(marca);
        marcajes.add(marcaReloj2);
        //Marcaje con la hora extra autorizada, pero marcó demasiado tarde asi que se descarta
        MarcaReloj marcaReloj3 = new MarcaReloj();
        cal.set(2022,9,3,20,0);
        marca = cal.getTime();
        marcaReloj3.setTiempo(marca);
        marcajes.add(marcaReloj3);
        //Marcaje con una hora extra sin autorizar, en la fecha incorrecta. Deberia ser descartado
        MarcaReloj marcaReloj4 = new MarcaReloj();
        cal.set(2022,9,4,19,0);
        marca = cal.getTime();
        marcaReloj4.setTiempo(marca);
        marcajes.add(marcaReloj4);

        //Setup autorizaciones
        List<Autorizacion> autorizaciones = new ArrayList<>();
        //Autorización validada con marcaje correcto
        Autorizacion autorizacion1 = new Autorizacion();
        cal.set(2022,9,2);
        Date diaAutorizacion = cal.getTime();
        autorizacion1.setFecha_auth(diaAutorizacion);
        autorizacion1.setHoras_aprobadas(1);
        autorizaciones.add(autorizacion1);
        //Autorización no validada por marcaje demasiado tarde
        Autorizacion autorizacion2 = new Autorizacion();
        cal.set(2022,9,3);
        diaAutorizacion = cal.getTime();
        autorizacion2.setFecha_auth(diaAutorizacion);
        autorizacion2.setHoras_aprobadas(1);
        autorizaciones.add(autorizacion2);

        //Setup empleado
        categoria.setLetra('C');
        empleado.setCategoria(categoria);
        empleado.setMarcas_reloj(marcajes);
        empleado.setAutorizaciones(autorizaciones);

        //Setup respuesta esperada
        List<Autorizacion> autorizacionesCorrectas = new ArrayList<>();
        autorizacionesCorrectas.add(autorizacion1);

        List<Autorizacion> autorizacionesValidadas = oficinaRRHH.autorizacionesHorasExtraValidas(empleado);
        double montoHorasExtra = oficinaRRHH.calcularMontoHorasExtra(empleado, autorizacionesValidadas);

        assertEquals(autorizacionesCorrectas,autorizacionesValidadas);
        assertEquals(10000, montoHorasExtra);
    }

    @Test
    void obtenerDiasLaborales(){
        LocalDate inicio = LocalDate.of(2022,9,5);
        LocalDate fin = LocalDate.of(2022,9,11);

        List<LocalDate> diasLaborales = inicio.datesUntil(LocalDate.of(2022,9,10))
                .collect(Collectors.toList());

        List<LocalDate> diasObtenidos = oficinaRRHH.obtenerDiasLaborales(inicio, fin);

        System.out.println(diasLaborales);
        System.out.println(diasObtenidos);

        assertEquals(diasLaborales,diasObtenidos);
    }

}
