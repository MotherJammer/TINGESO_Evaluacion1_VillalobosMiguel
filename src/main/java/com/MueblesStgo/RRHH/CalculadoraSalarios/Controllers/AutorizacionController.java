package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.AutorizacionService;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class AutorizacionController {

    @Autowired
    AutorizacionService autorizacionService;
    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/uploadAuth")
    public String subirAutorizacion(@ModelAttribute Autorizacion newAuth) {
        Empleado empleadoAuth = empleadoService.findByRut(newAuth.getRut_empleado_auth());
        newAuth.setEmpleado(empleadoAuth);

        autorizacionService.save(newAuth);
        System.out.println("Autorización ingresada exitosamente");

        return "redirect:/HExtraJusti";
    }
}
