package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.InfoSueldo;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.InfoSueldoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InfoSueldoServiceTest {

    @Mock
    private InfoSueldoRepository infoSueldoRepository;

    @InjectMocks
    private InfoSueldoService infoSueldoService;

    private InfoSueldo infoSueldo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        infoSueldo = new InfoSueldo();
    }

    @Test
    void findAll() {
        when(infoSueldoRepository.findAll()).thenReturn(Arrays.asList(infoSueldo));
        assertNotNull(infoSueldoService.findAll());
    }
}