package com.leomar.videojuegos.controller;

import com.leomar.videojuegos.dto.JuegoDTO;
import com.leomar.videojuegos.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final JuegoService service;

    public JuegoController(JuegoService service) {
        this.service = service;
    }

    @PostMapping
    public JuegoDTO crear(@Valid @RequestBody JuegoDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping
    public List<JuegoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public JuegoDTO obtener(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    // --------- Endpoints mejorados ---------

    // Buscar por parte del título
    @GetMapping("/buscar")
    public List<JuegoDTO> buscarPorTitulo(@RequestParam("titulo") String titulo) {
        return service.buscarPorTitulo(titulo);
    }

    // Filtrar por género
    @GetMapping("/genero/{genero}")
    public List<JuegoDTO> buscarPorGenero(@PathVariable String genero) {
        return service.buscarPorGenero(genero);
    }

    // Filtrar por plataforma
    @GetMapping("/plataforma/{plataforma}")
    public List<JuegoDTO> buscarPorPlataforma(@PathVariable String plataforma) {
        return service.buscarPorPlataforma(plataforma);
    }
}