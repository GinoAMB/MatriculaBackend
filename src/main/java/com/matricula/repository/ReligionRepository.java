package com.matricula.repository;

import com.matricula.entity.ReligionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReligionRepository extends JpaRepository<ReligionEntity, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
}
