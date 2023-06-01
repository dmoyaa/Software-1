package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.Cliente;
import co.edu.unbosque.software1.dtos.DatabaseConector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Clientes {
    DatabaseConector connector = new DatabaseConector();

    public List <Cliente> obtenerClientes(){
        Connection conexión = connector.obtenerConexion();
        Statement stmt = null;
        List<Cliente> clientes = new ArrayList<Cliente>() ;


        try {
            String sql = "SELECT * FROM cliente ";
            stmt = conexión.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            String cedula;
            String nombre;
            String direccion;
            String correo;
            String telefono;



            while (rs.next()) {
                cedula =rs.getString("cedula");
                nombre = rs.getString("nombre");
                direccion = rs.getString("apellido");
                correo = rs.getString("direccion");
                telefono = rs.getString("telefono");

                Cliente temp = new Cliente(cedula,nombre,direccion,correo,telefono);
                clientes.add(temp);
                temp.toString();
            }

            rs.close();
            stmt.close();
            connector.desconectar();
        } catch (SQLException e) {
            System.out.println("-------------------VALILMOS VERGA-------------------");
            e.printStackTrace();
        }finally {

            try {
                if (stmt != null) stmt.close();
               connector.desconectar();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return clientes;
    }


}
