package notasusuario.archivos;

import notasusuario.excepciones.NotaYaExistenteException;
import notasusuario.modelo.Nota;
import notasusuario.modelo.Usuario;
import notasusuario.vista.Consola;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ArchivoNota {

    static Path rutaUsuarios = Path.of("../data/users.txt");

    Usuario usuario;
    Path rutaUsuario;

    public ArchivoNota(Usuario usuario){
        this.usuario = usuario;
        rutaUsuario = Path.of("../data/" + usuario.curarEmail());
    }

    public void archivarNota(Nota nota) throws IOException {

        Path rutaFichero = rutaUsuario.resolve(nota.getTitulo());

        try (BufferedWriter writer = Files.newBufferedWriter(rutaFichero)) {

            for (String line : nota.getLineas()) {
                writer.write(line);
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public boolean existeArchivo(String nombre){
        return Files.exists(rutaUsuario.resolve(nombre));
    }

    public void aniadirRegistroNota(Nota nota) throws NotaYaExistenteException, IOException {
        Path registry = this.rutaUsuario.resolve("registry.txt");

        ArrayList<String> lineas = Parser.lineaALinea(registry);
        for (String line : lineas) {
            if (line.equals(nota.getNombreArchivo())){
                throw new NotaYaExistenteException(nota.getNombreArchivo());
            }
        }

        try (BufferedWriter writer = Files.newBufferedWriter(registry, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

            writer.write(nota.getNombreArchivo());
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    
    public void aniadirANota(List<String> lineas, Nota nota) throws IOException {
        try (BufferedWriter bf = Files.newBufferedWriter(rutaUsuario.resolve(nota.getNombreArchivo()), StandardOpenOption.APPEND)) {
            for (String line : lineas) {
                bf.write(line);
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    
    public void eliminarNota(String nombre) throws IOException {
        try {
            Files.delete(rutaUsuario.resolve(nombre));
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
    
    public List<String> listaNotas() throws IOException {
        try{
            return Parser.lineaALinea(rutaUsuario.resolve("registry.txt"));
        }
        catch (IOException e){
            throw new IOException(e);
        }
    }

    public Nota getNota(int index) throws IOException {
        try{
            List<String> notas = listaNotas();
            String nombre = notas.get(index).replaceFirst("\\.txt$", "");
            List<String> lineas = Parser.lineaALinea(rutaUsuario.resolve(notas.get(index)));
            Nota nota = new Nota(nombre);
            nota.setLineas(lineas);
            return nota;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static ArchivoNota iniciarSesion(String email, String contrasenia) throws Exception {
        try {
            Path rutaUsuarios = Path.of("../data/users.txt");
            List<String> informacion = Parser.lineaALinea(rutaUsuarios);

            if(informacion.isEmpty()){
                throw new IOException("No se puede iniciar sesión");
            }

            for (String line : informacion) {
                String[] infoLinea = Parser.parseLinea(line);
                if (infoLinea[2].equals(contrasenia) && (infoLinea[0].equals(email) || infoLinea[1].equals(email))) {
                    return new ArchivoNota(new Usuario(infoLinea[0], infoLinea[1]));
                }
            }
            throw new IOException("No se encontró el usuario especificado");
        } catch (IOException e) {
            throw new IOException(e);
        } catch (Exception e) {
            throw new Exception(e);
        }


    }

    public static void registrarUsuario(Usuario usuario, String contrasenia) throws IOException {
        try(BufferedWriter bf = Files.newBufferedWriter(rutaUsuarios, StandardOpenOption.CREATE, StandardOpenOption.APPEND)){
            if (!usuarioExistente(usuario)) {
                bf.write(usuario.getNombre()+";"+usuario.getEmail()+";"+contrasenia);
                Path carpetaPadre = rutaUsuarios.getParent();
                Path carpetaUsuario = carpetaPadre.resolve(usuario.curarEmail());
                Files.createDirectory(carpetaPadre.resolve(usuario.curarEmail()));
                Files.createFile(carpetaUsuario.resolve("registry.txt"));
            } else {
                throw new IOException("Usuario ya existente");
            }
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public static boolean usuarioExistente(Usuario usuario) throws IOException {
        try (BufferedReader br = Files.newBufferedReader(rutaUsuarios)) {
            for  (String linea = br.readLine(); linea != null; ) {
                String email = Parser.parseLinea(linea)[1];
                if(email.equals(usuario.getEmail())){
                    return true;
                }
            }
            return false;
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

}