import java.nio.file.*;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        // --- HU1: comprobar archivos de datos ---
        Path memesFile = Paths.get("datos/memes.txt");
        Path realidadesFile = Paths.get("datos/realidades.json");
        Path solucionesFile = Paths.get("datos/soluciones.xml");

        if(!Files.exists(memesFile) || !Files.exists(realidadesFile) || !Files.exists(solucionesFile)){
            System.out.println("Faltan archivos en el directorio datos.");
            return;
        }

        // --- HU2: comprobar archivo de resultados ---
        Path resultadosFile = Paths.get("resultados/mejores.txt");

        try{

            if(!Files.exists(resultadosFile)){
                Files.createDirectories(Paths.get("resultados"));
                Files.createFile(resultadosFile);
                System.out.println("Archivo de resultados creado.");
            }

        }catch(IOException e){
            System.out.println("Error creando el archivo de resultados.");
        }

        // --- HU3: leer memes ---
        List<Meme> memes = LectorMemes.leerMemes("datos/memes.txt");

        // --- HU4: leer realidades ---
        List<Realidad> realidades =
                LectorRealidades.leerRealidades("datos/realidades.json");

        // --- leer soluciones ---
        List<Solucion> soluciones =
                LectorSoluciones.leerSoluciones("datos/soluciones.xml");


        // --- Mostrar datos cargados (solo para probar que funciona) ---
        System.out.println("MEMES CARGADOS:");
        for(Meme m : memes){
            System.out.println(m);
        }

        System.out.println("\nREALIDADES CARGADAS:");
        for(Realidad r : realidades){
            System.out.println(r);
        }

        System.out.println("\nSOLUCIONES CARGADAS:");
        for(Solucion s : soluciones){
            System.out.println(s);
        }

        System.out.println("\nSistema iniciado correctamente.");

    }
}