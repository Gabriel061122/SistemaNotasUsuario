package notasusuario.controlador;

import notasusuario.excepciones.UsuarioNoEncontradoException;
import notasusuario.modelo.Usuario;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorUsuario {

    static Path rutaData = Path.of("..", "data");

    public Usuario iniciarSesion(String email, String contrasenya){
        try(BufferedReader br = Files.newBufferedReader(rutaData.resolve("users.txt"))){
            String linea;
            while ((linea = br.readLine()) != null){
                if(verificarUsuario(email, contrasenya, linea)){
                    String[] info = Parser.parseLinea(linea);
                    return new  Usuario(info[0], info[1]);
                }
            }
            throw new UsuarioNoEncontradoException();
        } catch (Exception e) {
            throw new RuntimeException("Ha habido un error al leer el usuario");
        }

    }

    public static void registrarUsuario(Usuario usuario, String contrasenya){
        if(Files.exists(rutaData.resolve("users.txt"))){
            try(BufferedReader br = Files.newBufferedReader(rutaData.resolve("users.txt"))){


                String linea;
                while ((linea = br.readLine()) != null){

                    if(verificarUsuario(usuario,  linea)){
                        throw new IllegalArgumentException("Usuario ya existe");
                    }


                    escribirLinea(rutaData.resolve("users.txt"), (usuario.getNombre() + ";" + usuario.getEmail() + ";" + contrasenya));

                }
            } catch (Exception e) {
                throw new RuntimeException("Ha habido un error al escribir usuario");
            }
        }else{
            try {
                Files.createFile(rutaData.resolve("users.txt"));
                escribirLinea(rutaData.resolve("users.txt"), (usuario.getNombre() + ";" + usuario.getEmail() + ";" + contrasenya));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    }

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

    public static void escribirLinea(Path path, String linea ){
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            bw.write(linea);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
