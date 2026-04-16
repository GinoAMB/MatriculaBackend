package com.matricula.util;

public class MessageConstants {

    public static class Usuario {
        public static final String EMAIL_EXISTS = "El correo ya existe";
        public static final String INVALID_CREDENTIALS = "Credenciales inválidas";
        public static final String INACTIVE = "El usuario está inactivo";
        public static final String NOT_FOUND_OR_INACTIVE = "El correo no está registrado o el usuario está inactivo";
    }

    public static class Rol {
        public static final String NOT_FOUND = "Rol no encontrado";
        public static final String ALREADY_EXISTS = "El rol ya existe";
    }

    public static class Documento {
        public static final String ALREADY_EXISTS = "El documento ya existe";
    }

    public static class Pais {
        public static final String ALREADY_EXISTS = "El Pais ya existe";
    }

    public static class Religion {
        public static final String ALREADY_EXISTS = "La Religion ya existe";
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
}
