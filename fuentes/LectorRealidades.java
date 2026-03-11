/**
 * Descripción: Este fichero forma parte del proyecto Memes-8M.
 * 
 * @author: Carlos Soriano
 */

import org.json.JSONArray; //He tenido que añadir libreria externa para poder usar este import
import org.json.JSONObject; //Igual que el anterior, es parte de la misma libreria externa
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de leer el fichero de realidades (realidades.json)
 * y convertir cada entrada del archivo en un objeto de tipo Realidad.
 */
public class LectorRealidades {

    /**
     * Lee el fichero JSON de realidades y devuelve una lista de objetos Realidad.
     * @param ruta ruta del fichero JSON a leer
     * @return lista de objetos Realidad con los datos del fichero
     */
    public static List<Realidad> leerRealidades(String ruta) {
        try {
            // Leer el contenido del fichero JSON
            String contenido = new String(Files.readAllBytes(Paths.get(ruta)));
            JSONArray jsonArray = new JSONObject(contenido).getJSONArray("memes");

            // Crear una lista de objetos Realidad
            List<Realidad> realidades = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Realidad realidad = new Realidad(
                    jsonObject.getInt("id"),
                    jsonObject.getString("respuesta"),
                    jsonObject.getJSONArray("fuentes").getJSONObject(0).getString("enlace")
                );
                realidades.add(realidad);
            }

            // Devolver la lista de realidades
            return realidades;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}