package com.leomar.videojuegos.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "etiqueta")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etiquetaId")
    private Integer id;

    @Column(name = "nombreEtiqueta", length = 100, nullable = false, unique = true)
    private String nombreEtiqueta;

    @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.LAZY)
    private Set<Juego> juegos = new HashSet<>();

    public Etiqueta() {
    }

    public Etiqueta(Integer id, String nombreEtiqueta) {
        this.id = id;
        this.nombreEtiqueta = nombreEtiqueta;
    }

    // ========= Getters / Setters =========

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreEtiqueta() {
        return nombreEtiqueta;
    }

    public void setNombreEtiqueta(String nombreEtiqueta) {
        this.nombreEtiqueta = nombreEtiqueta;
    }

    public Set<Juego> getJuegos() {
        return juegos;
    }

    public void setJuegos(Set<Juego> juegos) {
        this.juegos = juegos;
    }
}