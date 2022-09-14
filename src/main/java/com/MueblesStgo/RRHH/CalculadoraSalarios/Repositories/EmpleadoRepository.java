package com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    public Empleado findByRut(String rut);

    //Problema:La query no encuentra la columna id de empleado
    @Query(value = "SELECT e.nombre, e.apellidos, e.rut, mr.tiempo FROM empleado e INNER JOIN marcas_reloj mr on e.id = mr.id_empleado", nativeQuery = true)
    List<Empleado> findAllEmpleadosWithMarcas();
}
