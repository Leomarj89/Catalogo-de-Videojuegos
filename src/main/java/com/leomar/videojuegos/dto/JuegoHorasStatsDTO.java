package com.leomar.videojuegos.dto;

public class JuegoHorasStatsDTO {

    private Integer juegoId;
    private String titulo;
    private Long horasTotales;
    private String portada;  // NUEVO

    public JuegoHorasStatsDTO() {
    }

    public JuegoHorasStatsDTO(Integer juegoId, String titulo, Long horasTotales, String portada) {
        this.juegoId = juegoId;
        this.titulo = titulo;
        this.horasTotales = horasTotales;
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

    public Long getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Long horasTotales) {
        this.horasTotales = horasTotales;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }
}