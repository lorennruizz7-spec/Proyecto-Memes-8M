package com.iescastelar;
/**
 * Corta: Gestiona y verifica la existencia de archivos de datos del sistema.
 *
 * Larga: Encapsula la lógica de comprobación de los tres archivos necesarios
 * para el funcionamiento de la aplicación ubicados en el directorio.
 *
 * @author Jesús Gutiérrez
 */
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GestorDirectorio {

    //Ruta a los archivos que corresponde con cada variable.
    private final Path memesFile = Paths.get("datos/memes.txt");
    private final Path realidadesFile = Paths.get("datos/realidades.json");
    private final Path solucionesFile = Paths.get("datos/soluciones.xml");

    /**
     * Creamos un comprobador que nos dirá si existe el archivo en la ruta.
     * @return true/false en caso de que exista o no
    */
    public boolean comprobarMemes() {
        return comprobarArchivo(memesFile, "memes.txt");
    }

    public boolean comprobarRealidades() {
        return comprobarArchivo(realidadesFile, "realidades.json");
    }

    public boolean comprobarSoluciones() {
        return comprobarArchivo(solucionesFile, "soluciones.xml");
    }

    /**
     * Comprueba la existencia de todos los archivos necesarios para la aplicación.
     * Si alguno de ellos no existe, muestra un mensaje de error por consola.
     *
     * @return true si todos los archivos están presentes, false si falta alguno.
     */
    public boolean comprobarTodos() {
        boolean memesOk = comprobarMemes();
        boolean realidadesOk = comprobarRealidades();
        boolean solucionesOk = comprobarSoluciones();

        if (!memesOk || !realidadesOk || !solucionesOk) {
            System.out.println("Faltan archivos en el directorio datos.");
            return false;
        }

        System.out.println("Todos los archivos están presentes.");
        return true;
    }
    /**
     * Método auxiliar que verifica si un archivo concreto existe en la ruta indicada.
     * Muestra un mensaje por consola indicando si fue encontrado o no.
     *
     * @param ruta con la ubicación del archivo a comprobar.
     * @param nombreArchivo Nombre del archivo para mostrarlo en los mensajes de consola.
     * @return true si el archivo existe, false en caso de lo contrario.
     */

    private boolean comprobarArchivo(Path ruta, String nombreArchivo) {
        if (!Files.exists(ruta)) {
            System.out.println("Archivo no encontrado: " + nombreArchivo);
            return false;
        }
        System.out.println("Archivo encontrado: " + nombreArchivo);
        return true;
    }
}