package com.leomar.videojuegos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "juego")
public class Juego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "juegoId")
    private Integer id;

    @Column(nullable = false, length = 255)
    private String titulo;

    @Column(name = "anioLanzamiento")
    private Integer anioLanzamiento;

    @Column(length = 255)
    private String desarrollador;

    @Column(length = 255)
    private String editora;

    @Column(length = 100)
    private String genero;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma", length = 50)
    private Plataforma plataforma;

    // En la BD puede ser nvarchar(max)
    private String descripcion;

    @Column(length = 500)
    private String portada;

    // ==== Progreso personal ====

    @Column(name = "horasJugadas")
    private Integer horasJugadas;

    @Column(precision = 3, scale = 1)
    private BigDecimal puntaje;

    @Enumerated(EnumType.STRING)
    @Column(name = "estadoTermino", length = 50)
    private EstadoTermino estadoTermino;

    @Column(length = 1000)
    private String notas;

    // ==== Relación con Etiquetas ====

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "juego_etiqueta",
            joinColumns = @JoinColumn(name = "juegoId"),
            inverseJoinColumns = @JoinColumn(name = "etiquetaId")
    )
    private Set<Etiqueta> etiquetas = new HashSet<>();

    // ========= Constructores =========

    public Juego() {
    }

    public Juego(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    // ========= Getters / Setters =========

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

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }
}