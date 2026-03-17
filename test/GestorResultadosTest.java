import com.iescastelar.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class GestorResultadosTest {

    @BeforeEach
    public void limpiarArchivo() throws Exception {
        // Limpiamos el archivo antes de cada test para partir de cero
        Files.createDirectories(Paths.get("resultados"));
        Files.writeString(Paths.get("resultados" + File.separator + "mejores.txt"), "");
    }

    @Test
    public void testComprobarArchivoResultados() {
        GestorResultados.comprobarArchivoResultados();
        assertTrue(Files.exists(Paths.get("resultados/mejores.txt")));
    }

    @Test
    public void testGuardarResultado() {
        GestorResultados.guardarResultado("Ana", 5);
        List<String[]> top3 = GestorResultados.obtenerTop3();
        assertEquals(1, top3.size());
        assertEquals("Ana", top3.get(0)[0]);
        assertEquals("5", top3.get(0)[1]);
    }

    @Test
    public void testObtenerTop3OrdenadoCorrectamente() {
        GestorResultados.guardarResultado("Ana", 3);
        GestorResultados.guardarResultado("Luis", 5);
        GestorResultados.guardarResultado("Maria", 4);
        List<String[]> top3 = GestorResultados.obtenerTop3();
        assertEquals("Luis", top3.get(0)[0]);
        assertEquals("Maria", top3.get(1)[0]);
        assertEquals("Ana", top3.get(2)[0]);
    }

    @Test
    public void testEstaEnTop3ConMenosDeTresResultados() {
        GestorResultados.guardarResultado("Ana", 3);
        assertTrue(GestorResultados.estaEnTop3(1));
    }

    @Test
    public void testEstaEnTop3SuperaElPeor() {
        GestorResultados.guardarResultado("Ana", 5);
        GestorResultados.guardarResultado("Luis", 4);
        GestorResultados.guardarResultado("Maria", 3);
        assertTrue(GestorResultados.estaEnTop3(4));
    }

    @Test
    public void testNoEstaEnTop3() {
        GestorResultados.guardarResultado("Ana", 5);
        GestorResultados.guardarResultado("Luis", 4);
        GestorResultados.guardarResultado("Maria", 3);
        assertFalse(GestorResultados.estaEnTop3(2));
    }
}
