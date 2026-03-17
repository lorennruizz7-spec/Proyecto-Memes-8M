import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class JuegoTest {

    private List<Meme> memes() {
        return List.of(
            new Meme(1, "Meme 1"),
            new Meme(2, "Meme 2"),
            new Meme(3, "Meme 3"),
            new Meme(4, "Meme 4"),
            new Meme(5, "Meme 5")
        );
    }

    private List<Realidad> realidades() {
        return List.of(
            new Realidad(1, "Realidad 1", "https://fuente1.com"),
            new Realidad(2, "Realidad 2", "https://fuente2.com"),
            new Realidad(3, "Realidad 3", "https://fuente3.com"),
            new Realidad(4, "Realidad 4", "https://fuente4.com"),
            new Realidad(5, "Realidad 5", "https://fuente5.com")
        );
    }

    @Test
    public void testPuntuacionInicialEsCero() {
        Juego juego = new Juego(memes(), realidades());
        assertEquals(0, juego.getPuntuacion());
    }

    @Test
    public void testBuscarRealidadPorIdExistente() {
        Juego juego = new Juego(memes(), realidades());
        Realidad resultado = juego.buscarRealidadPorId(1);
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
    }

    @Test
    public void testBuscarRealidadPorIdNoExistente() {
        Juego juego = new Juego(memes(), realidades());
        Realidad resultado = juego.buscarRealidadPorId(99);
        assertNull(resultado);
    }

    @Test
    public void testPedirEleccionValida() {
        Juego juego = new Juego(memes(), realidades());
        Scanner scanner = new Scanner("3\n");
        int eleccion = juego.pedirEleccion(scanner, 5);
        assertEquals(3, eleccion);
    }

    @Test
    public void testPedirEleccionEntradaInvalidaLuegoValida() {
        Juego juego = new Juego(memes(), realidades());
        // Primero introduce una entrada invalida, luego una valida
        Scanner scanner = new Scanner("abc\n2\n");
        int eleccion = juego.pedirEleccion(scanner, 5);
        assertEquals(2, eleccion);
    }
}
