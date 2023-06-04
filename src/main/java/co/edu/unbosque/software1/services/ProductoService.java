package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.ProductoDto;
import co.edu.unbosque.software1.dtos.ProveedorDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoService {

    private ProductoDto productoDto = null;
    private Conexion conexion;
    private PreparedStatement prestmt = null;
    private Statement stmt = null;


    public ProductoService() {
        conexion = new Conexion();
    }

    public ProductoDto consultarProducto(int id_producto) {
        int id_productors;
        String nombrers;
        int stockMinrs;
        int stockrs;
        String nit_proveedorrs;
        double precio_ventars;
        double precio_comprars;
        String detallers;

        try {
            conexion.conectar();
            Connection conn = conexion.getConnection();
            String consulta = "SELECT * from producto where id_producto = ?";
            prestmt = conn.prepareStatement(consulta);
            prestmt.setInt(1, id_producto);
            ResultSet rs = prestmt.executeQuery();

            while (rs.next()) {
                id_productors = rs.getInt("id_producto");
                nombrers = rs.getString("nombre");
                stockMinrs = rs.getInt("stockMin");
                stockrs = rs.getInt("stock");
                nit_proveedorrs = rs.getString("nit_proveedor");
                precio_ventars = rs.getDouble("precio_venta");
                precio_comprars = rs.getDouble("precio_compra");
                detallers = rs.getString("detalle");

                productoDto = new ProductoDto(id_productors, nombrers, stockMinrs, stockrs, nit_proveedorrs, precio_ventars, precio_comprars,
                        detallers);
                System.out.println(productoDto);
            }

            rs.close();
            prestmt.close();
            conn.close();
            conexion.desconectar();


        } catch (SQLException e) {
            conexion.desconectar();
            //return null;
        }
        return productoDto;
    }


    public List<ProductoDto> listaProductos() throws SQLException {
        List<ProductoDto> lista = null;
        int rsId_producto;
        String rsnombre;
        int rsStockMin;
        int rsStock;
        String rsNit_proveedor;
        double rsPrecio_venta;
        double rsPrecio_compra;
        String rsdetalle;

        try {
            String sql = "SELECT * FROM producto";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery();
            lista = new ArrayList<>();

            while (rs.next()) {
                rsId_producto = rs.getInt("id_producto");
                rsnombre = rs.getString("nombre");
                rsStockMin = rs.getInt("stockMin");
                rsStock = rs.getInt("stock");
                rsNit_proveedor = rs.getString("nit_proveedor");
                rsPrecio_venta = rs.getDouble("precio_venta");
                rsPrecio_compra = rs.getDouble("precio_compra");
                rsdetalle = rs.getString("detalle");

                productoDto = new ProductoDto(rsId_producto, rsnombre, rsStockMin, rsStock, rsNit_proveedor, rsPrecio_venta, rsPrecio_compra,
                        rsdetalle);
                lista.add(productoDto);
                System.out.println(productoDto.getNombre());
            }

            rs.close();
            prestmt.close();
            conn.close();
            conexion.desconectar();

            return lista;

        } catch (SQLException e) {
            e.printStackTrace();
            prestmt.close();
            conexion.desconectar();
        }
        return lista;
    }

    public boolean actualizarProducto(ProductoDto producto) {
        boolean respuesta = true;
        try {
            String sql = "UPDATE producto set nombre = ?, stock = ?, precio_venta = ?, precio_compra = ?";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, producto.getNombre());
            prestmt.setInt(2, producto.getStock());
            prestmt.setDouble(3, producto.getPrecio_venta());
            prestmt.setDouble(4, producto.getPrecio_compra());
            prestmt.executeUpdate();
            respuesta = true;
            prestmt.close();
            conn.close();
            conexion.desconectar();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta = false;
        }

        return respuesta;
    }


    public boolean agregarProducto(ProductoDto producto) {
        boolean respuesta = true;
        System.out.println("Entra en crear producto");
        try {
            if (consultarProducto(producto.getId_producto()) == null) {
                String insert = "INSERT INTO producto values (?,?,?,?,?,?,?,?)";
                conexion.conectar();
                Connection conn = conexion.getConnection();
                prestmt = conn.prepareStatement(insert);

                prestmt.setInt(1, producto.getId_producto());
                prestmt.setString(2, producto.getNombre());
                prestmt.setInt(3, 5);
                prestmt.setInt(4, producto.getStock());
                prestmt.setString(5, producto.getNit_proveedor());
                prestmt.setDouble(6, producto.getPrecio_venta());
                prestmt.setDouble(7, producto.getPrecio_compra());
                prestmt.setString(8, "");
                prestmt.executeUpdate();
                respuesta = true;
                prestmt.close();
                conn.close();
                conexion.desconectar();
            } else {
                respuesta = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            respuesta = false;
        }
        return respuesta;
    }

    public boolean eliminarProducto(int id_producto) {
        boolean respuesta;
        try {
            String sql = "delete from producto where id_producto = ?";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setInt(1, productoDto.getId_producto());
            prestmt.executeUpdate();
            respuesta = true;
            prestmt.close();
            conn.close();
            conexion.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            respuesta = false;
        }

        return respuesta;
    }
}
