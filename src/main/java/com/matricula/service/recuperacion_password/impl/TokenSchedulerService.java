package com.matricula.service.recuperacion_password.impl;

import com.matricula.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenSchedulerService {

    private final TokenRepository tokenRepository;

    @Scheduled(fixedRate = 60000)
    @Transactional
    public void limpiarTokensExpirados() {

        int afectados = tokenRepository.limpiarTokensExpirados(LocalDateTime.now());

    }
}
