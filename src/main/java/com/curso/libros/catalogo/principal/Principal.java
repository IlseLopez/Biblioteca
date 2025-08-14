package com.curso.libros.catalogo.principal;
import com.curso.libros.catalogo.Model.DatosLibro;
import com.curso.libros.catalogo.Model.DatosResultado;

import com.curso.libros.catalogo.Model.Libro;
import com.curso.libros.catalogo.repository.LibroRepository;
import com.curso.libros.catalogo.service.ConsumoAPI;
import com.curso.libros.catalogo.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE ="https://openlibrary.org/search.json?title=";
    private ConvierteDatos conversor = new ConvierteDatos();
    List<DatosResultado> datosLibros = new ArrayList<>();
    private LibroRepository repository;


    public Principal(LibroRepository repository) {
        this.repository = repository;
    }


    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0){
            var menu = """
                    1.- BUSCAR LIBRO
                    2.- Mostrar libros buscados
                    3.- Lista Autores
                    4.- Lista Autores de Libros publicados por año
                    0.- SALIR
                    """;

            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscaLibro();
                    break;
                case 2:
                    librosBuscados();
                    break;
                case 3: listaAutores();
                    break;
                case 4: listaAutoresAno();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación....");
                    break;
            }
        }

    }



    private DatosResultado buscarLibro() {
        System.out.println("¿Nombre del libro que estas buscando?");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+nombreLibro.replace(" ","+"));
        //System.out.println(json);
        DatosResultado datosLibro = conversor.obtenerDatos(json, DatosResultado.class);
        //System.out.println(datosLibro);
        datosLibro.libros().stream()
                .limit(1)
                .forEach(libro -> {
 					System.out.println("Título: " + libro.titulo());
  					System.out.println("Autor(es): " + libro.autor());
  					System.out.println("Publicado: " + libro.primeraPublicacion());
  					System.out.println("Idiomas: " + libro.idiomas());
  					System.out.println("-----------------------");
  				                });
        return datosLibro;

    }

    private void buscaLibro(){
        /*
        DatosResultado datosLibro = buscarLibro();
        Libro libro = new Libro(datosLibro);
        repository.save(libro);
        //datosLibros.add(datosLibro);
        System.out.println(datosLibro);*/

        DatosResultado datosResultado = buscarLibro();

        datosResultado.libros().stream()
                .limit(2) // tomamos solo 3
                .map(Libro::new) // convertir DatosLibro → Libro usando tu constructor
                .forEach(repository::save); // guardar cada uno


    }

    private void librosBuscados() {
        List<Libro>datosLibros = repository.findAll();
        System.out.println(datosLibros);


        /*
        if (datosLibros.isEmpty()) {
            System.out.println("Aún no has buscado ningún libro.");
            return;
        }

        System.out.println("Libros buscados previamente:");
        for (DatosResultado resultado : datosLibros) {
            resultado.libros().stream()
                    .limit(1)
                    .forEach(libro -> {
                        System.out.println("Título: " + libro.titulo());
                        System.out.println("Autor(es): " + libro.autor());
                        System.out.println("Publicado: " + libro.primeraPublicacion());
                        System.out.println("Idioma: " +
                                (libro.idiomas() != null && !libro.idiomas().isEmpty()
                                        ? libro.idiomas().get(0)
                                        : "No especificado")
                        );
                        System.out.println("-----------------------");
                    });
        }*/
    }

    private void listaAutores() {
        List<String> autores = new ArrayList<>();

        for (DatosResultado resultado : datosLibros) {
            resultado.libros().stream()
                    .limit(5) // opcional: solo los primeros 5 libros de cada búsqueda
                    .forEach(libro -> {
                        if (libro.autor() != null && !libro.autor().isEmpty()) {
                            autores.add(libro.autor().get(0)); // primer autor
                        }
                    });
        }

        System.out.println("Autores encontrados:");
        autores.forEach(System.out::println);


    }

    private void listaAutoresAno() {
        System.out.println("Ingrese el año que le interesa saber qué autor publicó en ese año:");
        int anio = teclado.nextInt();
        teclado.nextLine(); // limpiar buffer

        List<String> autoresAno = new ArrayList<>();

        for (DatosResultado resultado : datosLibros) {
            resultado.libros().stream()
                    .filter(libro -> libro.primeraPublicacion() != null && libro.primeraPublicacion() == anio)
                    .forEach(libro -> {
                        if (libro.autor() != null && !libro.autor().isEmpty()) {
                            autoresAno.add(libro.autor().get(0)); // primer autor
                        }
                    });
        }

        if (autoresAno.isEmpty()) {
            System.out.println("No se encontraron autores que publicaran en " + anio);
        } else {
            System.out.println("Autores que publicaron en " + anio + ":");
            autoresAno.forEach(System.out::println);
        }

    }





}

