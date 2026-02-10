package com.leomar.videojuegos.dto;

public class JuegoPuntajeStatsDTO {

    private Integer juegoId;
    private String titulo;
    private Double puntajePromedio;
    private String portada;  // NUEVO

    public JuegoPuntajeStatsDTO() {
    }

    public JuegoPuntajeStatsDTO(Integer juegoId, String titulo, Double puntajePromedio, String portada) {
        this.juegoId = juegoId;
        this.titulo = titulo;
        this.puntajePromedio = puntajePromedio;
        this.portada = portada;
    }

    public Integer getJuegoId() {
        return juegoId;
    }

    public void setJuegoId(Integer juegoId) {
        this.juegoId = juegoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getPuntajePromedio() {
        return puntajePromedio;
    }

    public void setPuntajePromedio(Double puntajePromedio) {
        this.puntajePromedio = puntajePromedio;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}