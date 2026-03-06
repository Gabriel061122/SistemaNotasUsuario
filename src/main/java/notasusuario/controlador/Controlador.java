package notasusuario.controlador;

import notasusuario.archivos.ArchivoNota;
import notasusuario.modelo.Usuario;
import notasusuario.vista.Consola;

public class Controlador {
    private UsuarioNota usuarioNota;

    public Controlador(UsuarioNota usuarioNota) {
        this.usuarioNota = usuarioNota;
    }

    public void iniciar() {

        ArchivoNota archn;
        UsuarioNota servicio;
        boolean salir = false;
        while(true){
            Consola.mostrarMenuInicio();
            int opcion = Consola.obtenerOpcion(3);
            switch (opcion){
                case 1:
                    try{
                        archn = ArchivoNota.iniciarSesion(Consola.email(), Consola.contrasenia());
                    } catch (Exception e) {
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;

                case 2:
                    try{
                        Usuario usuario = Consola.crearUsuario();
                        String contrasenia = Consola.contrasenia();
                        ArchivoNota.registrarUsuario(usuario, contrasenia);
                        archn = ArchivoNota.iniciarSesion(usuario.getEmail(), contrasenia);
                    } catch (Exception e) {
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;

                case 3:
                    salir = true;
                    break;

            }
            break;
        }
        while(!salir){
            Consola.mostrarMenuAcciones();
            int opcion = Consola.obtenerOpcion(3);
        }
        System.out.println("\nHasta luego");
    }
}
