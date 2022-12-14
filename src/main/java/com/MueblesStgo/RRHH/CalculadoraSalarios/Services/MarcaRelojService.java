package com.MueblesStgo.RRHH.CalculadoraSalarios.Services;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.EmpleadoRepository;
import com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories.MarcaRelojRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@Service
public class MarcaRelojService {
    @Autowired
    MarcaRelojRepository marcaRelojRepository;
    @Autowired
    EmpleadoRepository empleadoRepository;
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
                LOGGER.errorv("***** ERROR DE SUBIDA DE ARCHIVO *****", (Object) new String[]{e.getMessage()});
            }
        }
        return "El archivo de marcajes ha sido ingresado satisfactoriamente";
    }

    public void borrarMarcas(){
        marcaRelojRepository.deleteAll();
    }
    public void readData() {
        Empleado empleado;
        Date tiempo = null;
        String rut = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        try (BufferedReader reader = new BufferedReader(new FileReader("marcajes/DATA.TXT"))) {
            String line;
            while ((line = reader.readLine()) != null) { //Mientras siga habiendo marcajes por ingresar
                //Lectura de la l??nea y trabajo de reconocimiento de datos
                String[] lineaDividida = line.split(";");
                String fechaStr = lineaDividida[0] + " " + lineaDividida[1];
                tiempo = formatoFecha.parse(fechaStr);
                rut = lineaDividida[2];
                //Creaci??n del marcaje y guardado en la base
                MarcaReloj marca = new MarcaReloj();
                marca.setTiempo(tiempo);
                empleado = empleadoRepository.findByRut(rut);
                marca.setEmpleado(empleado);
                marcaRelojRepository.save(marca);
            }
        } catch (IOException e) {
            LOGGER.errorv("***** ERROR DE APERTURA DE ARCHIVO *****", (Object) new String[]{e.getMessage()});
        } catch (ParseException e) {
            LOGGER.errorv("***** ERROR DE FORMATO DE FECHA EN MARCAJES *****", (Object) new String[]{e.getMessage()});
        }
    }

    public MarcaReloj obtenerPrimeraMarca() {
        List<MarcaReloj> marcasIngresadas = marcaRelojRepository.findAll();
        return marcasIngresadas.get(0);
    }

    public MarcaReloj obtenerUltimaMarca() {
        List<MarcaReloj> marcasIngresadas = marcaRelojRepository.findAll();
        return marcasIngresadas.get(marcasIngresadas.size() - 1);
    }
}
