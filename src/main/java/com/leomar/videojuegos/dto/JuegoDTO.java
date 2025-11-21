package com.leomar.videojuegos.dto;

import lombok.Data;

@Data
public class JuegoDTO {
    private Integer id;
    private String titulo;
    private Integer anioLanzamiento;
    private String desarrollador;
    private String editora;
    private String genero;
    private String plataforma;
    private String descripcion;
    private String portada;
}