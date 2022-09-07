package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "marcas_reloj")
public class MarcaReloj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_marca;

    private Date tiempo;

    @ManyToOne
    @JoinColumn(name="id_empleado")
    private Empleado empleado;
}
