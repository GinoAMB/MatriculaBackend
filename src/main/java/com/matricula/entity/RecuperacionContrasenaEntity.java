package com.matricula.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "recuperacion_contrasena")
@Getter
@Setter
@NoArgsConstructor
public class RecuperacionContrasenaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recuperacion")
    private Integer idRecuperacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @Column(name = "token")
    private String token;

    @Column(name = "expiracion")
    private LocalDateTime expiracion;

    @Column(name = "usado")
    private Boolean usado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
}