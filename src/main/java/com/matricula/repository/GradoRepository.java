package com.matricula.repository;

import com.matricula.entity.GradoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradoRepository extends JpaRepository<GradoEntity, Integer> {

    List<GradoEntity> findByNivelIdNivel(Integer idNivel);
}