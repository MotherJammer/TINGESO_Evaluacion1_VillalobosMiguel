package com.MueblesStgo.RRHH.CalculadoraSalarios.Entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "justificativo")
public class Justificativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_justificativo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha_justi;

    private String motivo;
    private String rut_empleado_justi;

    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Empleado empleado;
}
