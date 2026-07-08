package com.utn.utnclimalert.repository;

import com.utn.utnclimalert.model.RegistroClima;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RegistroClimaRepository extends JpaRepository<RegistroClima, Long> {
    // al usar "fechaRegistro" en la entidad, el metodo cambia su nombre automaticamente
    Optional<RegistroClima> findTopByOrderByFechaRegistroDesc();
}