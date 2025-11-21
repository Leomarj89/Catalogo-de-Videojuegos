package com.leomar.videojuegos.service;

import com.leomar.videojuegos.dto.JuegoDTO;

import java.util.List;

public interface JuegoService {
    JuegoDTO guardar(JuegoDTO dto);
    List<JuegoDTO> listar();
    JuegoDTO buscarPorId(Integer id);
    void eliminar(Integer id);

    // Nuevos
    List<JuegoDTO> buscarPorGenero(String genero);
    List<JuegoDTO> buscarPorPlataforma(String plataforma);
    List<JuegoDTO> buscarPorTitulo(String tituloParcial);
}
