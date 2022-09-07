package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "autorizacion_horas_extras")
public class Autorizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_auth;

    private int horas_aprobadas;
    private Date fecha_auth;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
