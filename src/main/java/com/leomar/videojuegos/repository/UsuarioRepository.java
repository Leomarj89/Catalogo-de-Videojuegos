package com.leomar.videojuegos.repository;

import com.leomar.videojuegos.model.Juego;
import com.leomar.videojuegos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByJuego(Juego juego);

    List<Usuario> findByEstadoTerminoIgnoreCase(String estadoTermino);

    List<Usuario> findByPuntajeGreaterThanEqual(Double puntaje);

    // Para filtrar por etiqueta
    List<Usuario> findDistinctByEtiquetas_Id(Integer etiquetaId);
}