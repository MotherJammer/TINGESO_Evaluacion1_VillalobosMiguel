package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;


    private String rut;
    private String apellidos;
    private String nombre;
    private Date fecha_nac;
    private Date fecha_ing;

    //Averiguar bien lo de las relaciones
    /*
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cat")
    private Categoria categoria;
     */
}
