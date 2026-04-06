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
@Table(name = "matricula")
@Getter
@Setter
@NoArgsConstructor
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matricula")
    private Integer idMatricula;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private PersonaEntity alumno;

    @ManyToOne
    @JoinColumn(name = "id_seccion")
    private SeccionEntity seccion;

    @ManyToOne
    @JoinColumn(name = "id_periodo")
    private PeriodoEscolarEntity periodo;

    @ManyToOne
    @JoinColumn(name = "id_estado")
    private EstadoMatriculaEntity estado;

    @Column(name = "fecha_matricula")
    private LocalDate fechaMatricula;
}