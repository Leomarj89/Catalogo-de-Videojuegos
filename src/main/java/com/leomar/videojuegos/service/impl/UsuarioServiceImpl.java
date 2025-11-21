package com.leomar.videojuegos.service.impl;

import com.leomar.videojuegos.dto.UsuarioDTO;
import com.leomar.videojuegos.model.Etiqueta;
import com.leomar.videojuegos.model.Juego;
import com.leomar.videojuegos.model.Usuario;
import com.leomar.videojuegos.repository.EtiquetaRepository;
import com.leomar.videojuegos.repository.JuegoRepository;
import com.leomar.videojuegos.repository.UsuarioRepository;
import com.leomar.videojuegos.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepo;
    private final JuegoRepository juegoRepo;
    private final EtiquetaRepository etiquetaRepo;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepo,
                              JuegoRepository juegoRepo,
                              EtiquetaRepository etiquetaRepo) {
        this.usuarioRepo = usuarioRepo;
        this.juegoRepo = juegoRepo;
        this.etiquetaRepo = etiquetaRepo;
    }

    @Override
    @Transactional
    public UsuarioDTO guardar(UsuarioDTO dto) {
        Usuario usuario;

        if (dto.getId() != null) {
            usuario = usuarioRepo.findById(dto.getId()).orElse(new Usuario());
        } else {
            usuario = new Usuario();
        }

        Juego juego = juegoRepo.findById(dto.getJuegoId())
                .orElseThrow(() -> new IllegalArgumentException("Juego no encontrado id=" + dto.getJuegoId()));

        usuario.setJuego(juego);
        usuario.setFechaInicio(dto.getFechaInicio());
        usuario.setFechaCompletado(dto.getFechaCompletado());
        usuario.setHorasJugadas(dto.getHorasJugadas());
        usuario.setPuntaje(dto.getPuntaje());
        usuario.setEstadoTermino(dto.getEstadoTermino());
        usuario.setNotas(dto.getNotas());

        if (dto.getEtiquetasIds() != null && !dto.getEtiquetasIds().isEmpty()) {
            List<Etiqueta> etiquetas = etiquetaRepo.findAllById(dto.getEtiquetasIds());
            if (usuario.getEtiquetas() == null) {
                usuario.setEtiquetas(new HashSet<>());
            }
            usuario.getEtiquetas().clear();
            usuario.getEtiquetas().addAll(etiquetas);
        } else {
            if (usuario.getEtiquetas() != null) {
                usuario.getEtiquetas().clear();
            }
        }

        usuario = usuarioRepo.save(usuario);
        return toDto(usuario);
    }

    @Override
    public List<UsuarioDTO> listar() {
        return usuarioRepo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO buscarPorId(Integer id) {
        return usuarioRepo.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        usuarioRepo.deleteById(id);
    }

    @Override
    public List<UsuarioDTO> listarPorEstado(String estadoTermino) {
        return usuarioRepo.findByEstadoTerminoIgnoreCase(estadoTermino)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> listarPorJuego(Integer juegoId) {
        Juego juego = juegoRepo.findById(juegoId)
                .orElseThrow(() -> new IllegalArgumentException("Juego no encontrado id=" + juegoId));

        return usuarioRepo.findByJuego(juego)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> listarPorPuntajeMinimo(Double puntajeMinimo) {
        return usuarioRepo.findByPuntajeGreaterThanEqual(puntajeMinimo)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDTO> listarPorEtiqueta(Integer etiquetaId) {
        return usuarioRepo.findDistinctByEtiquetas_Id(etiquetaId)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ---------- mapping helper ----------

    private UsuarioDTO toDto(Usuario u) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(u.getId());
        dto.setJuegoId(u.getJuego().getId());
        dto.setFechaInicio(u.getFechaInicio());
        dto.setFechaCompletado(u.getFechaCompletado());
        dto.setHorasJugadas(u.getHorasJugadas());
        dto.setPuntaje(u.getPuntaje());
        dto.setEstadoTermino(u.getEstadoTermino());
        dto.setNotas(u.getNotas());

        if (u.getEtiquetas() != null) {
            List<Integer> etiquetasIds = u.getEtiquetas()
                    .stream()
                    .map(Etiqueta::getId)
                    .collect(Collectors.toList());
            dto.setEtiquetasIds(etiquetasIds);
        } else {
            dto.setEtiquetasIds(Collections.emptyList());
        }

        return dto;
    }
}