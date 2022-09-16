package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_auth;

    private String rut_empleado_auth;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
