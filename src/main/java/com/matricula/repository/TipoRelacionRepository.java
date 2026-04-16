package com.matricula.repository;

import com.matricula.entity.TipoRelacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRelacionRepository extends JpaRepository<TipoRelacionEntity, Integer> {
    boolean existsByNombreIgnoreCase(String nombre);
}
