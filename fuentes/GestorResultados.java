/**
 * Descripción: Este fichero forma parte del proyecto Memes-8M.
 * 
 * @author: Carlos Soriano
 */

import java.io.BufferedReader;       // ← NUEVO
import java.io.BufferedWriter;       // ← NUEVO
import java.io.File;
import java.io.FileReader;           // ← NUEVO
import java.io.FileWriter;           // ← NUEVO
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;          // ← NUEVO
import java.util.List;               // ← NUEVO

/**
 * Corta: Se encarga de gestionar el directorio y el fichero donde se guardaran las mejores puntuaciones.
 * 
 * Larga: Comprueba si existe el directorio "resultados" y el fichero "mejores.txt". Si no existen,
 * los crea. También permite guardar resultados nuevos y consultar el Top 3.
 */
public class GestorResultados {

    // ← NUEVO: constantes de ruta centralizadas
    private static final Path DIRECTORIO      = Paths.get("resultados");
    private static final Path ARCHIVO_MEJORES = Paths.get("resultados" + File.separator + "mejores.txt");

    /**
     * Comprueba si existe el directorio "resultados" y el fichero "mejores.txt".
     * Si no existen, los crea vacios.
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

    // ================== NUEVO: HU9 ==================

    /**
     * HU9 - Guarda un nuevo resultado al final de mejores.txt.
     * Formato de cada linea: "nombre puntuacion"
     *
     * @param nombre     Nombre del jugador.
     * @param puntuacion Puntuacion obtenida (0-5).
     */
    public static void guardarResultado(String nombre, int puntuacion) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_MEJORES.toFile(), true))) {
            bw.write(nombre + " " + puntuacion);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar el resultado: " + e.getMessage());
        }
    }

    /**
     * HU9 - Lee mejores.txt, ordena por puntuacion y devuelve los 3 mejores.
     *
     * @return Lista con hasta 3 elementos, cada uno es un String[] {nombre, puntuacion}.
     */
    public static List<String[]> obtenerTop3() {
        List<String[]> resultados = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO_MEJORES.toFile()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
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

        // Ordenar de mayor a menor puntuacion
        resultados.sort((a, b) -> Integer.parseInt(b[1]) - Integer.parseInt(a[1]));

        // Devolver solo los 3 primeros
        return resultados.subList(0, Math.min(3, resultados.size()));
    }

    /**
     * HU9 - Comprueba si una puntuacion merece estar en el Top 3.
     * Devuelve true si hay menos de 3 resultados guardados,
     * o si la puntuacion supera al peor resultado del Top 3 actual.
     *
     * @param puntuacion Puntuacion obtenida por el jugador.
     * @return true si entra en el Top 3, false si no.
     */
    public static boolean estaEnTop3(int puntuacion) {
        List<String[]> top3 = obtenerTop3();

        // Si hay menos de 3 resultados, siempre entra
        if (top3.size() < 3) return true;

        // Si hay 3 o mas, entra solo si supera al peor (el ultimo del top3)
        int peor = Integer.parseInt(top3.get(2)[1]);
        return puntuacion > peor;
    }

    // ================== FIN NUEVO ==================
}