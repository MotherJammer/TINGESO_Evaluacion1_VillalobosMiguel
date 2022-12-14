package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_cat;

    private char letra;
    private int sueldo_fijo_mensual;

    @OneToMany(mappedBy = "categoria")
    private List<Empleado> empleados;


}
