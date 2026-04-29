package com.matricula.controller;

import com.matricula.dto.academico.NivelResponseDTO;
import com.matricula.dto.academico.SeccionRequestDTO;
import com.matricula.service.academico.NivelService;
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
@RequestMapping("/api/academico")
@RequiredArgsConstructor
@Tag(name = "Académico", description = "Gestión de niveles, grados y secciones")
public class AcademicoController {

    private final NivelService nivelService;

    @Operation(
            summary = "Listar estructura académica",
            description = "Obtiene niveles con sus grados y secciones"
    )
    @PreAuthorize("hasRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/niveles")
    public ResponseEntity<List<NivelResponseDTO>> listarEstructura() {
        List<NivelResponseDTO> response = nivelService.listarEstructura();
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Registrar una nueva sección",
            description = "Crea una nueva sección asociada a un grado y nivel"
    )
    @PreAuthorize("hasRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/secciones")
    public ResponseEntity<Void> registrarSeccion(
            @RequestBody @Valid SeccionRequestDTO request) {

        nivelService.registrarSeccion(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}