package com.matricula.controller;

import com.matricula.dto.common.PageResponseDTO;
import com.matricula.dto.usuario.*;
import com.matricula.service.usuario.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;


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
    public ResponseEntity<PageResponseDTO<UsuarioResponseDTO>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String rol,
            @RequestParam(required = false) Boolean estado
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                usuarioService.list(search, rol, estado, pageable)
        );
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

    @Operation(
            summary = "Cambiar estado de usuario",
            description = "Permite activar o desactivar un usuario del sistema."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/{id}/status")
    public ResponseEntity<UsuarioResponseDTO> changeStatus(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                usuarioService.changeStatus(id)
        );
    }

    @Operation(
            summary = "Actualizar usuario",
            description = "Permite actualizar la información de un usuario del sistema."
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUsuarioRequestDTO request
    ) {

        return ResponseEntity.ok(
                usuarioService.update(id, request)
        );
    }
}