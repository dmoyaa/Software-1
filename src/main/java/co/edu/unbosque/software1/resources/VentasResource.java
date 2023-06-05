//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.VentaDto;
import co.edu.unbosque.software1.services.VentasService;
import java.sql.Date;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/sells")
public class VentasResource {
    public VentasResource() {
    }

    @GET
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response get() {
        Response response = null;
        List ventasporpagar = (new VentasService()).obtenerVentasNoPagadas();

        try {
            return ventasporpagar != null ? Response.ok().entity(ventasporpagar).build() : Response.status(404).entity(new ExceptionMessage(404, "Sells not found")).build();
        } catch (Exception var4) {
            var4.printStackTrace();
            return Response.status(404).entity(new ExceptionMessage(404, "Falló al consultar")).build();
        }
    }

    @PUT
    @Path("/{vta}")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response put(@PathParam("vta") int idfra) {
        Response response = null;
        String idventa= String.valueOf(idfra);
        Boolean venta = (new VentasService()).delete(idventa);
        try {
            if (venta) {
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
    @Path("/{cedula}")
    @Produces({"application/json"})
    @Consumes({"application/x-www-form-urlencoded"})
    public Response pst(@PathParam("cedula") Date cedula, @FormParam("cedulacliente") String name, @FormParam("totalventas") Boolean check) {
        Response response = null;
        System.out.println("----------------------------------entroooooooooooooooooooooooooooo");
        if (check == null) {
            check = false;
        }

        if (name == null) {
            name = "";
        }

        List clientes = (new VentasService()).obtenerVentas(cedula, name, check);

        try {
            return clientes != null ? Response.ok().entity(clientes).build() : Response.status(404).entity(new ExceptionMessage(404, "User not found")).build();
        } catch (Exception var7) {
            var7.printStackTrace();
            return Response.status(404).entity(new ExceptionMessage(404, "Falló al Ingresar")).build();
        }
    }


}
