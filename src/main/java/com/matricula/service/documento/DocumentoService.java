package com.matricula.service.documento;

import com.matricula.dto.documento.DocumentoRequestDTO;
import com.matricula.dto.documento.DocumentoResponseDTO;

import java.util.List;

public interface DocumentoService {
    DocumentoResponseDTO register(DocumentoRequestDTO requestDTO);
    List<DocumentoResponseDTO> list();
}
