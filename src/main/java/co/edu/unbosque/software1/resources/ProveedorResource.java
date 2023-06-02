package co.edu.unbosque.software1.resources;


import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.ProveedorDto;
import co.edu.unbosque.software1.services.ProveedorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/proveedor")
public class ProveedorResource {

    private ProveedorDto proveedor= null;
    private ProveedorService proveedorService ;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListaProveedores() {
        Response response = null;
        List<ProveedorDto> lista = null;
        proveedorService = new ProveedorService();
        try{
            lista = proveedorService.listaProveedores();
            System.out.println("Lista perfecta");
            if(lista!=null){
                response= Response.ok().entity(lista).build();
            }else {
                response = Response.status(404).entity(new ExceptionMessage(404, "No existen proveedores")).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404, "Error con proveedores")).build();
        }
        return response;
    }

    @GET
    @Path("/{nit_proveedor}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response informacionProveedor(@PathParam("nit_proveedor") String nit){
        proveedor= new ProveedorDto("","","","","");
        Response response = null;
        proveedorService = new ProveedorService();
        try{
            proveedor = proveedorService.buscarProveedor(nit);

            if (proveedor!=null){
                response = Response.ok().entity(proveedor).build();
            }
            else{
                response = Response.status(404).entity(new ExceptionMessage(404, "No existe proveedor")).build();
            }

        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404, "Error con proveedores")).build();
        }
        return response;
    }

}
