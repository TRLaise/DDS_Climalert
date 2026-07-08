package com.utn.utnclimalert.service;

import com.utn.utnclimalert.model.RespuestaClima;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ClimaApiService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.location}")
    private String ubicacion;

    public RespuestaClima obtenerClimaActual() {
        String url = apiUrl + "?key=" + apiKey + "&q=" + ubicacion;
        return restTemplate.getForObject(url, RespuestaClima.class);
    }
}