package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.MarcaRelojRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarcaRelojServiceTest {

    @Mock
    private MarcaRelojRepository marcaRelojRepository;

    @InjectMocks
    private MarcaRelojService marcaRelojService;

    private MarcaReloj marcaReloj1;
    private MarcaReloj marcaReloj2;
    private MarcaReloj marcaReloj3;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        marcaReloj1 = new MarcaReloj();
        Long idMarca1 = 1L;
        marcaReloj1.setId_marca(idMarca1);
        marcaReloj2 = new MarcaReloj();
        Long idMarca2 = 2L;
        marcaReloj2.setId_marca(idMarca2);
        marcaReloj3 = new MarcaReloj();
        Long idMarca3 = 3L;
        marcaReloj3.setId_marca(idMarca3);
    }

    @Test
    void borrarMarcas() {
        marcaRelojService.borrarMarcas();

        verify(marcaRelojRepository, times(1)).deleteAll();
    }

    @Test
    void obtenerPrimeraMarca() {
        when(marcaRelojRepository.findAll()).thenReturn(Arrays.asList(marcaReloj1,marcaReloj2,marcaReloj3));
        assertEquals(marcaReloj1.getId_marca(), marcaRelojService.obtenerPrimeraMarca().getId_marca());
    }

    @Test
    void obtenerUltimaMarca() {
        when(marcaRelojRepository.findAll()).thenReturn(Arrays.asList(marcaReloj1,marcaReloj2,marcaReloj3));
        assertEquals(marcaReloj3.getId_marca(), marcaRelojService.obtenerUltimaMarca().getId_marca());
    }
}