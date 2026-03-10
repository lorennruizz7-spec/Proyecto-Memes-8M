import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**


Clase encargada de leer el fichero de memes (memes.txt)
 * y convertir cada línea del archivo en un objeto de tipo Meme.
 */
public class LectorMemes {

    /**
     * Este método recibe la ruta del fichero de memes
     * y devuelve una lista con todos los memes encontrados.
     * @param recibe una lista de memes
     * @return recibe una ruta
     */
    public static List<Meme> leerMemes(String ruta){

        List<Meme> memes = new ArrayList<>();

        try{

            // Leemos todas las líneas del fichero
            // Cada línea corresponde a un meme
            List<String> lineas = Files.readAllLines(Paths.get(ruta));

            // Recorremos cada línea del archivo
            for(String linea : lineas){

                // Separamos los datos usando ";" como separador
                String[] partes = linea.split(";");

                int id = Integer.parseInt(partes[0]);
                String texto = partes[1];

                // Creamos un objeto Meme con los datos obtenidos
                Meme meme = new Meme(id, texto);

                // Añadimos el meme a la lista
                memes.add(meme);
            }

        }catch(IOException e){

            System.out.println("Error leyendo el fichero de memes.");
        }

        // Devolvemos la lista con todos los memes cargados
        return memes;
    }
}                                                                                                                                                                         