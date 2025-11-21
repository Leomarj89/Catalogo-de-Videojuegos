package com.leomar.videojuegos.repository;

import com.leomar.videojuegos.model.Etiqueta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {

    // Buscar por nombre exacto (ignorando mayúsculas/minúsculas)
    Etiqueta findByNombreEtiquetaIgnoreCase(String nombreEtiqueta);

    // Verificar si existe la etiqueta
    boolean existsByNombreEtiquetaIgnoreCase(String nombreEtiqueta);

}