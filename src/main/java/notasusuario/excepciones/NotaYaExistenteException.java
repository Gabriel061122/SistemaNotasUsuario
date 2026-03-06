package notasusuario.excepciones;

import java.util.Scanner;

public class NotaYaExistenteException extends Exception {
    public NotaYaExistenteException(String nota) {
        super("La nota '"+nota+"' que desea crear ya existe");
    }

    public boolean getConfirmacion(int i){
        System.out.print("Desea crear otro archivo? Responda afirmativamente con 1 para afirmar, 2 para negar.");
        return i == 1;
    }
}
