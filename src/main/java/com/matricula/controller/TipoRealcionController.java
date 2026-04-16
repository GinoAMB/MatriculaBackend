package com.matricula.controller;

import com.matricula.dto.tipo_relacion.TipoRequestDTO;
import com.matricula.dto.tipo_relacion.TipoResponseDTO;
import com.matricula.service.tipo_relacion.TipoRelacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-relacion")
@RequiredArgsConstructor
@Tag(name = "Tipo Relación", description = "Gestión de tipos de relación")
public class TipoRealcionController {

    private final TipoRelacionService tipoRelacionService;

    @Operation(
            summary = "Registrar un nuevo tipo de relación",
            description = "Crea un nuevo tipo de relación en el sistema"
    )
    @PostMapping("/register")
    public ResponseEntity<TipoResponseDTO> register(@RequestBody @Valid TipoRequestDTO requestDTO){
        TipoResponseDTO responseDTO = tipoRelacionService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(
            summary = "Listar tipos de relación",
            description = "Obtiene todos los tipos de relación registrados"
    )
    @GetMapping
    public ResponseEntity<List<TipoResponseDTO>> list(){
        List<TipoResponseDTO> tipoResponseDTOS = tipoRelacionService.list();
        return ResponseEntity.ok(tipoResponseDTOS);
    }
}
