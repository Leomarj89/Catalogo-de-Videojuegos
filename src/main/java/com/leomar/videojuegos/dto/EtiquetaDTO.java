package com.leomar.videojuegos.dto;

public class EtiquetaDTO {

    private Integer id;
    private String nombreEtiqueta;

    public EtiquetaDTO() {
    }

    public EtiquetaDTO(Integer id, String nombreEtiqueta) {
        this.id = id;
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }
}
