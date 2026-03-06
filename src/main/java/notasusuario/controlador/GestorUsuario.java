package notasusuario.controlador;

import notasusuario.excepciones.UsuarioNoEncontradoException;
import notasusuario.modelo.Usuario;
import notasusuario.archivos.Parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.nio.file.Files;


public class GestorUsuario {

    static Path rutaData = Path.of("..", "data");


    private static boolean verificarUsuario(String email, String contrtasenya, String linea){
        String[] lineaDividida = Parser.parseLinea(linea);
        if (lineaDividida.length != 3){
            throw new IllegalArgumentException("La línea: " + linea + ", es defectuosa");
        }else {
            return email.equals(lineaDividida[1]) && contrtasenya.equals(lineaDividida[2]);
        }

    }

    private static boolean verificarUsuario(Usuario usuario, String linea){
        String[] lineaDividida = Parser.parseLinea(linea);
        if (lineaDividida.length != 3){
            throw new IllegalArgumentException("La línea: " + linea + ", es defectuosa");
        }else {
            return usuario.getEmail().equals(lineaDividida[1]);
        }

    }


}
