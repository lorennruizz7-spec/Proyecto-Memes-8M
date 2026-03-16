import java.util.*;
 
/**
 * Punto de entrada del programa.
 *
 * Se encarga de preparar los datos necesarios y lanzar el juego.
 *
 * @author Jesús Gutiérrez Rucha
 * @author Carlos Soriano Pegado
 * @author Lorena Ruiz Roblas
 */
public class Main {
 
    private static final String RUTA_MEMES = "datos/memes.txt";
    private static final String RUTA_REALIDADES = "datos/realidades.json";
 
    private List<Meme> memes;
    private List<Realidad> realidades;
 
    public static void main(String[] args) {
        new Main().ejecutar();
    }
 
    /**
     * Prepara los datos y lanza el juego.
     *
     * @return true si el programa arranco y termino correctamente, false si hubo error.
     */
    public boolean ejecutar() {
 
        // HU1 - Comprueba que existen los tres ficheros de datos necesarios.
        // Si alguno falta, informa al usuario y detiene el programa.
        GestorDirectorio gestorDir = new GestorDirectorio();
        if (!gestorDir.comprobarTodos()) {
            System.out.println("El programa no puede continuar: faltan archivos de datos.");
            return false;
        }
 
        // HU2 - Comprueba si existe el directorio resultados y el fichero resultados.txt.
        // Si no existen, los crea vacios.
        GestorResultados.comprobarArchivoResultados();
 
        // HU3 - Lee el fichero memes.txt y genera la lista de objetos Meme.
        memes = LectorMemes.leerMemes(RUTA_MEMES);
 
        // HU4 - Lee el fichero realidades.json y genera la lista de objetos Realidad.
        realidades = LectorRealidades.leerRealidades(RUTA_REALIDADES);
 
        if (memes == null || memes.isEmpty() || realidades == null || realidades.isEmpty()) {
            System.out.println("No se han podido cargar los datos.");
            return false;
        }
 
        // HU5, HU6, HU7, HU8 - Lanza el juego con los datos cargados.
        Scanner scanner = new Scanner(System.in);
        new Juego(memes, realidades).jugar(scanner);
        scanner.close();
 
        return true;
    }
 
    public List<Meme> getMemes() {
         return memes; 
        }
    public List<Realidad> getRealidades() { 
        return realidades; 
    }
}