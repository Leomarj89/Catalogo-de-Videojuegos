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
@Table(name = "etiqueta")
public class Etiqueta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etiquetaId")
    private Integer id;

    @Column(name = "nombreEtiqueta", length = 100, nullable = false, unique = true)
    private String nombreEtiqueta;

    @ManyToMany(mappedBy = "etiquetas", fetch = FetchType.LAZY)
    private Set<Usuario> usuarios = new HashSet<>();
}