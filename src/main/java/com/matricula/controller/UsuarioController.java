package com.matricula.controller;

import com.matricula.dto.usuario.AuthResponse;
import com.matricula.dto.usuario.LoginRequestDTO;
import com.matricula.dto.usuario.RegisterRequestDTO;
import com.matricula.dto.usuario.UsuarioResponseDTO;
import com.matricula.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Gestion de usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // REGISTER
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Crea un nuevo usuario en el sistema"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> register(@RequestBody RegisterRequestDTO request) {

        UsuarioResponseDTO response = usuarioService.register(request);

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Lista de usuarios",
            description = "Obtiene todos los usuarios registrados"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioResponseDTO>> list(){
        List<UsuarioResponseDTO> usuarios = usuarioService.list();
        return ResponseEntity.ok(usuarios);
    }

    // LOGIN (devuelve JWT + datos)
    @Operation(
            summary = "Autenticación de usuario",
            description = "Permite a un usuario iniciar sesión mediante su correo y contraseña. " +
                    "Si las credenciales son válidas, retorna un token JWT junto con la información del usuario."
    )
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO request) {

        AuthResponse response = usuarioService.login(request);

        return ResponseEntity.ok(response);
    }
}