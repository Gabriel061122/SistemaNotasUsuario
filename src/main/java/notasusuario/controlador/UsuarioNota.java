package notasusuario.controlador;

import notasusuario.archivos.ArchivoNota;
import notasusuario.excepciones.NotaNoExistenteException;
import notasusuario.excepciones.NotaYaExistenteException;
import notasusuario.modelo.Nota;
import notasusuario.modelo.Usuario;
import notasusuario.vista.Consola;



import java.io.IOException;

import java.nio.file.Path;
import java.util.List;


public class UsuarioNota {

    private ArchivoNota archn;

    public UsuarioNota(ArchivoNota archn) {
        this.archn = archn;
    }

  public void crearNotaNueva() throws NotaYaExistenteException{
      try {
          Nota nota = Consola.crearNota();
          nota.setLineas(Consola.getLineas());
          archn.archivarNota(nota);
      }catch (IOException e) {
          throw new RuntimeException(e);
      }
  }


  public void editarNota(Nota nota, ArchivoNota archn) throws IOException {
      if (archn.existeArchivo(nota.getTitulo() + ".txt")) {
          try {
              Consola.modificarLinea(nota);
              archn.archivarNota(nota);
          } catch (IOException e) {
              throw new IOException(e);
          }
      } else {
          throw new NotaNoExistenteException(nota.getTitulo());
      }
  }

  public void eliminarNota(Nota nota) throws IOException {
        try {
            archn.eliminarNota(nota.getNombreArchivo());
        } catch (IOException e) {
            throw new IOException(e);
        }
  }

  public void aniadirANota(Nota nota, List<String> lineas) throws IOException {
        try {
            archn.aniadirANota(lineas, nota);
        } catch (IOException e) {
            throw new IOException(e);
        }
  }

  public void mostrarNotas() throws IOException {
        try {
            Consola.mostrarLineas(archn.listaNotas());
        } catch (IOException e){
            throw new IOException(e);
        }
  }

  public void mostrarNota(Nota nota) throws IOException {
        try{
            Consola.mostrarLineas(archn.listaNotas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  }


}
