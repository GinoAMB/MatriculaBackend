package com.matricula.repository;

import com.matricula.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Integer> {
    List<MatriculaEntity> findByAlumno_IdPersona(Integer idAlumno);
}
