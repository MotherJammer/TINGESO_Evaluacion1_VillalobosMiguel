package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.EmpleadoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        empleado = new Empleado();
        empleado.setRut("20.676.834-7");
    }

    @Test
    void findAll() {
        when(empleadoRepository.findAll()).thenReturn(Arrays.asList(empleado));
        assertNotNull(empleadoService.findAll());
    }

    @Test
    void findByRut() {
        when(empleadoRepository.findByRut("20.676.834-7")).thenReturn(empleado);
        assertNotNull(empleadoService.findByRut("20.676.834-7"));
    }
}