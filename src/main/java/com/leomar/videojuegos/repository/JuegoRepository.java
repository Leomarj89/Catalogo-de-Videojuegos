package com.leomar.videojuegos.repository;

import com.leomar.videojuegos.dto.EstadoCountDTO;
import com.leomar.videojuegos.dto.JuegoHorasStatsDTO;
import com.leomar.videojuegos.dto.JuegoPuntajeStatsDTO;
import com.leomar.videojuegos.model.Juego;
import com.leomar.videojuegos.model.Plataforma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Integer> {

    // --- búsquedas básicas ---
    List<Juego> findByTituloContainingIgnoreCase(String tituloParcial);

    List<Juego> findByGeneroIgnoreCase(String genero);

    List<Juego> findByPlataforma(Plataforma plataforma);

    // --- STATS ---

    @Query("""
           SELECT new com.leomar.videojuegos.dto.EstadoCountDTO(
               j.estadoTermino,
               COUNT(j)
           )
           FROM Juego j
           GROUP BY j.estadoTermino
           """)
    List<EstadoCountDTO> totalPorEstado();

    @Query("""
           SELECT new com.leomar.videojuegos.dto.JuegoHorasStatsDTO(
               j.id,
               j.titulo,
               SUM(j.horasJugadas),
               j.portada
           )
           FROM Juego j
           WHERE j.horasJugadas IS NOT NULL
           GROUP BY j.id, j.titulo, j.portada
           ORDER BY SUM(j.horasJugadas) DESC
           """)
    List<JuegoHorasStatsDTO> topJuegosPorHoras();

    @Query("""
           SELECT new com.leomar.videojuegos.dto.JuegoPuntajeStatsDTO(
               j.id,
               j.titulo,
               AVG(j.puntaje),
               j.portada
           )
           FROM Juego j
           WHERE j.puntaje IS NOT NULL
           GROUP BY j.id, j.titulo, j.portada
           ORDER BY AVG(j.puntaje) DESC
           """)
    List<JuegoPuntajeStatsDTO> puntajePromedioPorJuego();
}