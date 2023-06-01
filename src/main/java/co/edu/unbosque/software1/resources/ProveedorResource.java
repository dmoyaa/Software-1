package co.edu.unbosque.software1.resources;


import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.ProveedorDto;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/proveedor")
public class ProveedorResource {

    private ProveedorDto prveedor= null;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListaProveedores() {
        Response response = null;
        List<ProveedorDto> lista = null;
        try{


        }catch (Exception e){
           response = Response.status(404).entity(new ExceptionMessage(404, "Error con proveedores")).build();
        }
        return response;
    }

}
