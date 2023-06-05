package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.UsuarioDto;
import co.edu.unbosque.software1.dtos.UsuarioDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioService {

    private UsuarioDto usuario = null;
    private Conexion conexion;
    private PreparedStatement prestmt = null;

    public UsuarioService(){
        conexion = new Conexion();
    }

    public UsuarioDto consultarUsuario(String username){
        String loginrs;
        String passrs;
        String rolrs;
        try {

            conexion.conectar();
            Connection conn = conexion.getConnection();
            String consulta = "Select * from usuario where login = ?";
            prestmt =conn.prepareStatement(consulta);
            prestmt.setString(1,username);
            ResultSet rs=prestmt.executeQuery();

            while (rs.next()){
                loginrs =rs.getString("login");
                passrs = rs.getString("pass");
                rolrs = rs.getString("id_rol");

                usuario = new UsuarioDto(loginrs,passrs,rolrs);
                System.out.println(usuario);
            }

            rs.close();
            prestmt.close();
            conn.close();
            conexion.desconectar();

            if (usuario != null){
                return usuario;
            }
            else{
                System.out.println("Usuario  no encontrado");
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
