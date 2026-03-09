import java.nio.file.*;
import java.util.*;
import org.json.*;
/**
 * Fichero de la clase LectorRealidades 
 * Hecho en clase para leer las realidades de un JSON
 * 
 * @author: Jesús Gutiérrez Rucha 
 */

public class LectorRealidades {
/**
 * Corta: Este métedo lee un  archivo JSON y lo convierte en una lista de objetos
 * Larga: Este método recibe un archivo JSON, lee el archivo e indentifica el id, texto y fuente del JSON, recibe los valores de acada variable definida en el JSON
 * los añade a la lista y los muestra por pantalla. Al final hay un catch en caso de error
 * Parámetros: 
 * @param ruta Recibe la ruta del archivo JSON que leerá
 * 
 * @return Lista de objetos parseadas del archivo 
 */
    public static List<Realidad> leerRealidades(String ruta){

        List<Realidad> realidades = new ArrayList<>();

        try{

            String contenido = Files.readString(Paths.get(ruta));

            JSONArray array = new JSONArray(contenido);

            for(int i=0;i<array.length();i++){

                JSONObject objeto = array.getJSONObject(i);

                int id = objeto.getInt("id");
                String texto = objeto.getString("texto");
                String fuente = objeto.getString("fuente");

                realidades.add(new Realidad(id,texto,fuente));
            }

        }catch(Exception e){
            System.out.println("Error leyendo realidades.json");
        }

        return realidades;
    }
}