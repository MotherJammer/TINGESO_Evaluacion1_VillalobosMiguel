package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.InfoSueldo;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.InfoSueldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoSueldoService {

    @Autowired
    InfoSueldoRepository infoSueldoRepository;

    public List<InfoSueldo> findAll(){
        return infoSueldoRepository.findAll();
    }
}
