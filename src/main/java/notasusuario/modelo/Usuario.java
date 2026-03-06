package notasusuario.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Usuario {

  private String nombre;
  private String email;

  public Usuario(String nombre, String email) {
    this.nombre = nombre;
    this.email = verificarMail(email);
  }

  private String verificarMail(String email) {
    Pattern pattern = Pattern.compile("[A-Za-z0-9]+@[A-Za-z0-9]+\\.[a-z0-9]+$");
    Matcher matcher = pattern.matcher(email);
    if (matcher.find()) {
      return email;
    }
    throw new IllegalArgumentException("Se ha insertado un email no válido");
  }

  public String curarEmail(){
    String[] partesEmail = this.getEmail().split("[@.]");
    StringBuilder emailCurado = new StringBuilder();
    for (String s : partesEmail) {
      emailCurado.append(s);
    }
    return emailCurado.toString();
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
