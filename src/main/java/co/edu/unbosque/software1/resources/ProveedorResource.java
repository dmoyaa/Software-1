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
        Response response = null;
        proveedorService = new ProveedorService();
        System.out.println("SEXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
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

    @PUT
    @Path("/{nit}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@FormParam("name") String nombre, @FormParam("address") String direccion,
                               @FormParam("email") String email, @FormParam("phone") String telefono,
                               @PathParam("nit") String nit){
        proveedor= new ProveedorDto(nit,nombre,direccion,email, telefono);
        Response response= null;
        proveedorService = new ProveedorService();
        System.out.println("sexo anal duro en el b-a-----------------------ñ-o--");
        try{
            if (proveedorService.actualizarProveedor(proveedor) == true){
                response=Response.ok().entity(new ExceptionMessage(200, "Actualizado exitosamente")).build();
                System.out.println("Lo logré que emocion entuziasmo");
            }else{
                response=Response.status(404).entity(new ExceptionMessage(404,"Fallo en actualizar")).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response=Response.status(404).entity(new ExceptionMessage(404,"Error al actualizar")).build();
        }
        return response;
    }

    @PUT
    @Path("eliminar/{nit}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@PathParam("nit") String nit){
        Response response = null;
        proveedorService = new ProveedorService();
        System.out.println("SEXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        try{

            if (proveedorService.eliminarProveedor(nit) == true){
                response=Response.ok().entity(new ExceptionMessage(200, "Eliminado exitosamente")).build();
            }
            else{
                response=Response.status(404).entity(new ExceptionMessage(404,"Error al eliminar")).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response=Response.status(404).entity(new ExceptionMessage(404,"Falló al actualizar")).build();
        }
        return response;
    }

    @POST
    @Path("agregar/{nit}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response agregar(@PathParam("nit") String nit, @FormParam("name") String name,
                            @FormParam("address") String address, @FormParam("email") String email,
                            @FormParam("phone") String phone){
        Response response = null;
        proveedorService = new ProveedorService();
        proveedor = new ProveedorDto(nit, name, address, email, phone);
        System.out.println("ENTRO A AGREGAR PROVEEDOR-----------------------------------");
        try{

            if (proveedorService.agregarProveedor(proveedor)==true){
                response = Response.ok().entity(new ExceptionMessage(200, "Ingreso de proveedor exitoso")).build();

            }else{
                response = Response.status(404).entity(new ExceptionMessage(404, "Proveedor ya existente")).build();
            }


        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404,"Error insertando")).build();
        }

        return response;
    }
}
