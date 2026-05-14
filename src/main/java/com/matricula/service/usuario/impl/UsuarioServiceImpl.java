package com.matricula.service.usuario.impl;

import com.matricula.dto.common.PageResponseDTO;
import com.matricula.dto.usuario.*;
import com.matricula.entity.PersonaEntity;
import com.matricula.entity.RolEntity;
import com.matricula.entity.UsuarioEntity;
import com.matricula.exception.BadRequestException;
import com.matricula.exception.NotFoundException;
import com.matricula.exception.UnauthorizedException;
import com.matricula.mapper.UsuarioMapper;
import com.matricula.repository.PersonaRepository;
import com.matricula.repository.RolRepository;
import com.matricula.repository.UsuarioRepository;
import com.matricula.security.JwtUtil;
import com.matricula.service.usuario.UsuarioService;
import com.matricula.specification.UsuarioSpecification;
import com.matricula.util.MessageConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class  UsuarioServiceImpl implements UsuarioService {

    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    //REGISTER
    @Override
    public UsuarioResponseDTO register(RegisterRequestDTO request) {

        // 1. Validar correo
        if (usuarioRepository.existsByCorreo(request.correo())) {
            throw new BadRequestException(MessageConstants.Usuario.EMAIL_EXISTS);
        }

        // 2. Validar rol
        RolEntity rol = rolRepository.findById(request.idRol())
                .orElseThrow(() -> new NotFoundException(MessageConstants.Rol.NOT_FOUND));

        // 3. Mapear
        UsuarioEntity usuario = usuarioMapper.toEntity(request);

        // 4.Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(request.password()));

        // 5. Setear rol real
        usuario.setRol(rol);

        // 6. Guardar persona
        PersonaEntity persona = personaRepository.save(usuario.getPersona());
        usuario.setPersona(persona);

        // 7. Guardar usuario
        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    public AuthResponse login(LoginRequestDTO request) {

        // 1. Buscar usuario
        UsuarioEntity usuario = usuarioRepository.findByCorreo(request.correo())
                .orElseThrow(() -> new UnauthorizedException(MessageConstants.Usuario.INVALID_CREDENTIALS));

        // 2. Validar contraseña
        if (!passwordEncoder.matches(request.password(), usuario.getPassword())) {
            throw new UnauthorizedException(MessageConstants.Usuario.INVALID_CREDENTIALS);
        }

        // 3. Validar estado
        if (Boolean.FALSE.equals(usuario.getEstado())) {
            throw new UnauthorizedException(MessageConstants.Usuario.INACTIVE);
        }

        // 4. Generar token
        String token = jwtUtil.generateToken(
                usuario.getCorreo(),
                usuario.getRol().getNombre(),
                usuario.getIdUsuario()
        );

        // 5. Retornar respuesta
        return new AuthResponse(
                usuario.getIdUsuario(),
                usuario.getPersona().getNombre(),
                usuario.getRol().getNombre(),
                token
        );
    }

    @Override
    public PageResponseDTO<UsuarioResponseDTO> list(
            String search,
            String rol,
            Boolean estado,
            Pageable pageable
    ) {

        Page<UsuarioEntity> usuariosPage = usuarioRepository.findAll(
                UsuarioSpecification.filtrar(search, rol, estado),
                pageable
        );

        List<UsuarioResponseDTO> usuarios = usuariosPage.getContent()
                .stream()
                .map(usuarioMapper::toResponseDTO)
                .toList();

        return new PageResponseDTO<>(
                usuarios,
                usuariosPage.getNumber(),
                usuariosPage.getSize(),
                usuariosPage.getTotalElements(),
                usuariosPage.getTotalPages(),
                usuariosPage.isFirst(),
                usuariosPage.isLast()
        );
    }

    @Override
    public UsuarioResponseDTO changeStatus(Integer id) {
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageConstants.Usuario.NOT_FOUND)
                );

        usuario.setEstado(!usuario.getEstado());

        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO update(Integer id, UpdateUsuarioRequestDTO usuarioRequestDTO) {
        // 1. Buscar usuario
        UsuarioEntity usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException(MessageConstants.Usuario.NOT_FOUND)
                );

        // 2. Validar correo repetido
        if (!usuario.getCorreo().equals(usuarioRequestDTO.correo())
                && usuarioRepository.existsByCorreo(usuarioRequestDTO.correo())) {

            throw new BadRequestException(MessageConstants.Usuario.EMAIL_EXISTS);
        }

        // 3. Buscar rol
        RolEntity rol = rolRepository.findById(usuarioRequestDTO.idRol())
                .orElseThrow(() ->
                        new NotFoundException(MessageConstants.Rol.NOT_FOUND)
                );

        // 4. Actualizar usuario
        usuario.setCorreo(usuarioRequestDTO.correo());
        usuario.setRol(rol);
        usuario.setEstado(usuarioRequestDTO.estado());

        // 5. Actualizar contraseña si viene
        if (usuarioRequestDTO.password() != null && !usuarioRequestDTO.password().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(usuarioRequestDTO.password()));
        }

        // 6. Actualizar persona
        usuario.getPersona().setNombre(usuarioRequestDTO.nombre());
        usuario.getPersona().setApellidos(usuarioRequestDTO.apellidos());

        // 7. Guardar
        usuario = usuarioRepository.save(usuario);

        // 8. Retornar
        return usuarioMapper.toResponseDTO(usuario);
    }

}
