package com.matricula.service.religion;

import com.matricula.dto.religion.ReligionRequestDTO;
import com.matricula.dto.religion.ReligionResponseDTO;
import com.matricula.dto.religion.ReligionUpdateRequestDTO;

import java.util.List;

public interface ReligionService {
    ReligionResponseDTO register(ReligionRequestDTO requestDTO);
    List<ReligionResponseDTO> list();
    ReligionResponseDTO update(ReligionUpdateRequestDTO requestDTO);
}
