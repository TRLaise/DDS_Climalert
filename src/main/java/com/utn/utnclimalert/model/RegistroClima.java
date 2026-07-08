package com.utn.utnclimalert.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class RegistroClima {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double temperatura;
    private int humedad;
    private LocalDateTime fechaRegistro;
}
