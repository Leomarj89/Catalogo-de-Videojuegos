package com.leomar.videojuegos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "usuarios")
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

    @Column(length = 100)
    private String plataforma;

    // En la BD es nvarchar(max)
    private String descripcion;

    @Column(length = 500)
    private String portada;

    // Un juego puede tener muchos "usuarios" (registros de juego)
    @OneToMany(
            mappedBy = "juego",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Set<Usuario> usuarios = new HashSet<>();
}