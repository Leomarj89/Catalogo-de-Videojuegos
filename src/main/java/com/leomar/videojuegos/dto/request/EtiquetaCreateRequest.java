package com.leomar.videojuegos.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

@Data
public class EtiquetaCreateRequest {
    @NotBlank(message = "El nombre de la etiqueta no puede estar vacío")
    private String nombreEtiqueta;
}