package com.curso.libros.catalogo.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("numFound") int numeroBusqueda,
        @JsonAlias("q") String titulo
) {
}
