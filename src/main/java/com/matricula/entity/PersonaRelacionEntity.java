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

@Entity
@Table(name = "persona_relacion")
@Getter
@Setter
@NoArgsConstructor
public class PersonaRelacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacion")
    private Integer idRelacion;

    @ManyToOne
    @JoinColumn(name = "id_alumno")
    private PersonaEntity alumno;

    @ManyToOne
    @JoinColumn(name = "id_familiar")
    private PersonaEntity familiar;

    @ManyToOne
    @JoinColumn(name = "id_tipo_relacion")
    private TipoRelacionEntity tipoRelacion;

    @Column(name = "es_apoderado")
    private Boolean esApoderado;

    @Column(name = "es_fallecido")
    private Boolean esFallecido;
}