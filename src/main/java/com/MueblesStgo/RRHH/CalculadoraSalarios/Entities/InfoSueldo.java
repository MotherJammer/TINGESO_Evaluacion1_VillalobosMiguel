package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "sueldos_resultados")
public class InfoSueldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rut;
    private String apellidos;
    private String nombre;
    private char categoria;
    private int tiempoServicio; //En años
    private double sueldoFijoMensual;
    private double bonificacionTiempoServicio;
    private double bonificacionHorasExtra;
    private double descuentosAtrasoInasistencias;
    private double sueldoBruto;
    private double cotizacionPrevisional;
    private double cotizacionSalud;
    private double sueldoFinal;
}
