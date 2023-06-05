package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.ExceptionMessage;
import co.edu.unbosque.software1.dtos.ProductoDto;
import co.edu.unbosque.software1.dtos.ProveedorDto;
import co.edu.unbosque.software1.services.ProductoService;
import co.edu.unbosque.software1.services.ProveedorService;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/productos")

public class ProductoResource {

    private ProductoService productoService = new ProductoService();
    private ProductoDto producto = null;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response ListaProductos(){
        Response response = null;
        List<ProductoDto> lista = null;
        productoService = new ProductoService();
        try {
            lista = productoService.listaProductos();
            System.out.println("Lista ok");
            if (lista!=null){
                response = Response.ok().entity(lista).build();
            } else {
                response = Response.status(404).entity(new ExceptionMessage(404, "No existen Productos")).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404, "Error con Productos")).build();
        }
        return response;
    }

    @GET
    @Path("/actualizar/{id_producto}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultar(@PathParam("id_producto") int id_producto){
        Response response = null;
        productoService = new ProductoService();
        System.out.println("consulta");
        try{
            producto=productoService.consultarProducto(id_producto);
            if (producto!= null){
                response = Response.ok().entity(producto).build();
                System.out.println("ok");
            }else{
                response=Response.status(404).entity(new ExceptionMessage(404, "fallo al consultar")).build();
                System.out.println("no ok");
            }

        }catch (Exception e){
            response=Response.status(404).entity(new ExceptionMessage(404, "error")).build();
            System.out.println("peor que ok");
        }
        return response;
    }


    @PUT
    @Path("/actualizar2/{id_producto}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    //@Produces(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(@PathParam("id_producto") int id_producto, @FormParam("name_nombre") String nombre,@FormParam("stock") int stock, @FormParam("precio_venta") double precio_venta,
                                       @FormParam("precio_compra") double precio_compra){
        producto = new ProductoDto(id_producto, nombre, 1,stock, "", precio_venta,precio_compra, "");
        Response response = null;
        productoService = new ProductoService();
        System.out.println("entra en actualizar");
        try{
            if (productoService.actualizarProducto(producto) == true){
                response = Response.ok().entity(new ExceptionMessage(200, "Actualizado exitoso")).build();
                System.out.println("c actrualiza");
            } else {
                response=Response.status(404).entity(new ExceptionMessage(404, "fallo al actualizar")).build();

            }
        } catch (Exception e){
            e.printStackTrace();
            response=Response.status(404).entity(new ExceptionMessage(404, "error")).build();

        }

        return response;
    }



    @PUT
    @Path("/eliminar/{id_producto}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response eliminar(@FormParam("referencia") int id_producto){
        Response response = null;
        productoService = new ProductoService();
        System.out.println("SEXOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        try{

            if (productoService.eliminarProducto(id_producto) == true){
                response=Response.ok().entity(new ExceptionMessage(200, "Eliminado exitosamente")).build();
            }
            else{
                response=Response.status(404).entity(new ExceptionMessage(404,"Error al eliminar")).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            response=Response.status(404).entity(new ExceptionMessage(404,"Falló al eliminar")).build();
        }
        return response;
    }

    @POST
    @Path("agregar/{id_producto}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response agregar(@FormParam("referencia") int id_producto, @FormParam("name") String nombre,
                            @FormParam("stock2") int stock, @FormParam("precio_venta") double precio_venta,
                            @FormParam("precio_compra2") double precio_compra, @FormParam("proveedor") String nit_proveedor){
        Response response = null;
        productoService = new ProductoService();
        producto = new ProductoDto(id_producto, nombre, 10, stock, nit_proveedor,precio_venta,precio_compra,nombre);
        System.out.println("ENTRO A AGREGAR Producto-----------------------------------");
        try{

            if (productoService.agregarProducto(producto)==true){
                response = Response.ok().entity(new ExceptionMessage(200, "Ingreso de producto exitoso")).build();

            }else{
                response = Response.status(404).entity(new ExceptionMessage(404, "Producto ya existente")).build();
            }


        }catch (Exception e){
            e.printStackTrace();
            response = Response.status(404).entity(new ExceptionMessage(404,"Error agregando")).build();
        }

        return response;
    }
}
