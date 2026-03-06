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

        ArchivoNota archn = null;
        UsuarioNota servicio = null;
        boolean salirInicio = false;
        boolean salirAcciones = false;
        while(true){
            Consola.mostrarMenuInicio();
            int opcion = Consola.obtenerOpcion(3);
            switch (opcion){
                case 1:
                    try{
                        archn = ArchivoNota.iniciarSesion(Consola.email(), Consola.contrasenia());
                        servicio = new UsuarioNota(archn);
                    } catch (Exception e) {
                        Consola.imprimirMensajeExcepcion(e);
                    }


                case 2:
                    try{
                        Usuario usuario = Consola.crearUsuario();
                        String contrasenia = Consola.contrasenia();
                        ArchivoNota.registrarUsuario(usuario, contrasenia);
                        archn = ArchivoNota.iniciarSesion(usuario.getEmail(), contrasenia);
                        servicio = new UsuarioNota(archn);
                    } catch (Exception e) {
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;

                case 3:
                    salirInicio = true;
                    break;

            }
            break;
        }

        while(!salirInicio){
            Consola.mostrarMenuAcciones();
            int opcion = Consola.obtenerOpcion(7);
            switch (opcion){
                case 1:
                    try{
                        servicio.crearNotaNueva();
                    }catch(Exception e){
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;

                case 2:
                    try{
                        servicio.aniadirANota();
                    }catch(Exception e){
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;
                case 3:
                    try{
                        servicio.editarNota();
                    }catch(Exception e){
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;
                case 4:
                    try{
                        servicio.mostrarNotas();
                    }catch(Exception e){
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;
                case 5:
                    try{
                        servicio.mostrarNota();
                    }catch(Exception e){
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;
                case 6:
                    try{
                        servicio.eliminarNota();
                    }catch(Exception e){
                        Consola.imprimirMensajeExcepcion(e);
                    }
                    break;
                case 7:
                    salirAcciones = true;
                    break;
            }
            if (salirAcciones){
                break;
            }
        }
        System.out.println("\nHasta luego");
    }
}
