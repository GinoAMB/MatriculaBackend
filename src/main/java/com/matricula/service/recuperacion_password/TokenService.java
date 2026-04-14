package com.matricula.service.recuperacion_password;

import com.matricula.dto.recuperacion_password.CambiarPasswordDTO;
import com.matricula.dto.recuperacion_password.RecuperacionRequestDTO;
import com.matricula.dto.recuperacion_password.RecuperacionResponseDTO;
import com.matricula.dto.recuperacion_password.ValidarTokenDTO;

public interface TokenService {
    RecuperacionResponseDTO solicitarRecuperacion(RecuperacionRequestDTO request);
    RecuperacionResponseDTO validarToken(ValidarTokenDTO request);
    RecuperacionResponseDTO cambiarPassword(CambiarPasswordDTO request);
}
