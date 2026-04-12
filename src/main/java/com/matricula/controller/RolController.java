package com.matricula.controller;

import com.matricula.dto.rol.RolRequestDTO;
import com.matricula.dto.rol.RolResponseDTO;
import com.matricula.service.rol.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PostMapping("/register")
    public ResponseEntity<RolResponseDTO> register(@RequestBody @Valid RolRequestDTO requestDTO){
        RolResponseDTO response = rolService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Listar roles",
            description = "Obtiene todos los roles registrados"
    )
    @GetMapping
    public ResponseEntity<List<RolResponseDTO>> list(){
        List<RolResponseDTO> response = rolService.list();
        return ResponseEntity.ok(response);
    }
}
