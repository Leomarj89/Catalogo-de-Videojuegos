package com.leomar.videojuegos.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"juego", "etiquetas"})
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuarioId")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "juegoId", nullable = false)
    private Juego juego;

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    private Date fechaCompletado;

    private Integer horasJugadas;

    @Column(precision = 3, scale = 1)
    private Double puntaje;

    @Column(name = "estadoTermino", length = 50)
    private String estadoTermino;

    // nvarchar(max) en la BD
    private String notas;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_etiqueta",
            joinColumns = @JoinColumn(name = "usuarioId"),
            inverseJoinColumns = @JoinColumn(name = "etiquetaId")
    )
    private Set<Etiqueta> etiquetas = new HashSet<>();
}