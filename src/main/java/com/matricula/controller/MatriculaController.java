package com.matricula.controller;

import com.matricula.dto.common.PageResponseDTO;
import com.matricula.dto.matricula.AlumnoDetalleResponseDTO;
import com.matricula.dto.matricula.AlumnoPrintDTO;
import com.matricula.dto.matricula.AlumnoResponseDTO;
import com.matricula.dto.matricula.MatriculaRequestDTO;
import com.matricula.service.matricula.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@RequiredArgsConstructor
@Tag(name = "Matrículas", description = "Gestión de matrículas")
public class MatriculaController {

    private final MatriculaService matriculaService;

    @Operation(summary = "Registrar matrícula de alumno")
    @PreAuthorize("hasAnyRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> registrar(
            @Valid @RequestBody MatriculaRequestDTO request
    ) {

        AlumnoResponseDTO response = matriculaService.registrar(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(
            summary = "Lista de matrículas",
            description = "Obtiene la lista paginada de matrículas."
    )
    @PreAuthorize("hasAnyRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/lista")
    public ResponseEntity<PageResponseDTO<AlumnoResponseDTO>> list(

            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,

            @RequestParam(required = false) String search,
            @RequestParam(required = false) String nivel,
            @RequestParam(required = false) String grado,
            @RequestParam(required = false) String seccion
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(
                matriculaService.list(
                        search,
                        nivel,
                        grado,
                        seccion,
                        pageable
                )
        );
    }

    @Operation(
            summary = "Detalle de matrícula",
            description = "Obtiene el detalle completo de una matrícula por ID."
    )
    @PreAuthorize("hasAnyRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/{id}")
    public ResponseEntity<AlumnoDetalleResponseDTO> findById(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                matriculaService.findById(id)
        );
    }

    @Operation(
            summary = "Listado para impresión de alumnos",
            description = "Obtiene alumnos filtrados por nivel, grado y sección para impresión."
    )
    @PreAuthorize("hasAnyRole('DIRECTIVO', 'APOYO')")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/print")
    public ResponseEntity<List<AlumnoPrintDTO>> printList(

            @RequestParam(required = false) String nivel,
            @RequestParam(required = false) String grado,
            @RequestParam(required = false) String seccion
    ) {

        return ResponseEntity.ok(
                matriculaService.listarParaImpresion(nivel, grado, seccion)
        );
    }
}