package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class MarcaRelojService {
    private String folder="marcajes//";
    private final Logger logg = LoggerFactory.getLogger(MarcaRelojService.class);

    public String save(MultipartFile dataFile) {
        if (!dataFile.isEmpty()) {
            try {
                byte [] bytes = dataFile.getBytes();
                Path path = Paths.get(folder+dataFile.getOriginalFilename());
                Files.write(path, bytes);

                logg.info("Archivo guardado");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return "El archivo de marcajes ha sido ingresado satisfactoriamente";
    }
}
