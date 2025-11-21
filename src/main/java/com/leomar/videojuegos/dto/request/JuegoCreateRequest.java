package com.leomar.videojuegos.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class JuegoCreateRequest {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    private Integer anioLanzamiento;
    private String desarrolador;
    private String editora;
    private String genero;
    private String plataforma;
    private String description;
    private String portada;
}