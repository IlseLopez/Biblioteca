package com.curso.libros.catalogo.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosResultado(

        @JsonAlias("docs") List<DatosLibro> libros

) {
}
