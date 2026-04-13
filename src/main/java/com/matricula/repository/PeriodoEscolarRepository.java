package com.matricula.repository;

import com.matricula.entity.PeriodoEscolarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodoEscolarRepository extends JpaRepository<PeriodoEscolarEntity, Integer> {
    boolean existsByAnio(Integer anio);
}
