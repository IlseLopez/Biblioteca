package com.curso.libros.catalogo.Model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private Integer primeraPublicacion;

    @Column(name = "idioma")
    private String idiomas;

    @ManyToMany
    @JoinTable(
            name = "libros_autores",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores;




    public Libro() {}

    public Libro(String titulo, Integer primeraPublicacion, String idiomas) {
        this.titulo = titulo;
        this.primeraPublicacion = primeraPublicacion;
        this.idiomas = idiomas;
    }

    public Libro(DatosResultado datosLibro) {
    }
    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.primeraPublicacion = datos.primeraPublicacion();
        this.idiomas = (datos.idiomas() != null && !datos.idiomas().isEmpty())
                ? datos.idiomas().get(0)
                : null;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getPrimeraPublicacion() {
        return primeraPublicacion;
    }

    public void setPrimeraPublicacion(Integer primeraPublicacion) {
        this.primeraPublicacion = primeraPublicacion;
    }
    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", primeraPublicacion=" + primeraPublicacion +
                ", idioma='" + idiomas + '\'' +
                '}';
    }



}
