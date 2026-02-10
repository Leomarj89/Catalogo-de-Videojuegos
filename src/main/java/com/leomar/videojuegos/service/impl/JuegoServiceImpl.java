package com.leomar.videojuegos.service.impl;

import com.leomar.videojuegos.dto.EstadoCountDTO;
import com.leomar.videojuegos.dto.JuegoDTO;
import com.leomar.videojuegos.dto.JuegoHorasStatsDTO;
import com.leomar.videojuegos.dto.JuegoPuntajeStatsDTO;
import com.leomar.videojuegos.model.Etiqueta;
import com.leomar.videojuegos.model.Juego;
import com.leomar.videojuegos.model.Plataforma;
import com.leomar.videojuegos.repository.EtiquetaRepository;
import com.leomar.videojuegos.repository.JuegoRepository;
import com.leomar.videojuegos.service.JuegoService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JuegoServiceImpl implements JuegoService {

    private final JuegoRepository juegoRepository;
    private final EtiquetaRepository etiquetaRepository;

    public JuegoServiceImpl(JuegoRepository juegoRepository,
                            EtiquetaRepository etiquetaRepository) {
        this.juegoRepository = juegoRepository;
        this.etiquetaRepository = etiquetaRepository;
    }

    // ========================================================
    // CRUD
    // ========================================================

    @Override
    public JuegoDTO guardar(JuegoDTO dto) {
        if (dto.getId() == null) {
            return crear(dto);
        } else {
            return actualizar(dto.getId(), dto);
        }
    }

    @Override
    public JuegoDTO crear(JuegoDTO dto) {
        Juego juego = new Juego();
        actualizarEntidadDesdeDto(juego, dto);
        juego = juegoRepository.save(juego);
        return toDto(juego);
    }

    @Override
    public JuegoDTO actualizar(Integer id, JuegoDTO dto) {
        Juego juego = juegoRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Juego no encontrado con id=" + id));

        actualizarEntidadDesdeDto(juego, dto);

        juego = juegoRepository.save(juego);
        return toDto(juego);
    }

    @Override
    public List<JuegoDTO> listar() {
        return juegoRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JuegoDTO buscarPorId(Integer id) {
        return juegoRepository.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        juegoRepository.deleteById(id);
    }

    // ========================================================
    // Búsquedas
    // ========================================================

    @Override
    public List<JuegoDTO> buscarPorTitulo(String tituloParcial) {
        if (tituloParcial == null || tituloParcial.isBlank()) {
            return List.of();
        }
        return juegoRepository.findByTituloContainingIgnoreCase(tituloParcial)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JuegoDTO> buscarPorGenero(String genero) {
        if (genero == null || genero.isBlank()) {
            return List.of();
        }
        return juegoRepository.findByGeneroIgnoreCase(genero.trim())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JuegoDTO> buscarPorPlataforma(String plataformaTexto) {
        if (plataformaTexto == null || plataformaTexto.isBlank()) {
            return List.of();
        }

        Plataforma plataforma;
        try {
            plataforma = Plataforma.valueOf(plataformaTexto.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            // Esto se transformará en 400 por el GlobalExceptionHandler
            throw new IllegalArgumentException(
                    "Plataforma no válida: " + plataformaTexto +
                            ". Valores válidos: " + Arrays.toString(Plataforma.values())
            );
        }

        return juegoRepository.findByPlataforma(plataforma)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ========================================================
    // Helpers de mapeo
    // ========================================================

    private JuegoDTO toDto(Juego j) {
        JuegoDTO dto = new JuegoDTO();

        dto.setId(j.getId());
        dto.setTitulo(j.getTitulo());
        dto.setAnioLanzamiento(j.getAnioLanzamiento());
        dto.setDesarrollador(j.getDesarrollador());
        dto.setEditora(j.getEditora());
        dto.setGenero(j.getGenero());
        dto.setPlataforma(j.getPlataforma());
        dto.setDescripcion(j.getDescripcion());
        dto.setPortada(j.getPortada());

        dto.setHorasJugadas(j.getHorasJugadas());
        dto.setPuntaje(j.getPuntaje());
        dto.setEstadoTermino(j.getEstadoTermino());
        dto.setNotas(j.getNotas());

        if (j.getEtiquetas() != null && !j.getEtiquetas().isEmpty()) {
            dto.setEtiquetasIds(
                    j.getEtiquetas()
                            .stream()
                            .map(Etiqueta::getId)
                            .collect(Collectors.toSet())
            );

            dto.setEtiquetasNombres(
                    j.getEtiquetas()
                            .stream()
                            .map(Etiqueta::getNombreEtiqueta)
                            .collect(Collectors.toSet())
            );
        } else {
            dto.setEtiquetasIds(Collections.emptySet());
            dto.setEtiquetasNombres(Collections.emptySet());
        }

        return dto;
    }

    private void actualizarEntidadDesdeDto(Juego juego, JuegoDTO dto) {
        // Datos base
        juego.setTitulo(dto.getTitulo());
        juego.setAnioLanzamiento(dto.getAnioLanzamiento());
        juego.setDesarrollador(dto.getDesarrollador());
        juego.setEditora(dto.getEditora());
        juego.setGenero(dto.getGenero());
        juego.setPlataforma(dto.getPlataforma());
        juego.setDescripcion(dto.getDescripcion());
        juego.setPortada(dto.getPortada());

        // Progreso personal
        juego.setHorasJugadas(dto.getHorasJugadas());
        juego.setPuntaje(dto.getPuntaje());
        juego.setEstadoTermino(dto.getEstadoTermino());
        juego.setNotas(dto.getNotas());

        // Etiquetas:
        // - Si dto.getEtiquetasIds() es null -> NO toco las etiquetas existentes
        // - Si no es null -> reemplazo completamente por esas IDs
        if (dto.getEtiquetasIds() != null) {
            if (dto.getEtiquetasIds().isEmpty()) {
                juego.setEtiquetas(new HashSet<>());
            } else {
                juego.setEtiquetas(new HashSet<>(
                        etiquetaRepository.findAllById(dto.getEtiquetasIds())
                ));
            }
        }
    }

    // ====== STATS ======

    @Override
    public List<EstadoCountDTO> obtenerTotalPorEstado() {
        return juegoRepository.totalPorEstado();
    }

    @Override
    public List<JuegoHorasStatsDTO> obtenerTopJuegosPorHoras(int limite) {
        List<JuegoHorasStatsDTO> lista = juegoRepository.topJuegosPorHoras();

        if (limite <= 0 || limite >= lista.size()) {
            return lista;
        }
        return lista.subList(0, limite);
    }

    @Override
    public List<JuegoPuntajeStatsDTO> obtenerPuntajePromedioPorJuego() {
        return juegoRepository.puntajePromedioPorJuego();
    }
}