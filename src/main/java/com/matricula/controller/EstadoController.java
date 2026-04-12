package com.matricula.controller;

import com.matricula.dto.estado.EstadoRequestDTO;
import com.matricula.dto.estado.EstadoResponseDTO;
import com.matricula.service.estado.EstadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
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
            summary = "Registrar un nuevo Estado de Matricula",
            description = "Crea un nuevo estado en el sistema"
    )
    @PostMapping("/register")
    public ResponseEntity<EstadoResponseDTO> register(@RequestBody @Valid EstadoRequestDTO requestDTO){
        EstadoResponseDTO responseDTO = estadoService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

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
