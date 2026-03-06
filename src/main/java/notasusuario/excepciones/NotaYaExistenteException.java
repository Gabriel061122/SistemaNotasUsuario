package notasusuario.excepciones;

import java.util.Scanner;

public class NotaYaExistenteException extends Exception {
    public NotaYaExistenteException(String nota) {
        super("La nota '"+nota+"' que desea crear ya existe");
    }

    public boolean getConfirmacion(Scanner sc){
        System.out.print("Desea crear otro archivo? Responda afirmativamente con 1. Cualquier otro caracter se considerará una negación");
        try{
            sc.nextLine();
            int confirmacion = Integer.parseInt(sc.nextLine());
            return confirmacion == 1;
        }
        catch(Exception e){
            return false;
        }
    }
}
