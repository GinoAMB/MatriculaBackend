package com.matricula.repository;

import com.matricula.entity.DocumentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Integer> {

    boolean existsByNombreIgnoreCase(String nombre);
}
