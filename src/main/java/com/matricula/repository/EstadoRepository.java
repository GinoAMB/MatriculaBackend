package com.matricula.repository;

import com.matricula.entity.EstadoMatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<EstadoMatriculaEntity, Integer> {
    boolean existsByNombreIgnoreCase (String nombre);
}
