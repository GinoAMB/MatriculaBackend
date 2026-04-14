package com.matricula.controller;

import com.matricula.dto.recuperacion_password.CambiarPasswordDTO;
import com.matricula.dto.recuperacion_password.RecuperacionRequestDTO;
import com.matricula.dto.recuperacion_password.RecuperacionResponseDTO;
import com.matricula.dto.recuperacion_password.ValidarTokenDTO;
import com.matricula.service.recuperacion_password.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recuperacion")
@RequiredArgsConstructor
@Tag(name = "Recuperación de Contraseña", description = "Gestión de recuperación de contraseñas")
public class TokenController {

    private final TokenService tokenService;

    @Operation(
            summary = "Solicitar recuperación de contraseña",
            description = "Envía un token al correo del usuario para recuperar su contraseña"
    )
    @PostMapping("/solicitar")
    public ResponseEntity<RecuperacionResponseDTO> solicitarRecuperacion(
            @RequestBody @Valid RecuperacionRequestDTO request) {

        return ResponseEntity.ok(tokenService.solicitarRecuperacion(request));
    }

    @Operation(
            summary = "Validar token",
            description = "Valida si el token es correcto y no ha expirado"
    )
    @PostMapping("/validar-token")
    public ResponseEntity<RecuperacionResponseDTO> validarToken(
            @RequestBody @Valid ValidarTokenDTO request) {

        return ResponseEntity.ok(tokenService.validarToken(request));
    }

    @Operation(
            summary = "Cambiar contraseña",
            description = "Permite cambiar la contraseña usando un token válido"
    )
    @PostMapping("/cambiar-password")
    public ResponseEntity<RecuperacionResponseDTO> cambiarPassword(
            @RequestBody @Valid CambiarPasswordDTO request) {

        return ResponseEntity.ok(tokenService.cambiarPassword(request));
    }
}