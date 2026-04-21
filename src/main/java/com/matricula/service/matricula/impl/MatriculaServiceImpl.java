package com.matricula.service.matricula.impl;

import com.matricula.dto.matricula.*;
import com.matricula.entity.*;
import com.matricula.exception.BadRequestException;
import com.matricula.exception.ConflictException;
import com.matricula.exception.NotFoundException;
import com.matricula.mapper.FamiliarMapper;
import com.matricula.mapper.MatriculaMapper;
import com.matricula.mapper.PersonaMapper;
import com.matricula.repository.*;
import com.matricula.service.matricula.MatriculaService;
import com.matricula.util.MessageConstants;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaMapper matriculaMapper;
    private final PersonaMapper personaMapper;
    private final FamiliarMapper familiarMapper;

    private final PersonaRepository personaRepository;
    private final MatriculaRepository matriculaRepository;
    private final PersonaRelacionRepository relacionRepository;

    private final SeccionRepository seccionRepository;
    private final PeriodoEscolarRepository periodoRepository;
    private final EstadoRepository estadoRepository;
    private final TipoRelacionRepository tipoRelacionRepository;
    private final DocumentoRepository documentoRepository;
    private final ReligionRepository religionRepository;
    private final PaisRepository paisRepository;

    @Override
    @Transactional
    public AlumnoResponseDTO registrar(MatriculaRequestDTO request) {

        validarMatricula(request);

        //Crear alumno
        PersonaEntity alumno = personaMapper.toAlumnoEntity(request.alumno());

        alumno.setTipoDocumento(
                documentoRepository.findById(request.alumno().idTipoDocumento())
                        .orElseThrow(() -> new NotFoundException(MessageConstants.Persona.DOCUMENTO_NOT_FOUND))
        );

        alumno.setReligion(
                religionRepository.findById(request.alumno().idReligion())
                        .orElseThrow(() -> new NotFoundException(MessageConstants.Persona.RELIGION_NOT_FOUND))
        );

        alumno.setPais(
                paisRepository.findById(request.alumno().idPais())
                        .orElseThrow(() -> new NotFoundException(MessageConstants.Pais.NOT_FOUND))
        );

        personaRepository.save(alumno);

        //Crear matrícula
        MatriculaEntity matricula = matriculaMapper.toEntity(request);

        matricula.setAlumno(alumno);
        matricula.setSeccion(seccionRepository.findById(request.idSeccion())
                .orElseThrow(() -> new NotFoundException(MessageConstants.Matricula.SECCION_NOT_FOUND)));
        matricula.setPeriodo(periodoRepository.findById(request.idPeriodo())
                .orElseThrow(() -> new NotFoundException(MessageConstants.Matricula.PERIODO_NOT_FOUND)));
        matricula.setEstado(estadoRepository.findById(request.idEstado())
                .orElseThrow(() -> new NotFoundException(MessageConstants.Matricula.ESTADO_NOT_FOUND)));

        matriculaRepository.save(matricula);

        //Familiares
        guardarFamiliar(request.padre(), alumno);
        guardarFamiliar(request.madre(), alumno);

        //Apoderado externo
        if (!esApoderadoPadreOMadre(request)) {
            guardarFamiliar(request.apoderadoExterno(), alumno);
        }

        //Response
        return new AlumnoResponseDTO(
                alumno.getIdPersona(),
                alumno.getNombre(),
                alumno.getApellidos(),
                matricula.getSeccion().getGrado().getNivel().getNombre(),
                matricula.getSeccion().getGrado().getNombre(),
                matricula.getSeccion().getNombre()
        );
    }

    private void validarMatricula(MatriculaRequestDTO request) {

        // Alumno duplicado
        personaRepository.findByNumeroDocumento(request.alumno().numeroDocumento())
                .ifPresent(p -> {
                    throw new ConflictException(MessageConstants.Matricula.ALUMNO_EXISTS);
                });

        // Fecha válida
        if (request.alumno().fechaNacimiento().isAfter(LocalDate.now())) {
            throw new BadRequestException(MessageConstants.Matricula.FECHA_NACIMIENTO_INVALIDA);
        }

        // Solo un apoderado
        if (request.padre() != null && request.madre() != null) {
            if (Boolean.TRUE.equals(request.padre().esApoderado()) &&
                    Boolean.TRUE.equals(request.madre().esApoderado())) {
                throw new BadRequestException(MessageConstants.Matricula.SOLO_UN_APODERADO);
            }
        }

        // Padre validaciones
        validarFamiliar(request.padre(), "padre");

        // Madre validaciones
        validarFamiliar(request.madre(), "madre");

        validarFamiliar(request.apoderadoExterno(), "apoderado externo");

        // Apoderado externo obligatorio si no hay otro
        if (!esApoderadoPadreOMadre(request) && request.apoderadoExterno() == null) {
            throw new BadRequestException(MessageConstants.Matricula.APODERADO_OBLIGATORIO);
        }

        // No permitir externo si ya hay apoderado
        if (esApoderadoPadreOMadre(request) && request.apoderadoExterno() != null) {
            throw new BadRequestException(MessageConstants.Matricula.NO_APODERADO_EXTERNO);
        }

        // Al menos un responsable
        if (
                (request.padre() == null || Boolean.TRUE.equals(request.padre().esFallecido())) &&
                        (request.madre() == null || Boolean.TRUE.equals(request.madre().esFallecido())) &&
                        request.apoderadoExterno() == null
        ) {
            throw new BadRequestException(MessageConstants.Matricula.RESPONSABLE_OBLIGATORIO);
        }
    }

    private void validarFamiliar(FamiliarRequestDTO dto, String tipo) {

        if (dto == null) return;

        // Fallecido no debe tener datos
        if (Boolean.TRUE.equals(dto.esFallecido()) && dto.datos() != null) {
            throw new BadRequestException(
                    String.format(MessageConstants.Familiar.FALLECIDO_CON_DATOS, tipo)
            );
        }

        // Si no es fallecido → datos obligatorios
        if (!Boolean.TRUE.equals(dto.esFallecido()) && dto.datos() == null) {
            throw new BadRequestException(
                    String.format(MessageConstants.Familiar.DATOS_OBLIGATORIOS, tipo)
            );
        }

        // Tipo relación obligatorio
        if (dto.idTipoRelacion() == null) {
            throw new BadRequestException(MessageConstants.Familiar.TIPO_RELACION_OBLIGATORIO);
        }
    }

    private void guardarFamiliar(FamiliarRequestDTO dto, PersonaEntity alumno) {

        if (dto == null || Boolean.TRUE.equals(dto.esFallecido())) {
            return;
        }

        PersonaEntity familiar = familiarMapper.toEntity(dto.datos());


        familiar.setTipoDocumento(
                documentoRepository.findById(dto.datos().idTipoDocumento())
                        .orElseThrow(() -> new NotFoundException(MessageConstants.Persona.DOCUMENTO_NOT_FOUND))
        );


        personaRepository.save(familiar);

        crearRelacion(alumno, familiar, dto.idTipoRelacion(), dto.esApoderado());
    }

    private void crearRelacion(PersonaEntity alumno,
                               PersonaEntity familiar,
                               Integer idTipoRelacion,
                               Boolean esApoderado) {

        PersonaRelacionEntity relacion = new PersonaRelacionEntity();

        relacion.setAlumno(alumno);
        relacion.setFamiliar(familiar);
        relacion.setTipoRelacion(
                tipoRelacionRepository.findById(idTipoRelacion)
                        .orElseThrow(() -> new NotFoundException(MessageConstants.Familiar.TIPO_RELACION_NOT_FOUND))
        );
        relacion.setEsApoderado(esApoderado);
        relacion.setEsFallecido(false);

        relacionRepository.save(relacion);
    }
    private boolean esApoderadoPadreOMadre(MatriculaRequestDTO dto) {

        return (dto.padre() != null && Boolean.TRUE.equals(dto.padre().esApoderado()))
                || (dto.madre() != null && Boolean.TRUE.equals(dto.madre().esApoderado()));
    }
}