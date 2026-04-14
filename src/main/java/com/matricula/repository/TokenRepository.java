package com.matricula.repository;

import com.matricula.entity.RecuperacionContrasenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<RecuperacionContrasenaEntity, Integer> {

    Optional<RecuperacionContrasenaEntity> findByToken(String token);

    Optional<RecuperacionContrasenaEntity> findByTokenAndUsadoFalse(String token);

    @Modifying
    @Query("""
    UPDATE RecuperacionContrasenaEntity t
    SET t.token = null
    WHERE t.usado = false
    AND t.token IS NOT NULL
    AND t.expiracion < :now
    """)
    int limpiarTokensExpirados(@Param("now") LocalDateTime now);
}
