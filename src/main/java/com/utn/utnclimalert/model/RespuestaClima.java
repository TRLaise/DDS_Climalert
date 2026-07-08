package com.utn.utnclimalert.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RespuestaClima {

    @JsonProperty("current")
    private Actual actual;

    @Data
    public static class Actual {
        @JsonProperty("temp_c")
        private double temperatura;

        @JsonProperty("humidity")
        private int humedad;
    }
}