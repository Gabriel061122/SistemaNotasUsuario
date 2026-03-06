package notasusuario.modelo;

import java.util.List;

public class Nota {

  String titulo;
  private List<String> lineas;

  public Nota(String titulo) {
    this.titulo = titulo;
  }

  public String getTitulo() {
    return titulo;
  }

  public String getNombreArchivo(){
    return getTitulo() + ".txt";
  }

  public void addLinea(String linea) {
    lineas.add(linea);
  }

  public String getLinea(int index) {
    return this.lineas.get(index);
  }

  public List<String> getLineas() {
    return this.lineas;
  }

  public void setLineas(List<String> lineas) {
    this.lineas = lineas;
  }
}
