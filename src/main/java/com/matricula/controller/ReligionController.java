package com.matricula.controller;

import com.matricula.dto.religion.ReligionRequestDTO;
import com.matricula.dto.religion.ReligionResponseDTO;
import com.matricula.dto.religion.ReligionUpdateRequestDTO;
import com.matricula.service.religion.ReligionService;
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
@RequestMapping("/api/religion")
@RequiredArgsConstructor
@Tag(name = "Religión", description = "Gestión de religiones")
public class ReligionController {

    private final ReligionService religionService;

    @Operation(
            summary = "Registrar una religión",
            description = "Crea una nueva religión en el sistema"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/register")
    public ResponseEntity<ReligionResponseDTO> register(@RequestBody @Valid ReligionRequestDTO requestDTO){
        ReligionResponseDTO responseDTO = religionService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Lista de religiones",
            description = "Obtiene todas las religiones registradas"
    )
    @PreAuthorize("hasAnyRole('ADMIN', 'DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<List<ReligionResponseDTO>> list(){
        List<ReligionResponseDTO> religiones = religionService.list();
        return ResponseEntity.ok(religiones);
    }

    @Operation(
            summary = "Actualizar religión",
            description = "Actualiza la información de una religión existente"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "bearerAuth")
    @PutMapping("/update")
    public ResponseEntity<ReligionResponseDTO> update(@RequestBody @Valid ReligionUpdateRequestDTO requestDTO){
        ReligionResponseDTO responseDTO = religionService.update(requestDTO);
        return ResponseEntity.ok(responseDTO);
    }
}