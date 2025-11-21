package com.leomar.videojuegos.service;

import com.leomar.videojuegos.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    UsuarioDTO guardar(UsuarioDTO dto);
    List<UsuarioDTO> listar();
    UsuarioDTO buscarPorId(Integer id);
    void eliminar(Integer id);

    // Nuevos
    List<UsuarioDTO> listarPorEstado(String estadoTermino);
    List<UsuarioDTO> listarPorJuego(Integer juegoId);
    List<UsuarioDTO> listarPorPuntajeMinimo(Double puntajeMinimo);
    List<UsuarioDTO> listarPorEtiqueta(Integer etiquetaId);
}
