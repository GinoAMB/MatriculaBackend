package com.matricula.controller;

import com.matricula.dto.periodo_escolar.PeriodoRequestDTO;
import com.matricula.dto.periodo_escolar.PeriodoResponseDTO;
import com.matricula.service.periodo_escolar.PeriodoService;
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
@RequestMapping("/api/periodo-escolar")
@RequiredArgsConstructor
@Tag(name = "Periodo Escolar", description = "Gestión de periodos escolares")
public class PeriodoEscolarController {

    private final PeriodoService periodoService;

    @Operation(
            summary = "Registrar un nuevo periodo escolar",
            description = "Crea un nuevo periodo escolar en el sistema"
    )
    @PreAuthorize("hasRole('DIRECTIVO')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<PeriodoResponseDTO> register(@RequestBody @Valid PeriodoRequestDTO requestDTO){
        PeriodoResponseDTO periodoResponseDTO = periodoService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(periodoResponseDTO);
    }

    @Operation(
            summary = "Lista de periodos escolares",
            description = "Obtiene todos los periodos escolares registrados"
    )
    @PreAuthorize("hasRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<PeriodoResponseDTO>> list(){
        List<PeriodoResponseDTO> periodoResponseDTOS = periodoService.list();
        return ResponseEntity.ok(periodoResponseDTOS);
    }
}