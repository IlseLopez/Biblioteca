package com.curso.libros.catalogo;

import com.curso.libros.catalogo.Model.DatosLibro;
import com.curso.libros.catalogo.Model.DatosResultado;
import com.curso.libros.catalogo.principal.Principal;
import com.curso.libros.catalogo.repository.LibroRepository;
import com.curso.libros.catalogo.service.ConsumoAPI;
import com.curso.libros.catalogo.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoApplication implements CommandLineRunner {

	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogoApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.mostrarMenu();
	}
}


/*
 *
 * var consumoapi = new ConsumoAPI();
 * 		var json = consumoapi.obtenerDatos("https://openlibrary.org/search.json?title=alice");
 * 		ConvierteDatos conversor = new ConvierteDatos();
 * 		var datos = conversor.obtenerDatos(json, DatosResultado.class);
 * 		datos.libros().stream()
 * 				.limit(5)
 * 				.forEach(libro -> {
 * 					System.out.println("Título: " + libro.titulo());
 * 					System.out.println("Autor(es): " + libro.autor());
 * 					System.out.println("Publicado: " + libro.primeraPublicacion());
 * 					System.out.println("Idiomas: " + libro.idiomas());
 * 					System.out.println("-----------------------");
 * 				                });
 *
 *
 */

