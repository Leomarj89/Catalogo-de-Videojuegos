package com.leomar.videojuegos.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class UsuarioDTO {
    private Integer id;
    private Integer juegoId;
    private Date fechaInicio;
    private Date fechaCompletado;
    private Integer horasJugadas;
    private Double puntaje;
    private String estadoTermino;
    private String notas;
    private List<Integer> etiquetasIds;
}