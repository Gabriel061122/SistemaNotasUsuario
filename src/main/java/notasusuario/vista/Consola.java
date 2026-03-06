package notasusuario.vista;

import notasusuario.controlador.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Consola {

    public static void mostrarLineas(List<String> lineas){
        for (int i = 0; i < lineas.size(); i++) {
            System.out.println(i+1 + " - " + lineas.get(i));
        }
    }

    public static void mostrarNotas(Path rutaUsuario){
        Path rutaRegistry = rutaUsuario.resolve("registry.txt");
        mostrarLineas(Parser.lineaALinea(rutaRegistry));
    }
}
