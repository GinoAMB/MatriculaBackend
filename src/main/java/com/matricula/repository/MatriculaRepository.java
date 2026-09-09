package com.matricula.repository;

import com.matricula.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Integer>, JpaSpecificationExecutor<MatriculaEntity> {
    List<MatriculaEntity> findByAlumno_IdPersona(Integer idAlumno);
}
