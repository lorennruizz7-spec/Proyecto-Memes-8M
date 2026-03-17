import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GestorDirectorioTest {

    @Test
    public void testComprobarTodosArchivosExisten() {
        GestorDirectorio gestor = new GestorDirectorio();
        assertTrue(gestor.comprobarTodos());
    }

    @Test
    public void testComprobarMemes() {
        GestorDirectorio gestor = new GestorDirectorio();
        assertTrue(gestor.comprobarMemes());
    }

    @Test
    public void testComprobarRealidades() {
        GestorDirectorio gestor = new GestorDirectorio();
        assertTrue(gestor.comprobarRealidades());
    }

    @Test
    public void testComprobarSoluciones() {
        GestorDirectorio gestor = new GestorDirectorio();
        assertTrue(gestor.comprobarSoluciones());
    }
}
