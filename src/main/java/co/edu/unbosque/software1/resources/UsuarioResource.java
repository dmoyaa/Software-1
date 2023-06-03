package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.UsuarioDto;
import co.edu.unbosque.software1.services.UsuarioService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/users")


public class UsuarioResource {


    private UsuarioService usuarioService = new UsuarioService();
    private UsuarioDto usuario = null;

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

    @POST
    @Path("/{register}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)

    public Response registroUsuario(@PathParam ("register") String username, @FormParam("Password") String password, @FormParam("Rol") String rol) {
        Response response = null;
        usuarioService = new UsuarioService();
        try{
            UsuarioDto user = usuarioService.crearUsuario(username, password, rol);

            if(user!=null){
                response= Response.ok().entity(new ExceptionMessage(200, "El usuario ha sido registrado exitosamente")).build();
            }else {
                response = Response.status(404).entity(new ExceptionMessage(404, "El usuario ya existe")).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404, "Error con el nuevo usuario")).build();
        }
        return response;
    }
}
