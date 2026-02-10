package com.leomar.videojuegos.dto;

import com.leomar.videojuegos.model.EstadoTermino;
import com.leomar.videojuegos.model.Plataforma;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public class JuegoDTO {

    private Integer id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 255, message = "El título no puede superar 255 caracteres")
    private String titulo;

    @Min(value = 1970, message = "El año de lanzamiento debe ser mayor o igual a 1970")
    @Max(value = 2100, message = "El año de lanzamiento es demasiado grande")
    private Integer anioLanzamiento;

    @Size(max = 255, message = "El desarrollador no puede superar 255 caracteres")
    private String desarrollador;

    @Size(max = 255, message = "La editora no puede superar 255 caracteres")
    private String editora;

    @Size(max = 100, message = "El género no puede superar 100 caracteres")
    private String genero;

    // Plataforma como enum (PC, PS4, etc.)
    private Plataforma plataforma;

    private String descripcion;

    @Size(max = 500, message = "La URL de la portada no puede superar 500 caracteres")
    private String portada;

    // ==== Progreso personal ====

    @Min(value = 0, message = "Las horas jugadas no pueden ser negativas")
    private Integer horasJugadas;

    @DecimalMin(value = "0.0", inclusive = true, message = "El puntaje no puede ser negativo")
    @DecimalMax(value = "10.0", inclusive = true, message = "El puntaje máximo permitido es 10.0")
    private BigDecimal puntaje;

    // puede ser null si aún no definiste estado
    private EstadoTermino estadoTermino;

    @Size(max = 1000, message = "Las notas no pueden superar 1000 caracteres")
    private String notas;

    // IDs de etiquetas a asociar (para POST/PUT)
    private Set<Integer> etiquetasIds;

    // Solo lectura: nombres de las etiquetas actuales del juego
    private Set<String> etiquetasNombres;

    // ===== Constructores =====

    public JuegoDTO() {
    }

    // Puedes agregar constructores extra si quieres

    // ===== Getters / Setters =====

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(Integer anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getDesarrollador() {
        return desarrollador;
    }

    public void setDesarrollador(String desarrollador) {
        this.desarrollador = desarrollador;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public Integer getHorasJugadas() {
        return horasJugadas;
    }

    public void setHorasJugadas(Integer horasJugadas) {
        this.horasJugadas = horasJugadas;
    }

    public BigDecimal getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(BigDecimal puntaje) {
        this.puntaje = puntaje;
    }

    public EstadoTermino getEstadoTermino() {
        return estadoTermino;
    }

    public void setEstadoTermino(EstadoTermino estadoTermino) {
        this.estadoTermino = estadoTermino;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Set<Integer> getEtiquetasIds() {
        return etiquetasIds;
    }

    public void setEtiquetasIds(Set<Integer> etiquetasIds) {
        this.etiquetasIds = etiquetasIds;
    }

    public Set<String> getEtiquetasNombres() {
        return etiquetasNombres;
    }

    public void setEtiquetasNombres(Set<String> etiquetasNombres) {
        this.etiquetasNombres = etiquetasNombres;
    }
}