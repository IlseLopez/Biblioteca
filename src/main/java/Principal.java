import com.curso.libros.catalogo.Model.DatosLibro;
import com.curso.libros.catalogo.service.ConsumoAPI;
import com.curso.libros.catalogo.service.ConvierteDatos;

import java.util.Scanner;

public class Principal {

    private Scanner sc = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://openlibrary.org/search.json?q=";
    private ConvierteDatos convertir = new ConvierteDatos();

    public void mostrarMenu(){
        System.out.println("Escribe el nombre del libro que buscas");
        var nombreLibro = sc.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        var datos = convertir.obtenerDatos(json, DatosLibro.class);
        System.out.println(datos);

    }

    private DatosLibro getDatosLibro() {
        System.out.println("Escribe el nombre del libro que buscas");
        var nombreLibro = sc.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));
        System.out.println(json);

        DatosLibro datos = convertir.obtenerDatos(json, DatosLibro.class);
        return datos;

    }




}
