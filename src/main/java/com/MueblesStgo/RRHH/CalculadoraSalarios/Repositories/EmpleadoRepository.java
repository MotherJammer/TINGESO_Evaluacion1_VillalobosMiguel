package com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    public Empleado findByRut(String rut);
}
