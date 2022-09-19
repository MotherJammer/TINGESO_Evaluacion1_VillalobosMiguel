package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Justificativo;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.EmpleadoService;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.JustificativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class JustificativoController {

    @Autowired
    JustificativoService justificativoService;

    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/uploadJustificativo")
    public String subirJustificativo(@ModelAttribute Justificativo newJusti){
        Empleado empleadoJusti = empleadoService.findByRut(newJusti.getRut_empleado_justi());
        newJusti.setEmpleado(empleadoJusti);

        justificativoService.save(newJusti);
        System.out.println("Justificativo ingresado exitosamente");

        return "redirect:/HExtraJusti";
    }
}
