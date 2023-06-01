package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.Cliente;
import co.edu.unbosque.software1.services.Clientes;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/clients")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            System.out.println("----------------------------------------entró al get puta madre-------------------------------------------");
            List<Cliente> clientes = new Clientes().obtenerClientes();

            return Response.ok()
           .entity(clientes)
                    .build();


        } catch (Exception e) {
            System.out.println("----------------------------------------NO SE SABE------------------------------------------");
            return Response.serverError().build();
        }
    }

}
