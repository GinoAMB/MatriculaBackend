package com.matricula.service.usuario;

import com.matricula.dto.usuario.AuthResponse;
import com.matricula.dto.usuario.LoginRequestDTO;
import com.matricula.dto.usuario.RegisterRequestDTO;
import com.matricula.dto.usuario.UsuarioResponseDTO;

public interface UsuarioService {

    UsuarioResponseDTO register(RegisterRequestDTO request);

    AuthResponse login(LoginRequestDTO request);

}
