package com.leomar.videojuegos.controller;

import com.leomar.videojuegos.dto.JuegoDTO;
import com.leomar.videojuegos.service.JuegoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/juegos")
public class JuegoController {

    private final JuegoService service;

    public JuegoController(JuegoService service) {
        this.service = service;
    }

    // ========= CRUD básico =========

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JuegoDTO crear(@Valid @RequestBody JuegoDTO dto) {
        // No tocamos el id, si viene null -> crear
        return service.crear(dto);
    }

    @GetMapping
    public List<JuegoDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public JuegoDTO obtener(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public JuegoDTO actualizar(@PathVariable Integer id,
                               @Valid @RequestBody JuegoDTO dto) {
        // No seteamos el id en el DTO, usamos el de la URL
        return service.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    // ========= Endpoints de búsqueda =========

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

    // Filtrar por plataforma (PC, PS4, etc.)
    @GetMapping("/plataforma/{plataforma}")
    public List<JuegoDTO> buscarPorPlataforma(@PathVariable String plataforma) {
        return service.buscarPorPlataforma(plataforma);
    }
}