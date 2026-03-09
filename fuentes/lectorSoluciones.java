import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.File;
import java.util.*;

public class LectorSoluciones {

    public static List<Solucion> leerSoluciones(String ruta){

        List<Solucion> soluciones = new ArrayList<>();

        try{

            File archivo = new File(ruta);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(archivo);

            NodeList lista = doc.getElementsByTagName("solucion");

            for(int i=0;i<lista.getLength();i++){

                Element elemento = (Element) lista.item(i);

                int memeId = Integer.parseInt(
                        elemento.getElementsByTagName("meme")
                        .item(0).getTextContent());

                int realidadId = Integer.parseInt(
                        elemento.getElementsByTagName("realidad")
                        .item(0).getTextContent());

                soluciones.add(new Solucion(memeId,realidadId));
            }

        }catch(Exception e){
            System.out.println("Error leyendo soluciones.xml");
        }

        return soluciones;
    }
}