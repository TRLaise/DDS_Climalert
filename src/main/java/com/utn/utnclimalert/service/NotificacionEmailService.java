package com.utn.utnclimalert.service;

import com.utn.utnclimalert.model.RegistroClima;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificacionEmailService {

    private final JavaMailSender enviadorMails;

    public void enviarEmailAlerta(RegistroClima registro) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo("admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com");
        mensaje.setSubject("ALERTA METEOROLÓGICA: Condiciones Extremas");
        mensaje.setText("Se han detectado las siguientes condiciones climáticas:\n\n" +
                "Temperatura: " + registro.getTemperatura() + "°C\n" +
                "Humedad: " + registro.getHumedad() + "%\n" +
                "Registrado en: " + registro.getFechaRegistro());

        try {
            enviadorMails.send(mensaje);
            System.out.println("Correo de alerta enviado a los destinatarios correspondientes.");
        } catch (Exception e) {
            System.err.println("[SIMULACIÓN CORREO] Alerta generada:\n" + mensaje.getText());
        }
    }
}
