package com.leomar.videojuegos.repository;

import com.leomar.videojuegos.model.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuegoRepository extends JpaRepository<Juego, Integer> {

    boolean existsByTituloIgnoreCase(String titulo);

    List<Juego> findByGeneroIgnoreCase(String genero);

    List<Juego> findByPlataformaIgnoreCase(String plataforma);

    List<Juego> findByTituloContainingIgnoreCase(String titulo);
}