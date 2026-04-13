package com.matricula.controller;

import com.matricula.dto.estado.EstadoResponseDTO;
import com.matricula.service.estado.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estadoMatricula")
@RequiredArgsConstructor
@Tag(name = "Estado de Matricula", description = "Gestion de Estado de Matricula")
public class EstadoController {
    private final EstadoService estadoService;

    @Operation(
            summary = "Lista de Estado de Matriculas",
            description = "Obtiene todos los estados registrados"
    )
    @GetMapping
    public ResponseEntity<List<EstadoResponseDTO>> list(){
        List<EstadoResponseDTO> estadoResponseDTOS = estadoService.list();
        return ResponseEntity.ok(estadoResponseDTOS);
    }
}
