package com.matricula.service.email;

public interface EmailService {
    void enviarCorreo(String destinatario, String asunto, String cuerpo);
}
