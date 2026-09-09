package com.matricula.dto.matricula;

import java.time.LocalDate;
import java.util.List;

public record AlumnoDetalleResponseDTO(
    // Matrícula
    Integer idMatricula,
    LocalDate fechaMatricula,
    String periodo,
    String estado,

    // Alumno
    Integer idAlumno,
    String nombre,
    String apellidos,

    Integer idTipoDocumento,
    String tipoDocumento,
    String numeroDocumento,

    String nivel,
    String grado,
    String seccion,

    String direccion,

    Boolean vieneDeOtraInstitucion,
    String nombreInstitucionProcedencia,

    Boolean tieneDiscapacidad,
    String descripcionDiscapacidad,

    Integer idReligion,
    String religion,

    LocalDate fechaNacimiento,

    Integer idPais,
    String pais,

    // Familiares
    List<FamiliarDetalleResponseDTO> familiares
) {}
