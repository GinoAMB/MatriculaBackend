package com.matricula.dto.matricula;

import java.time.LocalDate;

public record AlumnoPrintDTO(
        // Alumno
        String nombre,
        String apellidos,

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

        String religion,

        LocalDate fechaNacimiento,

        String pais
) {}
