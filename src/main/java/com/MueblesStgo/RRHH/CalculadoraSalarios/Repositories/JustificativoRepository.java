package com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.Justificativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JustificativoRepository extends JpaRepository<Justificativo, Long> {
}
