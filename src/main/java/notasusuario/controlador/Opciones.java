package notasusuario.controlador;

import java.util.Scanner;

public class Opciones {
    public static int obtenerOpcion(int limite, Scanner sc) {
        while(true){
            try{
                int numero = sc.nextInt();
                if (numero < limite && numero > 0){
                    return numero;
                }
                System.out.println("\n Introduzca un número dentro del límite establecido, por favor");
            } catch (Exception e) {
                System.out.println("\n Introduzca un valor válido, por favor");
            }
        }
    }
}
