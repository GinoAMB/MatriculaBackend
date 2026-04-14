package com.matricula.service.recuperacion_password.impl;

import com.matricula.dto.recuperacion_password.CambiarPasswordDTO;
import com.matricula.dto.recuperacion_password.RecuperacionRequestDTO;
import com.matricula.dto.recuperacion_password.RecuperacionResponseDTO;
import com.matricula.dto.recuperacion_password.ValidarTokenDTO;
import com.matricula.entity.RecuperacionContrasenaEntity;
import com.matricula.entity.UsuarioEntity;
import com.matricula.repository.TokenRepository;
import com.matricula.repository.UsuarioRepository;
import com.matricula.service.email.EmailService;
import com.matricula.service.recuperacion_password.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    @Override
    public RecuperacionResponseDTO solicitarRecuperacion(RecuperacionRequestDTO request) {

        UsuarioEntity usuario = usuarioRepository.findByCorreoAndEstadoTrue(request.correo())
                .orElseThrow(() -> new RuntimeException("El correo no está registrado o el usuario está inactivo"));


        String tokenValue = UUID.randomUUID().toString();

        RecuperacionContrasenaEntity token = new RecuperacionContrasenaEntity();
        token.setUsuario(usuario);
        token.setToken(tokenValue);
        token.setFechaCreacion(LocalDateTime.now());
        token.setExpiracion(LocalDateTime.now().plusMinutes(30));
        token.setUsado(false);

        tokenRepository.save(token);

        String nombre = (usuario.getPersona() != null)
                ? usuario.getPersona().getNombre()
                : "Usuario";

        String asunto = "Recuperación de contraseña";

        String cuerpo = """
        <div style="font-family:Arial; padding:20px;">
            <h2>Recuperación de contraseña</h2>
            <p>Hola <b>%s</b>,</p>
            <p>Usa el siguiente token para cambiar tu contraseña:</p>
            <h3 style="background:#eee; padding:10px;">%s</h3>
            <p>Este token expira en 30 minutos.</p>
        </div>
        """.formatted(nombre, tokenValue);

        emailService.enviarCorreo(usuario.getCorreo(), asunto, cuerpo);

        return new RecuperacionResponseDTO("Correo enviado correctamente", true);
    }

    @Override
    public RecuperacionResponseDTO validarToken(ValidarTokenDTO request) {

        RecuperacionContrasenaEntity token = tokenRepository
                .findByTokenAndUsadoFalse(request.token())
                .orElse(null);

        if (token == null) {
            return new RecuperacionResponseDTO("Token inválido", false);
        }

        if (token.getExpiracion().isBefore(LocalDateTime.now())) {
            return new RecuperacionResponseDTO("Token expirado", false);
        }

        return new RecuperacionResponseDTO("Token válido", true);
    }

    @Override
    public RecuperacionResponseDTO cambiarPassword(CambiarPasswordDTO request) {

        RecuperacionContrasenaEntity token = tokenRepository
                .findByTokenAndUsadoFalse(request.token())
                .orElse(null);

        if (token == null) {
            return new RecuperacionResponseDTO("Token inválido", false);
        }

        if (token.getExpiracion().isBefore(LocalDateTime.now())) {
            return new RecuperacionResponseDTO("Token expirado", false);
        }

        UsuarioEntity usuario = token.getUsuario();

        usuario.setPassword(passwordEncoder.encode(request.nuevoPassword()));
        usuarioRepository.save(usuario);

        token.setToken(null);
        token.setUsado(true);
        tokenRepository.save(token);

        return new RecuperacionResponseDTO("Contraseña actualizada correctamente", true);
    }
}