package com.matricula.dto.matricula;

import java.time.LocalDate;

public record AlumnoRequestDTO(
        String nombre,
        String apellidos,
        Integer idTipoDocumento,
        String numeroDocumento,
        String direccion,
        Boolean vieneDeOtraInstitucion,
        String nombreInstitucionProcedencia,
        Boolean tieneDiscapacidad,
        String descripcionDiscapacidad,
        LocalDate fechaNacimiento,
        Integer idReligion,
        Integer idPais
) {}
