package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat")
    private long id_cat;

    private char letra;
    private int sueldo_fijo_mensual;

    //Averiguar bien lo de las relaciones
    /*
    @OneToMany(mappedBy = "categoria")
    private Empleado empleado;
     */
}
