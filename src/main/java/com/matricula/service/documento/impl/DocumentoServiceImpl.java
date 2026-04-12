package com.matricula.service.documento.impl;

import com.matricula.dto.documento.DocumentoRequestDTO;
import com.matricula.dto.documento.DocumentoResponseDTO;
import com.matricula.entity.DocumentoEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.mapper.DocumentoMapper;
import com.matricula.repository.DocumentoRepository;
import com.matricula.service.documento.DocumentoService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentoServiceImpl implements DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final DocumentoMapper documentoMapper;

    @Override
    public DocumentoResponseDTO register(DocumentoRequestDTO requestDTO) {

        // Validar duplicados
        if(documentoRepository.existsByNombreIgnoreCase(requestDTO.nombre())){
            throw new BadRequestException(MessageConstants.Documento.ALREADY_EXISTS);
        }

        DocumentoEntity entity = documentoMapper.toEntity(requestDTO);
        DocumentoEntity saved = documentoRepository.save(entity);
        return documentoMapper.toDTO(saved);
    }

    @Override
    public List<DocumentoResponseDTO> list() {
        List<DocumentoEntity> documentos = documentoRepository.findAll();
        return documentos.stream()
                .map(documentoMapper::toDTO)
                .toList();
    }
}
