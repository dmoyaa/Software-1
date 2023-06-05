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
}
