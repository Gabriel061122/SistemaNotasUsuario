package notasusuario.controlador;

import notasusuario.modelo.Usuario;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Parser {

    public static String[] parseLinea(String linea){
        return linea.split(";");
    }

    public static ArrayList<String> lineaALinea(Path archivo){
        ArrayList<String> lista = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(archivo)){
            for (String linea = br.readLine(); br.readLine() != null; ) {
                lista.add(linea);
            }
            return lista;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }
}
