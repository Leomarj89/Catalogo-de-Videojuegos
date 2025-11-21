package com.leomar.videojuegos.controller;

import com.leomar.videojuegos.dto.EtiquetaDTO;
import com.leomar.videojuegos.service.EtiquetaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etiquetas")
public class EtiquetaController {

    private final EtiquetaService service;

    public EtiquetaController(EtiquetaService service) {
        this.service = service;
    }

    @PostMapping
    public EtiquetaDTO crear(@Valid @RequestBody EtiquetaDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping
    public List<EtiquetaDTO> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}