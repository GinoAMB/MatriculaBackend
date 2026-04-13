package com.matricula.repository;

import com.matricula.entity.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisRepository extends JpaRepository<PaisEntity, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
}
