package notasusuario.controlador;

import notasusuario.archivos.ArchivoNota;
import notasusuario.excepciones.NotaNoExistenteException;
import notasusuario.excepciones.NotaYaExistenteException;
import notasusuario.modelo.Nota;
import notasusuario.modelo.Usuario;
import notasusuario.vista.Consola;



import java.io.IOException;

import java.nio.file.Path;


public class UsuarioNota {

    private Usuario usuario;
    private ArchivoNota archn;

    public UsuarioNota(ArchivoNota archn, Usuario usuario) {
        this.archn = archn;
        this.usuario = usuario;
    }

  public void crearNotaNueva(Usuario usuario) throws NotaYaExistenteException{
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

  public void aniadirANota(Nota nota) throws IOException {
        
  }


}
