package co.edu.unbosque.software1.resources;

import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.ProveedorDto;

import java.sql.*;
import java.util.List;

public class ProveedorService {

    private ProveedorDto proveedor = null;
    private Conexion conexion;
    private PreparedStatement prestmt = null;
    private Statement stmt = null;

    public ProveedorService(){
        conexion = new Conexion();
    }

    public List<ProveedorDto> proveedor() throws SQLException {
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

            while (rs.next()){
                rsNit = rs.getString("nit_proveedor");
                rsNombre = rs.getString("nombre");
                rsDireccion = rs.getString("direccion");
                rsCorreo = rs.getString("correo");
                rsTelefono = rs.getString("telefono");
                proveedor=new ProveedorDto(rsNit,rsNombre,rsDireccion,rsCorreo,rsTelefono);
                lista.add(proveedor);
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
