package com.matricula.repository;

import com.matricula.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    boolean existsByCorreo(String correo);
    Optional<UsuarioEntity> findByCorreo(String correo);
}
