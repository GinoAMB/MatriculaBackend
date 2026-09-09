package com.matricula.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
@NoArgsConstructor
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "numero_documento")
    private String numeroDocumento;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "viene_de_otra_institucion")
    private Boolean vieneDeOtraInstitucion;

    @Column(name = "nombre_institucion_procedencia")
    private String nombreInstitucionProcedencia;

    @Column(name = "tiene_discapacidad")
    private Boolean tieneDiscapacidad;

    @Column(name = "descripcion_discapacidad")
    private String descripcionDiscapacidad;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "celular")
    private String celular;

    @ManyToOne
    @JoinColumn(name = "id_tipo")
    private DocumentoEntity tipoDocumento;

    @ManyToOne
    @JoinColumn(name = "id_religion")
    private ReligionEntity religion;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private PaisEntity pais;

    @OneToMany(mappedBy = "alumno")
    private List<PersonaRelacionEntity> relaciones;
}