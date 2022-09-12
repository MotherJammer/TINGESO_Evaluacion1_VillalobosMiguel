package com.MueblesStgo.RRHH.CalculadoraSalarios.Controllers;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Services.MarcaRelojService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MarcaRelojController {

    @Autowired
    private MarcaRelojService upload;

    @GetMapping("/")
    public String home() {
        return "cargaMarcaje";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("marcajeTurnos")MultipartFile file) {
        upload.save(file);
        upload.readData();
        return "redirect:/";
    }
}
