package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.AutorizacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AutorizacionServiceTest {

    @Mock
    private AutorizacionRepository autorizacionRepository;

    @InjectMocks
    private AutorizacionService autorizacionService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save() {
        Autorizacion newAuth = new Autorizacion();

        autorizacionService.save(newAuth);

        verify(autorizacionRepository, times(1)).save(newAuth);
    }
}