package com.matricula.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estado_matricula")
@Getter
@Setter
@NoArgsConstructor
public class EstadoMatriculaEntity {

    @Id
    @Column(name = "id_estado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstado;

    @Column(name = "nombre")
    private String nombre;
}
