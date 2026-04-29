package com.matricula.controller;

import com.matricula.dto.documento.DocumentoRequestDTO;
import com.matricula.dto.documento.DocumentoResponseDTO;
import com.matricula.service.documento.DocumentoService;
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
@RequestMapping("/api/documento")
@RequiredArgsConstructor
@Tag(name = "Tipo de Documento", description = "Gestion de Tipos de Documentos")
public class DocumentoController {

    private final DocumentoService documentoService;

    @Operation(
            summary = "Registrar un nuevo tipo de documento",
            description = "Crea un nuevo tipo de documento en el sistema"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<DocumentoResponseDTO> register(@RequestBody @Valid DocumentoRequestDTO requestDTO){
        DocumentoResponseDTO responseDTO = documentoService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Lista de tipo de documento",
            description = "Obtiene todos los tipos de documento registrados"
    )
    @PreAuthorize("hasRole('ADMIN', 'DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<DocumentoResponseDTO>> list(){
        List<DocumentoResponseDTO> responseDTOS = documentoService.list();
        return ResponseEntity.ok(responseDTOS);
    }
}
