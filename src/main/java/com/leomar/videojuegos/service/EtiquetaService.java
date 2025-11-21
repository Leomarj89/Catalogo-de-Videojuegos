package com.leomar.videojuegos.service;

import com.leomar.videojuegos.dto.EtiquetaDTO;
import java.util.List;

public interface EtiquetaService {
    EtiquetaDTO guardar(EtiquetaDTO dto);
    List<EtiquetaDTO> listar();
    void eliminar(Integer id);
}