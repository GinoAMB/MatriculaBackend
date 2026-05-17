package com.matricula.controller;

import com.matricula.dto.rol.RolRequestDTO;
import com.matricula.dto.rol.RolResponseDTO;
import com.matricula.dto.rol.RolUpdateRequestDTO;
import com.matricula.service.rol.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
@RequiredArgsConstructor
@Tag(name = "Rol", description = "Gestión de roles")
public class RolController {

    private final RolService rolService;

    @Operation(
            summary = "Registrar un nuevo rol",
            description = "Crea un nuevo rol en el sistema"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<RolResponseDTO> register(@RequestBody @Valid RolRequestDTO requestDTO){
        RolResponseDTO response = rolService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Listar roles",
            description = "Obtiene todos los roles registrados"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> list(){
        List<RolResponseDTO> response = rolService.list();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Actualizar rol",
            description = "Actualiza la información de un rol existente"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update")
    public ResponseEntity<RolResponseDTO> update(
            @RequestBody @Valid RolUpdateRequestDTO requestDTO) {

        RolResponseDTO response = rolService.update(requestDTO);

        return ResponseEntity.ok(response);
    }
}
