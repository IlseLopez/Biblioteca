package com.curso.libros.catalogo.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("author_name") List<String> autor,
        @JsonAlias("first_publish_year") Integer primeraPublicacion,
        @JsonAlias("language") List<String> idiomas

) {
}
