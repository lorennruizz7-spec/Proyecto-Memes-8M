/**
 * Fichero: GestorResultados.java
 * Descripción: Este fichero forma parte del proyecto Memes-8M.
 * Contiene la clase GestorResultados.java.
 * Corresponde a la HU2 del proyecto.
 * @autor: Carlos Soriano
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Clase: GestorResultados
 * Descripción:Se encarga de gestionar el directorio y el fichero donde se guardaran las mejores puntuaciones.
 * Comprueba si existe el directorio "resultados" y el fichero "mejores.txt". Sino existen, los crea para que el programa pueda seguir funcionando.
 */

public class GestorResultados {

     public static void comprobarArchivoResultados() {

        // Construimos la ruta del directorio con Paths.get()
        Path directorio = Paths.get("resultados");

        // Construimos la ruta del fichero con Paths.get() y File.separator
        Path archivoResultados = Paths.get("resultados" + File.separator + "mejores.txt");

        try {
            // Existe el dir?
            if (!Files.exists(directorio)) {
                // No existe: lo creamos 
                Files.createDirectories(directorio);
            }

            
            if (!Files.exists(archivoResultados)) {
                // No existe: lo creamos vacio con createFile()
                Files.createFile(archivoResultados);
                System.out.println("Archivo de resultados creado.");
            }

        } catch (IOException e) {
            // Capturamos cualquier error de entrada/salida 
            System.out.println("Error creando el archivo de resultados.");
        }
    }

    
    public static void main(String[] args) {
    comprobarArchivoResultados();
}


}