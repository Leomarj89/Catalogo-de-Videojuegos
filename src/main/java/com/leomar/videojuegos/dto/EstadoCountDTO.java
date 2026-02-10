package com.leomar.videojuegos.dto;

import com.leomar.videojuegos.model.EstadoTermino;

public class EstadoCountDTO {

    private EstadoTermino estado;
    private Long total;

    public EstadoCountDTO() {
    }

    public EstadoCountDTO(EstadoTermino estado, Long total) {
        this.estado = estado;
        this.total = total;
    }

    public EstadoTermino getEstado() {
        return estado;
    }

    public void setEstado(EstadoTermino estado) {
        this.estado = estado;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}