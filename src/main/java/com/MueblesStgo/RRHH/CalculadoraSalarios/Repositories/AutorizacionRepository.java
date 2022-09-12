package com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Autorizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacionRepository extends JpaRepository<Autorizacion, Long> {
}
