package com.utn.utnclimalert.scheduler;

import com.utn.utnclimalert.model.RegistroClima;
import com.utn.utnclimalert.model.RespuestaClima;
import com.utn.utnclimalert.repository.RegistroClimaRepository;
import com.utn.utnclimalert.service.NotificacionEmailService;
import com.utn.utnclimalert.service.ClimaApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ClimalertScheduler {

    private final ClimaApiService climaApiService;
    private final RegistroClimaRepository registroClimaRepository;
    private final NotificacionEmailService servicioEmail;

    @Scheduled(fixedRate = 300000)
    public void obtenerYGuardarClima() {
        try {
            RespuestaClima respuesta = climaApiService.obtenerClimaActual();
            if (respuesta != null && respuesta.getActual() != null) {
                RegistroClima registro = new RegistroClima();
                registro.setTemperatura(respuesta.getActual().getTemperatura());
                registro.setHumedad(respuesta.getActual().getHumedad());
                registro.setFechaRegistro(LocalDateTime.now());

                registroClimaRepository.save(registro);
                System.out.println("Nuevos datos almacenados - Temp: " + registro.getTemperatura() + "°C, Humedad: " + registro.getHumedad() + "%");
            }
        } catch (Exception e) {
            System.err.println("Error al contactar proveedor externo: " + e.getMessage());
        }
    }

    @Scheduled(fixedRate = 60000)
    public void procesarAlertas() {
        registroClimaRepository.findTopByOrderByFechaRegistroDesc().ifPresent(ultimoRegistro -> {
            if (ultimoRegistro.getTemperatura() > 35.0 && ultimoRegistro.getHumedad() > 60) {
                System.out.println("Condiciones críticas detectadas. Disparando alertas...");
                servicioEmail.enviarEmailAlerta(ultimoRegistro);
            }
        });
    }
}