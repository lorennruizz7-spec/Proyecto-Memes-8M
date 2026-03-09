import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class LectorMemes {

    public static List<Meme> leerMemes(String ruta){

        List<Meme> memes = new ArrayList<>();

        try{

            List<String> lineas = Files.readAllLines(Paths.get(ruta));

            for(String linea : lineas){

                String[] partes = linea.split(";");

                int id = Integer.parseInt(partes[0]);
                String texto = partes[1];

                memes.add(new Meme(id, texto));
            }

        }catch(IOException e){
            System.out.println("Error leyendo memes.txt");
        }

        return memes;
    }
}