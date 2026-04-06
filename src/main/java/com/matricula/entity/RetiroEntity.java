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

import java.time.LocalDate;

@Entity
@Table(name = "retiro")
@Getter
@Setter
@NoArgsConstructor
public class RetiroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_retiro")
    private Integer idRetiro;

    @ManyToOne
    @JoinColumn(name = "id_matricula")
    private MatriculaEntity matricula;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "fecha_retiro")
    private LocalDate fechaRetiro;
}