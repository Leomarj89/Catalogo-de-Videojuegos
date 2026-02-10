package com.leomar.videojuegos.controller;

import com.leomar.videojuegos.service.JuegoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DashboardController {

    private final JuegoService juegoService;

    public DashboardController(JuegoService juegoService) {
        this.juegoService = juegoService;
    }

    @GetMapping("/dashboard")
    public String verDashboard(
            @RequestParam(name = "top", defaultValue = "5") int top,
            Model model
    ) {
        model.addAttribute("estadisticasEstado", juegoService.obtenerTotalPorEstado());
        model.addAttribute("topHoras", juegoService.obtenerTopJuegosPorHoras(top));
        model.addAttribute("puntajes", juegoService.obtenerPuntajePromedioPorJuego());
        model.addAttribute("top", top);

        // se mapea a src/main/resources/templates/dashboard.html
        return "dashboard";
    }
}