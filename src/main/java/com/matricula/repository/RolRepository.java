package com.matricula.repository;

import com.matricula.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<RolEntity, Integer> {

    boolean existsByNombreIgnoreCase(String nombre);
}
