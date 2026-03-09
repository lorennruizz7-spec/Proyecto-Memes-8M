import java.nio.file.*;
import java.util.*;
import org.json.*;

public class LectorRealidades {

    public static List<Realidad> leerRealidades(String ruta){

        List<Realidad> realidades = new ArrayList<>();

        try{

            String contenido = Files.readString(Paths.get(ruta));

            JSONArray array = new JSONArray(contenido);

            for(int i=0;i<array.length();i++){

                JSONObject obj = array.getJSONObject(i);

                int id = obj.getInt("id");
                String texto = obj.getString("texto");
                String fuente = obj.getString("fuente");

                realidades.add(new Realidad(id,texto,fuente));
            }

        }catch(Exception e){
            System.out.println("Error leyendo realidades.json");
        }

        return realidades;
    }
}