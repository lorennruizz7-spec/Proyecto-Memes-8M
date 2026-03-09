public class Soluciones {

    private int memeId;
    private int realidadId;

    public Solucion(int memeId, int realidadId){
        this.memeId = memeId;
        this.realidadId = realidadId;
    }

    public int getMemeId(){
        return memeId;
    }

    public int getRealidadId(){
        return realidadId;
    }

    public String toString(){
        return "Meme " + memeId + " -> Realidad " + realidadId;
    }
}