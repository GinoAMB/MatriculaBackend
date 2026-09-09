package com.matricula.mapper;

import com.matricula.dto.matricula.*;
import com.matricula.entity.MatriculaEntity;
import com.matricula.entity.PersonaRelacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatriculaMapper {

    @Mapping(target = "idMatricula", ignore = true)
    @Mapping(target = "alumno", ignore = true)
    @Mapping(target = "seccion", ignore = true)
    @Mapping(target = "periodo", ignore = true)
    @Mapping(target = "estado", ignore = true)
    MatriculaEntity toEntity(MatriculaRequestDTO dto);


    @Mapping(source = "idMatricula", target = "idMatricula")
    @Mapping(source = "alumno.idPersona", target = "idAlumno")
    @Mapping(source = "alumno.nombre", target = "nombre")
    @Mapping(source = "alumno.apellidos", target = "apellidos")
    @Mapping(source = "alumno.tipoDocumento.nombre", target = "tipoDocumento")
    @Mapping(source = "alumno.numeroDocumento", target = "numeroDocumento")
    @Mapping(source = "seccion.grado.nivel.nombre", target = "nivel")
    @Mapping(source = "seccion.grado.nombre", target = "grado")
    @Mapping(source = "seccion.nombre", target = "seccion")
    AlumnoResponseDTO toAlumnoResponseDTO(MatriculaEntity entity);

    @Mapping(target = "idMatricula", source = "idMatricula")
    @Mapping(target = "fechaMatricula", source = "fechaMatricula")
    @Mapping(target = "periodo", source = "periodo.anio")
    @Mapping(target = "estado", source = "estado.nombre")

    @Mapping(target = "idAlumno", source = "alumno.idPersona")
    @Mapping(target = "nombre", source = "alumno.nombre")
    @Mapping(target = "apellidos", source = "alumno.apellidos")

    @Mapping(target = "idTipoDocumento", source = "alumno.tipoDocumento.idTipo")
    @Mapping(target = "tipoDocumento", source = "alumno.tipoDocumento.nombre")
    @Mapping(target = "numeroDocumento", source = "alumno.numeroDocumento")

    @Mapping(target = "nivel", source = "seccion.grado.nivel.nombre")
    @Mapping(target = "grado", source = "seccion.grado.nombre")
    @Mapping(target = "seccion", source = "seccion.nombre")

    @Mapping(target = "direccion", source = "alumno.direccion")

    @Mapping(target = "vieneDeOtraInstitucion", source = "alumno.vieneDeOtraInstitucion")
    @Mapping(target = "nombreInstitucionProcedencia", source = "alumno.nombreInstitucionProcedencia")

    @Mapping(target = "tieneDiscapacidad", source = "alumno.tieneDiscapacidad")
    @Mapping(target = "descripcionDiscapacidad", source = "alumno.descripcionDiscapacidad")

    @Mapping(target = "idReligion", source = "alumno.religion.idReligion")
    @Mapping(target = "religion", source = "alumno.religion.nombre")

    @Mapping(target = "fechaNacimiento", source = "alumno.fechaNacimiento")

    @Mapping(target = "idPais", source = "alumno.pais.idPais")
    @Mapping(target = "pais", source = "alumno.pais.nombre")

    @Mapping(target = "familiares", source = "alumno.relaciones")
    AlumnoDetalleResponseDTO toDetalleResponseDTO(MatriculaEntity entity);

    @Mapping(target = "idRelacion", source = "idRelacion")
    @Mapping(target = "idFamiliar", source = "familiar.idPersona")

    @Mapping(target = "nombre", source = "familiar.nombre")
    @Mapping(target = "apellidos", source = "familiar.apellidos")

    @Mapping(target = "idTipoDocumento", source = "familiar.tipoDocumento.idTipo")
    @Mapping(target = "tipoDocumento", source = "familiar.tipoDocumento.nombre")
    @Mapping(target = "numeroDocumento", source = "familiar.numeroDocumento")

    @Mapping(target = "direccion", source = "familiar.direccion")
    @Mapping(target = "celular", source = "familiar.celular")

    @Mapping(target = "idTipoRelacion", source = "tipoRelacion.idTipoRelacion")
    @Mapping(target = "tipoRelacion", source = "tipoRelacion.nombre")

    @Mapping(target = "esApoderado", source = "esApoderado")
    @Mapping(target = "esFallecido", source = "esFallecido")
    FamiliarDetalleResponseDTO toFamiliarDetalleDTO(PersonaRelacionEntity entity);

    @Mapping(target = "nombre", source = "alumno.nombre")
    @Mapping(target = "apellidos", source = "alumno.apellidos")

    @Mapping(target = "tipoDocumento", source = "alumno.tipoDocumento.nombre")
    @Mapping(target = "numeroDocumento", source = "alumno.numeroDocumento")

    @Mapping(target = "nivel", source = "seccion.grado.nivel.nombre")
    @Mapping(target = "grado", source = "seccion.grado.nombre")
    @Mapping(target = "seccion", source = "seccion.nombre")

    @Mapping(target = "direccion", source = "alumno.direccion")

    @Mapping(target = "vieneDeOtraInstitucion", source = "alumno.vieneDeOtraInstitucion")
    @Mapping(target = "nombreInstitucionProcedencia", source = "alumno.nombreInstitucionProcedencia")

    @Mapping(target = "tieneDiscapacidad", source = "alumno.tieneDiscapacidad")
    @Mapping(target = "descripcionDiscapacidad", source = "alumno.descripcionDiscapacidad")

    @Mapping(target = "religion", source = "alumno.religion.nombre")

    @Mapping(target = "fechaNacimiento", source = "alumno.fechaNacimiento")

    @Mapping(target = "pais", source = "alumno.pais.nombre")
    AlumnoPrintDTO toAlumnoPrintDTO(MatriculaEntity entity);
}
