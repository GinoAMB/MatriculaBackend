package com.matricula.service.documento;

import com.matricula.dto.documento.DocumentoRequestDTO;
import com.matricula.dto.documento.DocumentoResponseDTO;
import com.matricula.dto.documento.DocumentoUpdateRequestDTO;

import java.util.List;

public interface DocumentoService {
    DocumentoResponseDTO register(DocumentoRequestDTO requestDTO);
    List<DocumentoResponseDTO> list();
    DocumentoResponseDTO update(DocumentoUpdateRequestDTO requestDTO);
}
