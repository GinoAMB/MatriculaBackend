package com.matricula.service.academico.impl;

import com.matricula.dto.academico.GradoResponseDTO;
import com.matricula.dto.academico.NivelResponseDTO;
import com.matricula.dto.academico.SeccionRequestDTO;
import com.matricula.entity.GradoEntity;
import com.matricula.entity.SeccionEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.exception.ConflictException;
import com.matricula.exception.NotFoundException;
import com.matricula.mapper.SeccionMapper;
import com.matricula.repository.GradoRepository;
import com.matricula.repository.NivelRepository;
import com.matricula.repository.SeccionRepository;
import com.matricula.service.academico.NivelService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NivelServiceImpl implements NivelService {

    private final NivelRepository nivelRepository;
    private final GradoRepository gradoRepository;
    private final SeccionRepository seccionRepository;
    private final SeccionMapper seccionMapper;


    @Override
    public List<NivelResponseDTO> listarEstructura() {
        return nivelRepository.findAll().stream().map(nivel -> {

            var grados = gradoRepository.findByNivelIdNivel(nivel.getIdNivel())
                    .stream().map(grado -> {

                        var secciones = seccionRepository
                                .findByGradoIdGrado(grado.getIdGrado())
                                .stream()
                                .map(seccionMapper::toDTO) // ✔ uso del mapper
                                .toList();

                        return new GradoResponseDTO(
                                grado.getIdGrado(),
                                grado.getNombre(),
                                secciones
                        );

                    }).toList();

            return new NivelResponseDTO(
                    nivel.getIdNivel(),
                    nivel.getNombre(),
                    grados
            );

        }).toList();
    }

    @Override
    public void registrarSeccion(SeccionRequestDTO request) {

        Integer idNivel = request.idNivel();
        Integer idGrado = request.idGrado();
        String nombreSeccion = request.nombre().trim().toUpperCase();

        // 1. Validar nivel
        nivelRepository.findById(idNivel)
                .orElseThrow(() -> new NotFoundException(MessageConstants.Nivel.NOT_FOUND));

        // 2. Validar grado
        GradoEntity grado = gradoRepository.findById(idGrado)
                .orElseThrow(() -> new NotFoundException(MessageConstants.Grado.NOT_FOUND));

        // 3. Validar relación nivel-grado
        if (!grado.getNivel().getIdNivel().equals(idNivel)) {
            throw new BadRequestException(MessageConstants.Grado.INVALID_NIVEL);
        }

        // 4. Validar duplicado
        seccionRepository
                .findByNombreAndGradoIdGrado(nombreSeccion, idGrado)
                .ifPresent(s -> {
                    throw new ConflictException(MessageConstants.Seccion.ALREADY_EXISTS);
                });

        // 5. Crear sección
        SeccionEntity seccion = new SeccionEntity();
        seccion.setNombre(nombreSeccion);
        seccion.setGrado(grado);

        // 6. Guardar
        seccionRepository.save(seccion);
    }
}
