package notasusuario.controlador;

import notasusuario.modelo.Usuario;

public class Parser {

    public static String[] parseLinea(String linea){
        return linea.split(";");
    }
}
