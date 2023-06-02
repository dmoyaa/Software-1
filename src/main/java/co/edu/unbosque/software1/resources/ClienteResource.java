package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.Cliente;
import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.services.Clientes;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Path("/clients")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
           List<Cliente> clientes = new Clientes().obtenerClientes();

            return Response.ok()
           .entity(clientes)
                    .build();


        } catch (Exception e) {
            System.out.println("----------------------------------------NO SE SABE------------------------------------------");
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("cedula")  String cedula) {
        System.out.println("lo que envio es "+ cedula);
        Response response = null;
        System.out.println("-*-*-*-*-*-*-*ENTROOOOOOOOOOOOOOOOOOOOOO-*-*-*-*-*-*-*-**-*");
            Cliente cliente = new Clientes().ciente(cedula);
        System.out.println(cliente);
            if (cliente != null) {
                return response.ok().
                        entity(cliente)
                        .build();
            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "User not found"))
                        .build();
            }

    }

}
