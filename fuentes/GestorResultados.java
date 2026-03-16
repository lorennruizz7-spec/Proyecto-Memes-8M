/**
 * Descripción: Este fichero forma parte del proyecto Memes-8M.
 * Gestiona el almacenamiento y consulta de las mejores puntuaciones del juego.
 *
 * Se encarga de:
 * - Crear el directorio de resultados si no existe.
 * - Crear el archivo donde se guardan las puntuaciones.
 * - Guardar nuevas puntuaciones.
 * - Consultar el Top 3 de mejores resultados.
 * - Comprobar si una puntuación entra en el Top 3.
 *
 * @author Carlos Soriano
 */

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar el directorio y el fichero donde
 * se almacenan las mejores puntuaciones del juego.
 */
public class GestorResultados {

    /** Ruta del directorio donde se guardan los resultados. */
    private static final Path DIRECTORIO = Paths.get("resultados");

    /** Ruta del archivo donde se almacenan las mejores puntuaciones. */
    private static final Path ARCHIVO_MEJORES =
            Paths.get("resultados" + File.separator + "mejores.txt");

    /**
     * Comprueba si existen el directorio "resultados" y el fichero
     * "mejores.txt". Si no existen, los crea automáticamente.
     */
    public static void comprobarArchivoResultados() {

        try {

            if (!Files.exists(DIRECTORIO)) {
                Files.createDirectories(DIRECTORIO);
            }

            if (!Files.exists(ARCHIVO_MEJORES)) {
                Files.createFile(ARCHIVO_MEJORES);
                System.out.println("Archivo de resultados creado.");
            }

        } catch (IOException e) {
            System.out.println("Error creando el archivo de resultados.");
        }
    }

    /**
     * Guarda un nuevo resultado en el archivo de mejores puntuaciones.
     * Cada resultado se guarda en una línea con el formato:
     *
     * nombre puntuacion
     *
     * @param nombre nombre del jugador
     * @param puntuacion puntuación obtenida por el jugador
     */
    public static void guardarResultado(String nombre, int puntuacion) {

        try {
            List<String> linea = List.of(nombre + " " + puntuacion);
            Files.write(ARCHIVO_MEJORES, linea, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        } catch (IOException e) {
            System.out.println("Error al guardar el resultado: " + e.getMessage());
        }
    }

    /**
     * Lee el archivo de mejores puntuaciones, ordena los resultados
     * de mayor a menor y devuelve los tres mejores.
     *
     * @return lista con hasta 3 elementos. Cada elemento es un array
     *         de String donde:
     *         <ul>
     *           <li>posición 0: nombre del jugador</li>
     *           <li>posición 1: puntuación obtenida</li>
     *         </ul>
     */
    public static List<String[]> obtenerTop3() {

        List<String[]> resultados = new ArrayList<>();

        try {

            List<String> lineas = Files.readAllLines(ARCHIVO_MEJORES);

            for (String linea : lineas) {

                linea = linea.trim();

                if (!linea.isEmpty()) {

                    String[] partes = linea.split(" ");

                    if (partes.length == 2) {
                        resultados.add(partes);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer los resultados: " + e.getMessage());
        }

        // Ordenar de mayor a menor puntuación
        resultados.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

        List<String[]> top3 = new ArrayList<>();

        for (int i = 0; i < resultados.size() && i < 3; i++) {
            top3.add(resultados.get(i));
        }

        return top3;
    }

    /**
     * Comprueba si una puntuación puede entrar en el Top 3 actual.
     *
     * Si todavía hay menos de tres resultados guardados,
     * la puntuación entra automáticamente.
     *
     * @param puntuacion puntuación obtenida por el jugador
     * @return true si la puntuación entra en el Top 3,
     *         false si no supera ninguna de las existentes
     */
    public static boolean estaEnTop3(int puntuacion) {

        List<String[]> top3 = obtenerTop3();

        if (top3.size() < 3) {
            return true;
        }

        int peor = Integer.parseInt(top3.get(2)[1]);

        return puntuacion > peor;
    }
}