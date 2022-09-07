package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name="categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "empleado")
    private List<MarcaReloj> marcas_reloj;

    @OneToMany(mappedBy = "empleado")
    private List<Justificativo> justificativos;

    @OneToMany(mappedBy = "empleado")
    private List<Autorizacion> autorizaciones;
}
