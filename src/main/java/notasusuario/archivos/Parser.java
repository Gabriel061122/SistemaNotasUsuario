package notasusuario.archivos;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Parser {

    public static String[] parseLinea(String linea){
        return linea.split(";");
    }

    public static ArrayList<String> lineaALinea(Path archivo) throws IOException{
        ArrayList<String> lista = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(archivo)){
            for (String linea = br.readLine(); br.readLine() != null; ) {
                lista.add(linea);
            }
            return lista;
        }catch(IOException e){
            throw new IOException(e);
        }
    }
}
