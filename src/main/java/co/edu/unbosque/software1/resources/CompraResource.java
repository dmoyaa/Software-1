package co.edu.unbosque.software1.resources;


import co.edu.unbosque.software1.dtos.CompraDto;
import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.ProductoDto;
import co.edu.unbosque.software1.services.CompraService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;


@Path("/compra")
public class CompraResource {

    @Context
    ServletContext context;
    private CompraDto compraDto= null;
    private ProductoDto productoDto= null;
    private CompraService compraService= null;

    @GET
    @Path("/{nit}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response clientesProveedor(@PathParam("nit") String nit_proveedor){
        Response response = null;
        List<ProductoDto> lista = null;
        compraService = new CompraService();
        System.out.println("SEXO ANALLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLLL");
        try{
            lista = compraService.listaProductosProveedor(nit_proveedor);
            if (lista != null){
                response = Response.ok().entity(lista).build();
            }else{
                response = Response.status(404).entity(new ExceptionMessage(404,"Fallo en consulta")).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404,"Fallo buscando productos")).build();
        }
        return response;
    }

    @POST
    @Path("post/{proveedor}/{pago}")
    @Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertarCompra(@PathParam("proveedor") String nit, @FormParam("fechape") java.sql.Date fecha_Pago,
                                   @FormParam("total_pago") double total, @FormParam("producto") int id_producto,
                                   @FormParam("fecha_registro") java.sql.Date  date, @PathParam("pago") String pago){
        System.out.println("--------------------------------------------------POST");
        Response response = null;
        compraService = new CompraService();
        compraDto = new CompraDto(1, date, nit, total, pago,fecha_Pago,"",'A' );
        try{
            if (compraService.agregarCompra(compraDto)==true){
                response = Response.ok().entity(new ExceptionMessage(200, "Agregado exitoso")).build();
            }else{
                Response.status(404).entity(new ExceptionMessage(404, "Error ingresando")).build();
            }

        }catch (Exception e) {

            Response.status(404).entity(new ExceptionMessage(404, "Error")).build();
        }
        return response;
    }

}
