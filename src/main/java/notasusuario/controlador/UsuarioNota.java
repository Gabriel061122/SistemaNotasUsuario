package notasusuario.controlador;

import notasusuario.excepciones.NotaYaExistenteException;
import notasusuario.modelo.Nota;
import notasusuario.modelo.Usuario;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioNota {

  static final Path rutaData = Path.of("..", "data");

  public static void crearNotaNueva(Usuario usuario, Scanner sc) throws NotaYaExistenteException{
      try {
          String nombreNota = sc.nextLine();
          Path rutaFichero = rutaData.resolve(Path.of(curarEmail(usuario), nombreNota + ".txt"));
          if (Files.exists(rutaFichero)) {
              throw new NotaYaExistenteException(nombreNota);
          } else{
              try (BufferedWriter writer = Files.newBufferedWriter(rutaFichero)) {
                    Files.createFile(rutaFichero);

              } catch (Exception e) {

              }
          }
      }catch(NotaYaExistenteException e){
          e.getConfirmacion(sc);
      }
  }

  private Path obtenerRutaUsuario(Usuario usuario){
    return rutaData.resolve(Path.of("usuarios", curarEmail(usuario)));
  }

  public static String curarEmail(Usuario usuario){
    String[] partesEmail = usuario.getEmail().split("[@.]");
    String emailCurado = "";
      for (String s : partesEmail) {
          emailCurado += s;
      }
    return emailCurado;
  }

  public static List<String> getLineas(Scanner sc){

      String patron = ":q";
      Pattern pattern = Pattern.compile(patron);

      ArrayList<String> lineas = new ArrayList<>();

      System.out.println("\n Ingrese las notas que desea escribir. Pulse ENTER para saltar de línea. escriba ':q' para salir del modo escritura\n");
      while (true){
          String linea =  sc.nextLine();
          Matcher matcher = pattern.matcher(linea);
          if(matcher.find()){
              linea = linea.replaceFirst(patron, "");
              lineas.add(linea);
              return lineas;
          } else {
              lineas.add(linea + "\n");
          }

      }
  }
}
