package com.matricula.service.usuario;

import com.matricula.dto.common.PageResponseDTO;
import com.matricula.dto.usuario.*;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    UsuarioResponseDTO register(RegisterRequestDTO request);

    AuthResponse login(LoginRequestDTO request);

    PageResponseDTO<UsuarioResponseDTO> list(
            String search,
            String rol,
            Boolean estado,
            Pageable pageable
    );

    UsuarioResponseDTO changeStatus(Integer id);

    UsuarioResponseDTO update(Integer id, UpdateUsuarioRequestDTO usuarioRequestDTO);
}
