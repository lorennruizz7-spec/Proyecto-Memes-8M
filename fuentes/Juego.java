import java.util.*;
 
/**
 * Clase que encapsula toda la logica del juego Memes-8M.
 *
 * Implementa las siguientes historias de usuario:
 *
 *   HU5 - Muestra un meme elegido al azar (sin repeticion) y una lista
 *         numerada de realidades en orden aleatorio.
 *
 *   HU6 - El usuario elige el numero de la realidad que desmiente el meme.
 *         Si acierta, gana un punto.
 *
 *   HU7 - Muestra el marcador actualizado tras cada ronda y repite
 *         el proceso hasta completar 5 memes.
 *
 *   HU8 - Muestra la puntuacion final al terminar las 5 rondas.
 *
 * @author Jesús Gutiérrez Rucha
 */
public class Juego {
 
    private static final int RONDAS = 5;
 
    private final List<Meme> memes;
    private final List<Realidad> realidades;
    private int                  puntuacion = 0;
 
    /**
     * Constructor. Recibe las listas de memes y realidades ya cargadas.
     *
     * @param memes      Lista de memes disponibles para el juego.
     * @param realidades Lista de realidades disponibles como opciones de respuesta.
     */
    public Juego(List<Meme> memes, List<Realidad> realidades) {
        this.memes = memes;
        this.realidades = realidades;
    }
 
    // -------------------------------------------------------------------------
    // HU5 + HU6 + HU7 + HU8 - Logica del juego
    // -------------------------------------------------------------------------
 
    /**
     * Contiene el bucle principal del juego con las 5 rondas.
     * Cada ronda muestra un meme y sus opciones (HU5), recoge la respuesta del
     * usuario y puntua (HU6), y muestra el marcador (HU7).
     * Al salir del bucle muestra la puntuacion final (HU8).
     *
     * @param scanner Scanner para leer la entrada del usuario por consola.
     */
    public void jugar(Scanner scanner) {
 
        System.out.println("\n=== BIENVENIDO AL JUEGO MEMES 8M ===");
        System.out.println("Desmiente " + RONDAS + " memes eligiendo la realidad correcta.\n");
 
        // HU5 - Copiamos la lista de memes y la mezclamos para que cada partida
        // muestre los memes en orden distinto y sin repetirlos.
        List<Meme> memesSinRepetir = new ArrayList<>(memes);
        Collections.shuffle(memesSinRepetir);
 
        for (int ronda = 1; ronda <= RONDAS; ronda++) {
 
            System.out.println("--------------------------------------");
            System.out.println("Ronda " + ronda + " de " + RONDAS);
            System.out.println("--------------------------------------");
 
            // HU5 - Seleccionamos el meme de esta ronda (ya barajado, sin repeticion).
            Meme meme = memesSinRepetir.get(ronda - 1);
            System.out.println("\nMEME:\n  " + meme.getTexto() + "\n");
 
            // HU5 - Barajamos las realidades y las mostramos como lista numerada,
            // de modo que la respuesta correcta no este siempre en la misma posicion.
            List<Realidad> opcionesBarajadas = new ArrayList<>(realidades);
            Collections.shuffle(opcionesBarajadas);
 
            System.out.println("Cual de estas realidades desmiente el meme?");
            for (int i = 0; i < opcionesBarajadas.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + opcionesBarajadas.get(i).getTexto());
            }
 
            // HU6 - Pedimos al usuario que elija un numero de la lista.
            int eleccion = pedirEleccion(scanner, opcionesBarajadas.size());
            Realidad realidadElegida  = opcionesBarajadas.get(eleccion - 1);
            Realidad realidadCorrecta = buscarRealidadPorId(meme.getId());
 
            // HU6 - Comparamos la realidad elegida con la correcta segun soluciones.xml.
            // Si coinciden los ids, la respuesta es correcta y se suma un punto.
            boolean acierto = realidadCorrecta != null
                    && realidadElegida.getId() == realidadCorrecta.getId();
 
            if (acierto) {
                puntuacion++;
                System.out.println("\nCorrecto! +1 punto.");
            } else {
                System.out.println("\nIncorrecto.");
                if (realidadCorrecta != null) {
                    System.out.println("  La respuesta correcta era:\n  " + realidadCorrecta.getTexto());
                }
            }
 
            // HU7 - Mostramos el marcador actualizado al final de cada ronda.
            // El bucle continua hasta completar las 5 rondas.
            System.out.println("\nMarcador: " + puntuacion + " / " + ronda + "\n");
        }
 
        // HU8 - Al terminar las 5 rondas, mostramos la puntuacion final.
        mostrarPuntuacionFinal();
    }
 
    /**
     * HU6 - Pide al usuario un numero entre 1 y max por consola.
     * Repite la pregunta hasta recibir una entrada valida.
     *
     * @param scanner Scanner de entrada.
     * @param max     Numero maximo aceptado (tamanyo de la lista de opciones).
     * @return        Numero entero valido introducido por el usuario.
     */
    public int pedirEleccion(Scanner scanner, int max) {
        while (true) {
            System.out.print("\nTu eleccion (1-" + max + "): ");
            String linea = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(linea);
                if (valor >= 1 && valor <= max) return valor;
            } catch (NumberFormatException e) {
                // Entrada no numerica: se vuelve a pedir sin lanzar excepcion al usuario.
            }
            System.out.println("Por favor, introduce un numero entre 1 y " + max + ".");
        }
    }
 
    /**
     * HU8 - Muestra por consola la puntuacion final obtenida por el usuario
     * al completar las 5 rondas, junto con un mensaje segun el resultado.
     */
    public void mostrarPuntuacionFinal() {
        System.out.println("======================================");
        System.out.println("         PUNTUACION FINAL");
        System.out.println("======================================");
        System.out.println("  Has acertado " + puntuacion + " de " + RONDAS + " memes.");
 
        if (puntuacion == RONDAS) {
            System.out.println("  Perfecto! Conoces muy bien la realidad.");
        } else if (puntuacion >= 3) {
            System.out.println("  Bien hecho! Tienes buen ojo critico.");
        } else {
            System.out.println("  Sigue aprendiendo, la informacion es poder!");
        }
        System.out.println("======================================\n");
    }
 
    /**
     * HU6 - Busca en la lista de realidades aquella cuyo id coincide con el dado.
     * Permite encontrar la realidad correcta que desmiente cada meme.
     *
     * @param id Id de la realidad buscada (coincide con el id del meme segun soluciones.xml).
     * @return   El objeto Realidad correspondiente, o null si no existe.
     */
    public Realidad buscarRealidadPorId(int id) {
        for (Realidad real : realidades) {
            if (real.getId() == id) return real;
        }
        return null;
    }
 
    public int getPuntuacion() { return puntuacion; }
}