package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Justificativo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String hextrajusti(Model model) {
        model.addAttribute("autorizacion", new Autorizacion());
        model.addAttribute("justificativo", new Justificativo());
        return "panelHExtraJusti";
    }
}
