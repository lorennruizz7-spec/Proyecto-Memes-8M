public class Meme {
/**
 * Representa un meme o bulo que el jugador debe desmentir.
 */
    private int id;
    private String texto;

    public Meme(int id, String texto){
        this.id = id;
        this.texto = texto;
    }

    public int getId(){
        return id;
    }

    public String getTexto(){
        return texto;
    }

    public String toString(){
        return id + " - " + texto;
    }
}