package notasusuario.excepciones;

import java.util.Scanner;

public class UsuarioNoEncontradoException extends Exception {
    public UsuarioNoEncontradoException() {
        super("Usuario no encontrado");
    }


}
