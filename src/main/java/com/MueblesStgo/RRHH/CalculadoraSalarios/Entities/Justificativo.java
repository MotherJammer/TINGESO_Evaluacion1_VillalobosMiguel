package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "justificativo")
public class Justificativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_justificativo;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
