package com.matricula.controller;

import com.matricula.dto.pais.PaisRequestDTO;
import com.matricula.dto.pais.PaisResponseDTO;
import com.matricula.service.pais.PaisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pais")
@RequiredArgsConstructor
@Tag(name = "País", description = "Gestión de países")
public class PaisController {

    private final PaisService paisService;

    @Operation(
            summary = "Registrar un nuevo país",
            description = "Crea un nuevo país en el sistema"
    )
    @PostMapping("/register")
    public ResponseEntity<PaisResponseDTO> register(@RequestBody @Valid PaisRequestDTO requestDTO){
        PaisResponseDTO responseDTO = paisService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Lista de países",
            description = "Obtiene todos los países registrados"
    )
    @GetMapping
    public ResponseEntity<List<PaisResponseDTO>> list(){
        List<PaisResponseDTO> paisResponseDTOS = paisService.list();
        return ResponseEntity.ok(paisResponseDTOS);
    }
}