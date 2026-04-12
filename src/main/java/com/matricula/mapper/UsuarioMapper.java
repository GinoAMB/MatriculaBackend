package com.matricula.mapper;

import com.matricula.dto.usuario.RegisterRequestDTO;
import com.matricula.dto.usuario.UsuarioResponseDTO;
import com.matricula.entity.UsuarioEntity;
import com.matricula.entity.PersonaEntity;
import com.matricula.entity.RolEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // 🔹 Convertir RegisterRequestDTO → UsuarioEntity
    @Mappings({
            @Mapping(target = "idUsuario", ignore = true),
            // El correo viene directo del DTO
            @Mapping(target = "correo", source = "correo"),

            // El password también
            @Mapping(target = "password", source = "password"),

            // El estado lo puedes setear por defecto (activo)
            @Mapping(target = "estado", constant = "true"),

            // persona se arma manualmente (no viene como objeto en el DTO)
            @Mapping(target = "persona", source = "."),

            // rol también se arma manualmente usando idRol
            @Mapping(target = "rol", source = "idRol")
    })
    UsuarioEntity toEntity(RegisterRequestDTO dto);


    // 🔹 Mapear DTO → PersonaEntity (usado arriba automáticamente)
    @Mappings({
            @Mapping(target = "nombre", source = "nombre"),
            @Mapping(target = "apellidos", source = "apellidos"),

            // Campos que no vienen en el DTO → ignorar
            @Mapping(target = "idPersona", ignore = true),
            @Mapping(target = "numeroDocumento", ignore = true),
            @Mapping(target = "direccion", ignore = true),
            @Mapping(target = "vieneDeOtraInstitucion", ignore = true),
            @Mapping(target = "nombreInstitucionProcedencia", ignore = true),
            @Mapping(target = "tieneDiscapacidad", ignore = true),
            @Mapping(target = "descripcionDiscapacidad", ignore = true),
            @Mapping(target = "fechaNacimiento", ignore = true),
            @Mapping(target = "tipoDocumento", ignore = true),
            @Mapping(target = "religion", ignore = true),
            @Mapping(target = "pais", ignore = true)
    })
    PersonaEntity toPersona(RegisterRequestDTO dto);


    // 🔹 Convertir idRol → RolEntity
    default RolEntity map(Integer idRol) {
        if (idRol == null) return null;

        RolEntity rol = new RolEntity();
        rol.setIdRol(idRol); // solo seteas el ID (JPA luego lo resuelve)
        return rol;
    }


    // 🔹 Convertir UsuarioEntity → UsuarioResponseDTO
    @Mappings({
            @Mapping(target = "idUsuario", source = "idUsuario"),

            //acceder a datos dentro de persona
            @Mapping(target = "nombres", source = "persona.nombre"),
            @Mapping(target = "apellidos", source = "persona.apellidos"),

            @Mapping(target = "correo", source = "correo"),

            //obtener nombre del rol
            @Mapping(target = "rol", source = "rol.nombre")
    })
    UsuarioResponseDTO toResponseDTO(UsuarioEntity entity);
}