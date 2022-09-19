package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Justificativo;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.JustificativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JustificativoService {

    @Autowired
    JustificativoRepository justificativoRepository;

    public void save (Justificativo justificativo){
        justificativoRepository.save(justificativo);
    }
}
