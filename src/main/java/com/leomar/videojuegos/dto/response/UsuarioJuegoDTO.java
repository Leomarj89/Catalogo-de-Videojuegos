package com.leomar.videojuegos.dto.response;

import lombok.Data;

@Data
public class UsuarioJuegoDTO {
    private Long usuarioId;
    private Long juegoId;
    private String fechaInicio;
    private String fechaCompletado;
    private Integer horasJugadas;
    private Double puntaje;
    private String estadoTermino;
    private String notas;
}