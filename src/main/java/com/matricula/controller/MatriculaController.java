package com.matricula.controller;

import com.matricula.dto.matricula.AlumnoResponseDTO;
import com.matricula.dto.matricula.MatriculaRequestDTO;
import com.matricula.service.matricula.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
@Tag(name = "Matrículas", description = "Gestión de matrículas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @Operation(summary = "Registrar matrícula de alumno")
    @PreAuthorize("hasRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> registrar(
            @Valid @RequestBody MatriculaRequestDTO request
    ) {

        AlumnoResponseDTO response = matriculaService.registrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}