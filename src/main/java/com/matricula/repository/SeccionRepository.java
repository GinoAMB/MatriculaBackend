package com.matricula.repository;

import com.matricula.entity.SeccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeccionRepository extends JpaRepository<SeccionEntity, Integer> {
    List<SeccionEntity> findByGradoIdGrado(Integer idGrado);

    Optional<SeccionEntity> findByNombreAndGradoIdGrado(String nombre, Integer idGrado);
}
