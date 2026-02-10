package com.leomar.videojuegos.controller;

import com.leomar.videojuegos.dto.JuegoDTO;
import com.leomar.videojuegos.model.Plataforma;
import com.leomar.videojuegos.service.JuegoService;
import com.leomar.videojuegos.service.EtiquetaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/juegos")
public class JuegoViewController {

    private final JuegoService juegoService;
    private final EtiquetaService etiquetaService;

    public JuegoViewController(JuegoService juegoService,
                               EtiquetaService etiquetaService) {
        this.juegoService = juegoService;
        this.etiquetaService = etiquetaService;
    }

    // =========================
    // VER DETALLE
    // =========================
    @GetMapping("/{id}")
    public String verDetalle(@PathVariable Integer id, Model model) {
        JuegoDTO juego = juegoService.buscarPorId(id);

        if (juego == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Juego no encontrado");
        }

        model.addAttribute("juego", juego);
        return "juegos/detalle";  // templates/juegos/detalle.html
    }

    // =========================
    // NUEVO JUEGO (FORM)
    // =========================
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        JuegoDTO dto = new JuegoDTO();

        model.addAttribute("juego", dto);
        model.addAttribute("etiquetas", etiquetaService.listar());
        model.addAttribute("modoEdicion", false); // para el título/botón en el form

        return "juegos/nuevo";  // templates/juegos/nuevo.html
    }

    // =========================
    // EDITAR JUEGO (FORM)
    // =========================
    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Integer id, Model model) {
        JuegoDTO juego = juegoService.buscarPorId(id);

        if (juego == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Juego no encontrado");
        }

        model.addAttribute("juego", juego);
        model.addAttribute("etiquetas", etiquetaService.listar());
        model.addAttribute("modoEdicion", true);

        // Reutilizamos el MISMO formulario que para "nuevo"
        return "juegos/nuevo";
    }

    // =========================
    // GUARDAR (CREAR / EDITAR)
    // =========================
    @PostMapping("/guardar")
    public String guardarJuego(@Valid @ModelAttribute("juego") JuegoDTO dto,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            // Si hay errores, volvemos al formulario con el listado de etiquetas
            model.addAttribute("etiquetas", etiquetaService.listar());
            model.addAttribute("modoEdicion", dto.getId() != null);
            return "juegos/nuevo";
        }

        // IMPORTANTE:
        // tu JuegoServiceImpl tiene un método guardar(dto)
        // que hace: si id == null -> crear; si id != null -> actualizar
        JuegoDTO guardado = juegoService.guardar(dto);

        redirectAttributes.addFlashAttribute("mensaje", "Juego guardado correctamente.");
        return "redirect:/juegos/" + guardado.getId();
    }

    // =========================
    // ELIMINAR
    // =========================
    @PostMapping("/{id}/eliminar")
    public String eliminarJuego(@PathVariable Integer id,
                                RedirectAttributes redirectAttributes) {

        juegoService.eliminar(id);
        redirectAttributes.addFlashAttribute("mensaje", "Juego eliminado correctamente.");
        // Puedes redirigir al dashboard si prefieres:
        // return "redirect:/dashboard";
        return "redirect:/";
    }

    // ========================
    // Listar Juegos
    // ========================

    @GetMapping("/lista")
    public String listarJuegos(
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "letra", required = false) String letra,
            @RequestParam(name = "plataforma", required = false) String plataforma,
            Model model
    ) {
        // Partimos con todos los juegos
        List<JuegoDTO> juegos = juegoService.listar();

        // Filtro por texto libre (título)
        if (query != null && !query.isBlank()) {
            String qLower = query.toLowerCase();
            juegos = juegos.stream()
                    .filter(j -> j.getTitulo() != null &&
                            j.getTitulo().toLowerCase().contains(qLower))
                    .toList();
        }

        // Filtro por letra inicial del título
        if (letra != null && !letra.isBlank()) {
            String inicial = letra.substring(0, 1).toUpperCase();
            juegos = juegos.stream()
                    .filter(j -> j.getTitulo() != null &&
                            !j.getTitulo().isBlank() &&
                            j.getTitulo().toUpperCase().startsWith(inicial))
                    .toList();
        }

        // Filtro por plataforma (enum)
        if (plataforma != null && !plataforma.isBlank()) {
            String platUpper = plataforma.toUpperCase();
            juegos = juegos.stream()
                    .filter(j -> j.getPlataforma() != null &&
                            j.getPlataforma().name().equalsIgnoreCase(platUpper))
                    .toList();
        }

        // Letras A-Z para el filtro
        List<String> letras = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            letras.add(String.valueOf(c));
        }

        model.addAttribute("juegos", juegos);
        model.addAttribute("q", query);
        model.addAttribute("letraSeleccionada", letra);
        model.addAttribute("plataformaSeleccionada", plataforma);
        model.addAttribute("letras", letras);
        model.addAttribute("plataformas", Plataforma.values());

        // Mapea a src/main/resources/templates/juegos/lista.html
        return "juegos/lista";
    }
}