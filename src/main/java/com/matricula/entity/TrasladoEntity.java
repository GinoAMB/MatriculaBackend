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
@Table(name = "traslado")
@Getter
@Setter
@NoArgsConstructor
public class TrasladoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_traslado")
    private Integer idTraslado;

    @ManyToOne
    @JoinColumn(name = "id_matricula")
    private MatriculaEntity matricula;

    @Column(name = "institucion_destino")
    private String institucionDestino;

    @Column(name = "fecha_traslado")
    private LocalDate fechaTraslado;
}