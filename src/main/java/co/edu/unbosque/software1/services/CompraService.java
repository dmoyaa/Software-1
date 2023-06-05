package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.CompraDto;
import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.ProductoDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraService {

    private Conexion conexion;
    private PreparedStatement prestmt = null;
    private Statement stmt = null;
    private CompraDto compra = null;
    private ProductoDto productoDto = null;

    public List<ProductoDto> listaProductosProveedor(String nit) throws SQLException {
        List<ProductoDto> lista= null;
        conexion = new Conexion();
        int rsId;
        String rsNombre;
        int rsStockMin;
        int rsStock;
        String rsId_proveedor;
        double rsPrecioVenta;
        double rsPrecioCompra;
        String rsDetalle;

        try{
            String sql = "SELECT * FROM producto where nit_proveedor = ?";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1,nit);
            ResultSet rs = prestmt.executeQuery();
            lista = new ArrayList<>();

            while (rs.next()){
                rsId = rs.getInt("id_producto");
                rsNombre = rs.getString("nombre");
                rsStockMin = rs.getInt("stockMin");
                rsStock = rs.getInt("stock");
                rsId_proveedor = rs.getString("nit_proveedor");
                rsPrecioVenta = rs.getDouble("precio_venta");
                rsPrecioCompra = rs.getDouble("precio_compra");
                rsDetalle = rs.getString("detalle");
                productoDto = new ProductoDto(rsId, rsNombre, rsStockMin, rsStock, rsId_proveedor, rsPrecioVenta
                ,rsPrecioCompra, rsDetalle);
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

    public boolean agregarCompra(CompraDto compraDto) throws SQLException {
        boolean respuesta;
        PreparedStatement prestmt2 = null;
        conexion=new Conexion();
        int new_id = 0;
        int forma_pago = 0;
        if ((compraDto.getForma_pago().toUpperCase()).equals("CONTADO")){
            forma_pago=1;
        }else if ((compraDto.getForma_pago().toUpperCase()).equals("CRÉDITO")){
            forma_pago = 2;
        }
        try {
            conexion.conectar();
            Connection conn = conexion.getConnection();
            String id = "select max(id_compra +1) from compra";

            String sql = "Insert into compra values (?,?,?,?,?,?,?)";
            prestmt2 = conn.prepareStatement(id);
            ResultSet rs = prestmt2.executeQuery();
            while (rs.next()){
                new_id = rs.getInt("max(id_compra +1)");
            }
            prestmt = conn.prepareStatement(sql);
            prestmt.setInt(1,new_id);
            prestmt.setDate(2, (Date) compraDto.getDate());
            prestmt.setInt(3, forma_pago);
            prestmt.setString(4, compraDto.getId_proveedor());
            prestmt.setDouble(5, compraDto.getTotal());
            prestmt.setString(6, String.valueOf(compraDto.getFecha_pago()));
            prestmt.setString(7, String.valueOf(compraDto.getEstado()));

            prestmt.executeUpdate();
            respuesta = true;
            prestmt2.close();
            prestmt.close();
            conn.close();
            conexion.desconectar();
        }catch (Exception e){
            e.printStackTrace();
            prestmt.close();
            conexion.desconectar();
            respuesta=false;
        }
        return respuesta;
    }
}
