package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Justificativo;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.JustificativoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class JustificativoServiceTest {

    @Mock
    private JustificativoRepository justificativoRepository;

    @InjectMocks
    private JustificativoService justificativoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Justificativo justificativo = new Justificativo();

        justificativoService.save(justificativo);

        verify(justificativoRepository, times(1)).save(justificativo);
    }
}