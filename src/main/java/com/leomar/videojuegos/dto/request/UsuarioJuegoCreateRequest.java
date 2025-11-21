package com.leomar.videojuegos.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;

@Data
public class UsuarioJuegoCreateRequest {

    @NotNull(message = "Debe indicar un juego asociado")
    private Long juegoId;

    private String fechaInicio;
    private String fechaCompletado;
    private Integer horasJugadas;
    private Double puntaje;
    private String estadoTermino;
    private String notas;
}