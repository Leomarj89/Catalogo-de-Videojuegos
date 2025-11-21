package com.leomar.videojuegos.service.impl;

import com.leomar.videojuegos.dto.EtiquetaDTO;
import com.leomar.videojuegos.model.Etiqueta;
import com.leomar.videojuegos.repository.EtiquetaRepository;
import com.leomar.videojuegos.service.EtiquetaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtiquetaServiceImpl implements EtiquetaService {

    private final EtiquetaRepository repo;

    public EtiquetaServiceImpl(EtiquetaRepository repo) {
        this.repo = repo;
    }

    @Override
    public EtiquetaDTO guardar(EtiquetaDTO dto) {
        Etiqueta etiqueta = toEntity(dto);
        etiqueta = repo.save(etiqueta);
        return toDto(etiqueta);
    }

    @Override
    public List<EtiquetaDTO> listar() {
        return repo.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    // ---------- mapping helpers ----------

    private EtiquetaDTO toDto(Etiqueta e) {
        EtiquetaDTO dto = new EtiquetaDTO();
        dto.setId(e.getId());
        dto.setNombreEtiqueta(e.getNombreEtiqueta());
        return dto;
    }

    private Etiqueta toEntity(EtiquetaDTO dto) {
        Etiqueta e = new Etiqueta();
        e.setId(dto.getId());
        e.setNombreEtiqueta(dto.getNombreEtiqueta());
        return e;
    }
}