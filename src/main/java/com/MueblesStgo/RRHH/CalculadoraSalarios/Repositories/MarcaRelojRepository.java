package com.MueblesStgo.RRHH.CalculadoraSalarios.Repositories;

import com.MueblesStgo.RRHH.CalculadoraSalarios.Entities.MarcaReloj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRelojRepository extends JpaRepository<MarcaReloj, Long> {
}
