package com.leomar.videojuegos.service;

import com.leomar.videojuegos.dto.EstadoCountDTO;
import com.leomar.videojuegos.dto.JuegoDTO;
import com.leomar.videojuegos.dto.JuegoHorasStatsDTO;
import com.leomar.videojuegos.dto.JuegoPuntajeStatsDTO;

import java.util.List;

public interface JuegoService {

    JuegoDTO guardar(JuegoDTO dto);       // upsert (si trae id, actualiza; si no, crea)

    JuegoDTO crear(JuegoDTO dto);         // crear explícito
    JuegoDTO actualizar(Integer id, JuegoDTO dto);

    List<JuegoDTO> listar();
    JuegoDTO buscarPorId(Integer id);
    void eliminar(Integer id);

    // Búsquedas
    List<JuegoDTO> buscarPorTitulo(String tituloParcial);
    List<JuegoDTO> buscarPorGenero(String genero);
    List<JuegoDTO> buscarPorPlataforma(String plataformaTexto);

    // ====== STATS ======

    List<EstadoCountDTO> obtenerTotalPorEstado();

    List<JuegoHorasStatsDTO> obtenerTopJuegosPorHoras(int limite);

    List<JuegoPuntajeStatsDTO> obtenerPuntajePromedioPorJuego();
}