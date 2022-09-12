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
                throw new RuntimeException(e);
            }
        }
        return "El archivo de marcajes ha sido ingresado satisfactoriamente";
    }
    public void readData(){
        Empleado empleado;
        Date tiempo = null;
        String rut = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        try{
            BufferedReader reader = new BufferedReader(new FileReader("marcajes/DATA.TXT"));
            String line;
            while((line = reader.readLine()) != null) { //Mientras siga habiendo marcajes por ingresar
                //Lectura de la línea y trabajo de reconocimiento de datos
                String[] lineaDividida = line.split(";");
                String fechaStr = lineaDividida[0] + " " + lineaDividida[1];
                tiempo = formatoFecha.parse(fechaStr);
                rut = lineaDividida[2];
                //Creación del marcaje y guardado en la base
                MarcaReloj marca = new MarcaReloj();
                marca.setTiempo(tiempo);
                empleado = empleadoRepository.findByRut(rut);
                marca.setEmpleado(empleado);
                marcaRelojRepository.save(marca);
            }
            reader.close();

        }catch (IOException e){
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
