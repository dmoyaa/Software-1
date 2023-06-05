package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.ClienteDto;
import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.services.ClientesService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clients")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<ClienteDto> clientes = new ClientesService().obtenerClientes();

            return Response.ok()
                    .entity(clientes)
                    .build();


        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("cedula") String cedula) {
        Response response = null;

        ClienteDto cliente = new ClientesService().ciente(cedula);
        System.out.println(cliente);
        try {
            if (cliente != null) {
                return response.ok().
                        entity(cliente)
                        .build();
            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "User not found"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).entity(new ExceptionMessage(404, "Falló al Ingresar")).build();

        }

    }

    @PUT
    @Path("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response actualizarcliente(@FormParam("address") String direccion,
                                      @FormParam("lastname") String lastname,
                                      @FormParam("phone") String phone,
                                      @FormParam("name") String name,
                                      @PathParam("cedula") String cedula
    ) {
        Response response = null;
        Boolean cliente = new ClientesService().update(cedula, name, lastname, direccion, phone);

        try {
            if (cliente) {
                return response.ok().
                        entity(new ExceptionMessage(200, "Actualizado exitosamente"))
                        .build();

            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "USER NOT FOUND"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).entity(new ExceptionMessage(404, "Falló al Ingresar")).build();

        }

    }

    @PUT
    @Path("/delete/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response actualizarEliminar(
            @PathParam("cedula") String cedula
    ) {
        Response response = null;
        Boolean cliente = new ClientesService().delete(cedula);
        System.out.println(cliente.toString());
        try {
            if (cliente) {
                return response.ok().
                        entity(new ExceptionMessage(200, "Actualizado exitosamente"))
                        .build();

            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "USER NOT FOUND"))
                        .build();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).entity(new ExceptionMessage(404, "Falló al Ingresar")).build();

        }

    }

    @POST
    @Path("agregar/{cedular}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response ingresar(@FormParam("address") String direccion,
                             @FormParam("lastname") String lastname,
                             @FormParam("phone") String phone,
                             @FormParam("name") String name,
                             @PathParam("cedular") String cedula
    ) {
 Response response = null;
        Boolean cliente = new ClientesService().insert(cedula, name, lastname, direccion, phone);
        try {
            if (cliente) {
                return response.ok().
                        entity(new ExceptionMessage(200, "Ingresado exitosamente"))
                        .build();

            } else {
                return Response.status(404)
                        .entity(new ExceptionMessage(404, "USER NOT FOUND"))
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(404).entity(new ExceptionMessage(404, "Falló al Ingresar")).build();

        }


    }

}
