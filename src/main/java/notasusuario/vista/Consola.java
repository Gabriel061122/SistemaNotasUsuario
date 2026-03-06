package notasusuario.vista;

import notasusuario.modelo.Nota;
import notasusuario.modelo.Usuario;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Consola {

    static Scanner sc = new Scanner(System.in);

    public static void mostrarLineas(List<String> lineas){
        for (int i = 0; i < lineas.size(); i++) {
            System.out.println(i+1 + " - " + lineas.get(i));
        }
    }

    public static void imprimirMensajeExcepcion(Exception e){
        System.out.println(e.getMessage());
    }

    public static void imprimirNota(Path rutaArchivo){

        try(BufferedReader br = Files.newBufferedReader(rutaArchivo)){
            for (String linea = br.readLine(); br.readLine() != null; ) {
                System.out.println(linea);
            }

        }catch(Exception e){
            System.out.println(e.getMessage());

        }
    }

    public static int obtenerOpcion(int limite) {
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

    public static List<String> getLineas(){

        String patron = ":q";
        Pattern pattern = Pattern.compile(patron);

        ArrayList<String> lineas = new ArrayList<>();

        System.out.println("\n Ingrese las notas que desea escribir. Pulse ENTER para saltar de línea. escriba ':q' para salir del modo escritura\n");
        while (true){
            String linea =  sc.nextLine();
            Matcher matcher = pattern.matcher(linea);
            if(matcher.find()){
                linea = linea.replaceFirst(patron, "");
                lineas.add(linea + "\n");
                return lineas;
            } else {
                lineas.add(linea + "\n");
            }

        }
    }

    public static Nota crearNota(){
        return new Nota(sc.nextLine());
    }

    public static void modificarLinea(Nota nota){
        mostrarLineas(nota.getLineas());
        int opcion = obtenerOpcion(nota.getLineas().size()) - 1;
        System.out.println("\n Ingrese la nueva línea que desea escribir (la anterior será sobreescrita)");
        nota.getLineas().set(opcion, sc.nextLine());
    }

    public static void mostrarMenuInicio(){
        System.out.println("\nSeleccione una opción:");
        System.out.println("1. Iniciar Sesión");
        System.out.println("2. Registrarse");
        System.out.println("3. Salir");
    }

    public static void mostrarMenuAcciones(){
        System.out.println("\nSeleccione una opcion:");
        System.out.println("1. Crear Nota");
        System.out.println("2. Añadir a la Nota");
        System.out.println("3. Modificar Nota");
        System.out.println("4. Mostrar Notas");
        System.out.println("5. Mostrar una nota");
        System.out.println("6. Eliminar Nota");
        System.out.println("7. Salir");
    }

    public static Usuario crearUsuario(){
        try{
            System.out.println("\nIntroduce el nombre del usuario");
            String nombre = sc.nextLine();
            System.out.println("\nIntroduce el email del usuario");
            String email = sc.nextLine();

            return new Usuario(nombre, email);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static String contrasenia(){
        System.out.println("\nIntroduce la contraseña");
            return sc.nextLine();
    }

    public static String email(){
        System.out.println("\nIntroduce el email de tu usuario");
        return sc.nextLine();
    }

}
