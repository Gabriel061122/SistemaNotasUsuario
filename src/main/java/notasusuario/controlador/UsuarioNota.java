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


  public void editarNota() throws IOException {
        try {
            List<String> lista = archn.listaNotas();
            Consola.mostrarLineas(lista);
            Nota nota = archn.getNota(Consola.obtenerOpcion(lista.size()-1));
            Consola.modificarLinea(nota);
            archn.archivarNota(nota);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  }

  public void eliminarNota() throws IOException {
        try {
            List<String> lista = archn.listaNotas();
            Consola.mostrarLineas(lista);
            Nota nota = archn.getNota(Consola.obtenerOpcion(lista.size()-1));
            archn.eliminarNota(nota.getNombreArchivo());
        } catch (IOException e) {
            throw new IOException(e);
        }
  }

  public void aniadirANota() throws IOException {
      try {
          List<String> lista = archn.listaNotas();
          Consola.mostrarLineas(lista);
          Nota nota = archn.getNota(Consola.obtenerOpcion(lista.size()-1));
          List<String> listaAAniadir = Consola.getLineas();
          archn.aniadirANota(listaAAniadir ,nota );
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

  public void mostrarNota() throws IOException {
        try{
            List<String> lista = archn.listaNotas();
            Consola.mostrarLineas(lista);
            Nota nota = archn.getNota(Consola.obtenerOpcion(lista.size()-1));
            Consola.mostrarLineas(nota.getLineas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
  }


}
