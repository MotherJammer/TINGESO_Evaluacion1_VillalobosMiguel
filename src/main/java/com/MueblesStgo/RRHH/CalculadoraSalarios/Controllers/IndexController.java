package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping("/")
    public String home() {
        return "cargaMarcaje";
    }

    @GetMapping("/HExtraJusti")
    public String hextrajusti() { return "panelHExtraJusti"; }
}
