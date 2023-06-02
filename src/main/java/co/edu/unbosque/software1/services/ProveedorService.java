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
}
