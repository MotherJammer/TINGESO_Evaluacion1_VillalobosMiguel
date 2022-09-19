package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.MarcaRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping
public class MarcaRelojController {

    @Autowired
    private MarcaRelojService marcaRelojService;

    @PostMapping("/upload")
    public String upload(@RequestParam("marcajeTurnos")MultipartFile file) {
        marcaRelojService.save(file);
        marcaRelojService.readData();
        return "redirect:/HExtraJusti";
    }
}
