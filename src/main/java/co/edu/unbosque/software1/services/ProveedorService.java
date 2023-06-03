package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.ProveedorDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorService {

    private ProveedorDto proveedor = null;
    private Conexion conexion;
    private PreparedStatement prestmt = null;
    private Statement stmt = null;

    public ProveedorService(){

        conexion = new Conexion();
    }

    public ProveedorDto buscarProveedor(String nit){
        String rsNombre;
        String rsDireccion;
        String rsCorreo;
        String rsTelefono;
        try {
            conexion.conectar();
            Connection conn = conexion.getConnection();
            String sql = "Select * from proveedor where nit_proveedor = ?";
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1,nit);
            ResultSet rs = prestmt.executeQuery();

            while (rs.next()){
                rsNombre=rs.getString("nombre");
                rsDireccion = rs.getString("direccion");
                rsCorreo = rs.getString("correo");
                rsTelefono = rs.getString("telefono");
                proveedor = new ProveedorDto(nit,rsNombre,rsDireccion,rsCorreo,rsTelefono);
            }

            System.out.println(proveedor);

            rs.close();
            prestmt.close();
            conn.close();
            conexion.desconectar();

        }catch (SQLException e){
            conexion.desconectar();
        }
        return proveedor;
    }

    public List<ProveedorDto> listaProveedores() throws SQLException {
        List<ProveedorDto> lista= null;
        String rsNit;
        String rsNombre;
        String rsDireccion;
        String rsCorreo;
        String rsTelefono;

        try{
            String sql = "SELECT * FROM PROVEEDOR";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery();
            lista = new ArrayList<>();

            while (rs.next()){
                rsNit = rs.getString("nit_proveedor");
                rsNombre = rs.getString("nombre");
                rsDireccion = rs.getString("direccion");
                rsCorreo = rs.getString("correo");
                rsTelefono = rs.getString("telefono");
                proveedor=new ProveedorDto(rsNit,rsNombre,rsDireccion,rsCorreo,rsTelefono);
                lista.add(proveedor);
                System.out.println(proveedor.getNombre());
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

    public boolean actualizarProveedor(ProveedorDto proveedor) {
        boolean respuesta=true;
        try{
            String sql = "UPDATE proveedor set nombre = ?, direccion = ?, correo = ?, telefono= ? where nit_proveedor = ?";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1,proveedor.getNombre());
            prestmt.setString(2,proveedor.getDireccion());
            prestmt.setString(3,proveedor.getCorreo());
            prestmt.setString(4,proveedor.getTelefono());
            prestmt.setString(5,proveedor.getNit());
            prestmt.executeUpdate();
            respuesta = true;
            prestmt.close();
            conn.close();
            conexion.desconectar();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            respuesta = false;
        }
        return respuesta;
    }

    public boolean eliminarProveedor(String nit){
        boolean respuesta;
        try{
            String sql = "update proveedor set estado = 'I'";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.executeUpdate();
            respuesta=true;
            prestmt.close();
            conn.close();
            conexion.desconectar();
        }catch (SQLException e) {
            e.printStackTrace();
            respuesta = false;
        }
        return respuesta;
    }

    public boolean agregarProveedor(ProveedorDto proveedor) {
        boolean respuesta;
        try{
            String sql = "INSERT INTO proveedor values (?,?,?,?,?,?)";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1,proveedor.getNit());
            prestmt.setString(2,proveedor.getNombre());
            prestmt.setString(3,proveedor.getDireccion());
            prestmt.setString(4,proveedor.getCorreo());
            prestmt.setString(5,proveedor.getTelefono());
            prestmt.setString(6,"A");
            prestmt.executeUpdate();
            respuesta = true;
            prestmt.close();
            conn.close();
            conexion.desconectar();
        }catch (Exception e){
            e.printStackTrace();
            respuesta = false;
        }
        return respuesta;
    }
}
