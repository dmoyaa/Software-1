package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.UsuarioDto;
import co.edu.unbosque.software1.services.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/users")


public class UsuarioResource {

    private UsuarioService usuarioService = new UsuarioService();
    private UsuarioDto usuario = null;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListaUsuarios(){
        Response response = null;
        List<UsuarioDto> lista = null;
        usuarioService = new UsuarioService();
        System.out.println("entra a listaUsuario");
        try {
            lista = usuarioService.listaUsuarios();
            System.out.println("lista entra");
            if(lista!= null){
                response = Response.ok().entity(lista).build();
            }else{
                response = Response.status(404).entity(new ExceptionMessage(404, "No existen usuarios")).build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            response=Response.status(404).entity(new ExceptionMessage(404, "Error con usuarios")).build();
        }
        return response;
    }

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("username") String username){
        usuario = new UsuarioDto("","","");
        System.out.println("entro en get");
        Response response = null;
        try{
            usuario = usuarioService.consultarUsuario(username);
            if (usuario != null){
                response = Response.ok().entity(usuario).build();
            }else {
                String mensaje = "No se ha encontrado el usuario";
                response = Response.status(404).entity(new ExceptionMessage(404,mensaje)).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404,"ERROR")).build();
        }

        return response;
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarUsuario(@FormParam("username") String username, @FormParam("Password") String password,
                               @FormParam("Rol") String rol){
        usuario = new UsuarioDto(username,password,rol);
        Response response = null;
        usuarioService =new UsuarioService();
        System.out.println("pasa actualizar");
        try{
            if (usuarioService.actualizarUsuario(usuario) == true){
                response = Response.ok().entity(new ExceptionMessage(200, "Actualizado correctamente")).build();
                System.out.println("Actualizado");
            }else {
                response=Response.status(404).entity(new ExceptionMessage(404, "Fallo al actualizar")).build();

            }
        } catch (Exception e){
            e.printStackTrace();
            response=Response.status(404).entity(new ExceptionMessage(404, "Error al actualizar")).build();
        }

        return response;
    }

    @PUT
    @Path("eliminar/{username}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminarUsuario(@PathParam("username") String username){
        Response response = null;
        usuarioService = new UsuarioService();
        System.out.println("Eliminar");
        try{
            if (usuarioService.eliminarUsuario(username) == true){
                response = Response.ok().entity(new ExceptionMessage(200, "Eliminado exitoso")).build();
            }
            else {
                response = Response.status(404).entity(new ExceptionMessage(404, "Error al eliminar")).build();

            }
        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404, "Fallo al actualizar")).build();
        }
        return response;
    }


    @POST
    @Path("agregar/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response agregarUsuario(@PathParam("username") String username, @FormParam("Password") String password,
                                   @FormParam("Rol") String rol){
        Response response = null;
        usuarioService = new UsuarioService();
        usuario = new UsuarioDto(username, password, rol);
        System.out.println("ENTRO A AGREGAR Usuario-----------------------------------");
        try{

            if (usuarioService.crearUsuario(usuario)==true){
                response = Response.ok().entity(new ExceptionMessage(200, "Usuario creado exitosamente")).build();

            }else{
                response = Response.status(404).entity(new ExceptionMessage(404, "El Usuario ya existe")).build();
            }


        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404,"Error al crear")).build();
        }

        return response;
    }


}
