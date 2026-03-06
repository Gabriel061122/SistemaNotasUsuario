package notasusuario.modelo;

import java.util.ArrayList;

public class Nota {

  String titulo;
  private ArrayList<String> lineas = new ArrayList<>();

  public Nota(String titulo) {
    this.titulo = titulo;
  }

  public void addLinea(String linea) {
    lineas.add(linea);
  }

  public String getLinea(int index) {
    return this.lineas.get(index);
  }

  public ArrayList<String> getLineas() {
    return this.lineas;
  }
}
