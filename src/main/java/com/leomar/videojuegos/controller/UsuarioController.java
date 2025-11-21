package com.leomar.videojuegos.controller;

import com.leomar.videojuegos.dto.UsuarioDTO;
import com.leomar.videojuegos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioDTO crear(@Valid @RequestBody UsuarioDTO dto) {
        return service.guardar(dto);
    }

    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtener(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    // --------- Endpoints mejorados ---------

    // Registros por estado (COMPLETADO, EN_PROGRESO, etc.)
    @GetMapping("/estado/{estado}")
    public List<UsuarioDTO> listarPorEstado(@PathVariable("estado") String estadoTermino) {
        return service.listarPorEstado(estadoTermino);
    }

    // Todos los registros de un juego
    @GetMapping("/juego/{juegoId}")
    public List<UsuarioDTO> listarPorJuego(@PathVariable Integer juegoId) {
        return service.listarPorJuego(juegoId);
    }

    // Registros con puntaje mayor o igual a X
    @GetMapping("/puntaje-minimo/{puntaje}")
    public List<UsuarioDTO> listarPorPuntajeMinimo(@PathVariable("puntaje") Double puntajeMinimo) {
        return service.listarPorPuntajeMinimo(puntajeMinimo);
    }

    // Registros que tienen una etiqueta concreta
    @GetMapping("/etiqueta/{etiquetaId}")
    public List<UsuarioDTO> listarPorEtiqueta(@PathVariable Integer etiquetaId) {
        return service.listarPorEtiqueta(etiquetaId);
    }
}