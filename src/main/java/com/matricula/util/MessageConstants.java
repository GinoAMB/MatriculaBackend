package com.matricula.util;

public class MessageConstants {

    public static class Usuario {
        public static final String EMAIL_EXISTS = "El correo ya existe";
        public static final String INVALID_CREDENTIALS = "Credenciales inválidas";
        public static final String INACTIVE = "El usuario está inactivo";
        public static final String NOT_FOUND_OR_INACTIVE = "El correo no está registrado o el usuario está inactivo";
        public static final String NOT_FOUND = "Usuario no encontrado";
    }

    public static class Rol {
        public static final String NOT_FOUND = "Rol no encontrado";
        public static final String ALREADY_EXISTS = "El rol ya existe";
    }

    public static class Documento {
        public static final String ALREADY_EXISTS = "El documento ya existe";
        public static final String NOT_FOUND = "Documento no encontrado";
    }

    public static class Pais {
        public static final String ALREADY_EXISTS = "El Pais ya existe";
        public static final String NOT_FOUND = "País no encontrado";
    }

    public static class Religion {
        public static final String ALREADY_EXISTS = "La Religion ya existe";
        public static final String NOT_FOUND = "Religion no encontrado";
    }

    public static class PeriodoEscolar {
        public static final String ALREADY_EXISTS = "El periodo escolar ya existe";
    }

    public static class Token {
        public static final String INVALID = "Token inválido";
        public static final String EXPIRED = "Token expirado";
    }

    public static class Nivel {
        public static final String NOT_FOUND = "Nivel no encontrado";
    }

    public static class Grado {
        public static final String NOT_FOUND = "Grado no encontrado";
        public static final String INVALID_NIVEL = "El grado no pertenece al nivel especificado";
    }

    public static class Seccion {
        public static final String ALREADY_EXISTS = "La sección ya existe en ese grado";
    }

    public static class TipoRelacion {
        public static final String ALREADY_EXISTS = "El tipo de ralción ya existe";
    }

    public static class Matricula {
        public static final String ALUMNO_EXISTS = "El alumno ya existe";
        public static final String FECHA_NACIMIENTO_INVALIDA = "Fecha de nacimiento inválida";
        public static final String SOLO_UN_APODERADO = "Solo uno puede ser apoderado";
        public static final String APODERADO_OBLIGATORIO = "Debe registrar un apoderado";
        public static final String NO_APODERADO_EXTERNO = "No debe registrar apoderado externo";
        public static final String RESPONSABLE_OBLIGATORIO = "Debe existir al menos un responsable";

        public static final String SECCION_NOT_FOUND = "Sección no encontrada";
        public static final String PERIODO_NOT_FOUND = "Periodo no encontrado";
        public static final String ESTADO_NOT_FOUND = "Estado no encontrado";
    }

    public static class Persona {
        public static final String DOCUMENTO_NOT_FOUND = "Tipo documento no existe";
        public static final String RELIGION_NOT_FOUND = "Religión no existe";
    }

    public static class Familiar {
        public static final String FALLECIDO_CON_DATOS = "El %s fallecido no debe tener datos";
        public static final String DATOS_OBLIGATORIOS = "Debe ingresar datos del %s";
        public static final String TIPO_RELACION_OBLIGATORIO = "Tipo de relación es obligatorio";
        public static final String TIPO_RELACION_NOT_FOUND = "Tipo relación no existe";
    }
}
