package com.leomar.videojuegos.service.impl;

import com.leomar.videojuegos.dto.JuegoDTO;
import com.leomar.videojuegos.model.Juego;
import com.leomar.videojuegos.repository.JuegoRepository;
import com.leomar.videojuegos.service.JuegoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JuegoServiceImpl implements JuegoService {

    private final JuegoRepository repo;

    public JuegoServiceImpl(JuegoRepository repo) {
        this.repo = repo;
    }

    @Override
    public JuegoDTO guardar(JuegoDTO dto) {
        Juego juego = toEntity(dto);
        juego = repo.save(juego);
        return toDto(juego);
    }

    @Override
    public List<JuegoDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public JuegoDTO buscarPorId(Integer id) {
        return repo.findById(id)
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<JuegoDTO> buscarPorGenero(String genero) {
        return repo.findByGeneroIgnoreCase(genero)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JuegoDTO> buscarPorPlataforma(String plataforma) {
        return repo.findByPlataformaIgnoreCase(plataforma)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<JuegoDTO> buscarPorTitulo(String tituloParcial) {
        return repo.findByTituloContainingIgnoreCase(tituloParcial)
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    // ---------- mapping helpers ----------

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
        return dto;
    }

    private Juego toEntity(JuegoDTO dto) {
        Juego juego = new Juego();
        juego.setId(dto.getId());
        juego.setTitulo(dto.getTitulo());
        juego.setAnioLanzamiento(dto.getAnioLanzamiento());
        juego.setDesarrollador(dto.getDesarrollador());
        juego.setEditora(dto.getEditora());
        juego.setGenero(dto.getGenero());
        juego.setPlataforma(dto.getPlataforma());
        juego.setDescripcion(dto.getDescripcion());
        juego.setPortada(dto.getPortada());
        return juego;
    }
}
