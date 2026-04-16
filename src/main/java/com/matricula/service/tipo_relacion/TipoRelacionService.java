package com.matricula.service.tipo_relacion;

import com.matricula.dto.tipo_relacion.TipoRequestDTO;
import com.matricula.dto.tipo_relacion.TipoResponseDTO;

import java.util.List;

public interface TipoRelacionService {
    TipoResponseDTO register(TipoRequestDTO requestDTO);
    List<TipoResponseDTO> list();
}
