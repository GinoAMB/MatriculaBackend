package com.matricula.service.recuperacion_password.impl;

import com.matricula.dto.recuperacion_password.CambiarPasswordDTO;
import com.matricula.dto.recuperacion_password.RecuperacionRequestDTO;
import com.matricula.dto.recuperacion_password.RecuperacionResponseDTO;
import com.matricula.dto.recuperacion_password.ValidarTokenDTO;
import com.matricula.entity.RecuperacionContrasenaEntity;
import com.matricula.entity.UsuarioEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.exception.NotFoundException;
import com.matricula.repository.TokenRepository;
import com.matricula.repository.UsuarioRepository;
import com.matricula.service.email.EmailService;
import com.matricula.service.recuperacion_password.TokenService;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${cors.allowed-origins}")
    private String frontendUrl;

    @Transactional
    @Override
    public RecuperacionResponseDTO solicitarRecuperacion(RecuperacionRequestDTO request) {

        UsuarioEntity usuario = usuarioRepository.findByCorreoAndEstadoTrue(request.correo())
                .orElseThrow(() -> new NotFoundException(MessageConstants.Usuario.NOT_FOUND_OR_INACTIVE));


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


        String url = frontendUrl + "/reset-password?token=" + tokenValue;

        String cuerpo = """
        <div style="font-family:Arial; padding:20px;">
            <h2>Recuperación de contraseña</h2>
            <p>Hola <b>%s</b>,</p>
            <p>Haz clic en el siguiente enlace para cambiar tu contraseña:</p>
        
            <a href="%s" 
               style="display:inline-block; padding:10px 20px; background:#4CAF50; color:white; text-decoration:none; border-radius:5px;">
               Cambiar contraseña
            </a>
        
            <p>Este enlace expira en 30 minutos.</p>
        </div>
        """.formatted(nombre, url);

        emailService.enviarCorreo(usuario.getCorreo(), asunto, cuerpo);

        return new RecuperacionResponseDTO("Correo enviado correctamente", true);
    }

    @Override
    public RecuperacionResponseDTO validarToken(ValidarTokenDTO request) {

        validarTokenInterno(request.token());

        return new RecuperacionResponseDTO("Token válido", true);
    }

    @Override
    public RecuperacionResponseDTO cambiarPassword(CambiarPasswordDTO request) {

        RecuperacionContrasenaEntity token = validarTokenInterno(request.token());

        UsuarioEntity usuario = token.getUsuario();

        usuario.setPassword(passwordEncoder.encode(request.nuevoPassword()));
        usuarioRepository.save(usuario);

        token.setToken(null);
        token.setUsado(true);
        tokenRepository.save(token);

        return new RecuperacionResponseDTO("Contraseña actualizada correctamente", true);
    }

    private RecuperacionContrasenaEntity validarTokenInterno(String tokenValue) {

        RecuperacionContrasenaEntity token = tokenRepository
                .findByTokenAndUsadoFalse(tokenValue)
                .orElseThrow(() -> new BadRequestException(
                        MessageConstants.Token.INVALID
                ));

        if (token.getExpiracion().isBefore(LocalDateTime.now())) {
            throw new BadRequestException(MessageConstants.Token.EXPIRED);
        }

        return token;
    }
}