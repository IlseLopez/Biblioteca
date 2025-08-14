package com.curso.libros.catalogo.repository;

import com.curso.libros.catalogo.Model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {


}
