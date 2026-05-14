package com.matricula.repository;

import com.matricula.entity.UsuarioEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>, JpaSpecificationExecutor<UsuarioEntity> {

    boolean existsByCorreo(String correo);
    Optional<UsuarioEntity> findByCorreo(String correo);
    Optional<UsuarioEntity> findByCorreoAndEstadoTrue(String correo);
}
