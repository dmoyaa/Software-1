package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.UsuarioDto;
import co.edu.unbosque.software1.dtos.UsuarioDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UsuarioService {

    private UsuarioDto usuario = null;
    private Conexion conexion;
    private PreparedStatement prestmt = null;
    private PreparedStatement prestmt2 = null;

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

    public List<UsuarioDto> listaUsuarios() throws SQLException{
        List<UsuarioDto> lista = null;
        String rsLogin;
        String rsPassword;
        String rsRol;

        try {
            String sql = "SELECT * FROM usuario";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            ResultSet rs = prestmt.executeQuery();
            lista = new ArrayList<>();

            while (rs.next()){
                rsLogin = rs.getString("login");
                rsPassword = rs.getString("pass");
                rsRol = rs.getString("id_rol");
                usuario = new UsuarioDto(rsLogin,rsPassword,rsRol);
                lista.add(usuario);
                System.out.println(usuario.getLogin());
            }

            rs.close();
            prestmt.close();
            conn.close();
            conexion.desconectar();

            return lista;
        }
        catch (SQLException e){
            e.printStackTrace();
            prestmt.close();
            conexion.desconectar();
        }

        return lista;
    }

    public boolean actualizarUsuario(UsuarioDto usuario){
        boolean respuesta = true;

        try{
            String sql = "UPDATE usuario set login = ?, pass = ?, id_rol = ? where login = ?";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, usuario.getLogin());
            prestmt.setString(2, usuario.getPassword());
            prestmt.setString(3, usuario.getRol());
            prestmt.setString(4, usuario.getLogin());
            prestmt.executeUpdate();
            respuesta=true;
            prestmt.close();
            conn.close();
            conexion.desconectar();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            respuesta=false;
        }

        return respuesta;
    }

    public boolean eliminarUsuario(String login){
        boolean respuesta;
        try{
            String sql = "DELETE FROM usuario where login = ?";
            conexion.conectar();
            Connection conn = conexion.getConnection();
            prestmt = conn.prepareStatement(sql);
            prestmt.setString(1, usuario.getLogin());
            prestmt.execute();
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
    public boolean crearUsuario(UsuarioDto usuario){
        boolean respuesta;
        System.out.println("entra en crear");
        String fecha = "";

        try {
            if(consultarUsuario(usuario.getLogin())==null){
                int numero = 0;
                String insert = "INSERT INTO usuario values (?,?,?,?)";
                String max = "SELECT MAX(id_usuario+1) from usuario";
                conexion.conectar();
                Connection conn = conexion.getConnection();
                prestmt = conn.prepareStatement(insert);
                prestmt2 = conn.prepareStatement(max);

                ResultSet rs = prestmt2.executeQuery();

                while (rs.next()){
                    numero = rs.getInt("MAX(id_usuario+1)");
                }


                prestmt.setInt(1, numero);
                prestmt.setString(2, usuario.getLogin());
                prestmt.setString(3, usuario.getPassword());
                //prestmt.setDate(3, java.sql.Date.valueOf(""));
                prestmt.setString(4, usuario.getRol());
                prestmt.executeUpdate();
                respuesta = true;
                prestmt.close();
                prestmt2.close();
                rs.close();
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

}
