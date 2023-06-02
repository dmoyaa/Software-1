    package co.edu.unbosque.software1.services;

    import co.edu.unbosque.software1.dtos.Cliente;
    import co.edu.unbosque.software1.dtos.Conexion;

    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;

    public class Clientes {
        Conexion connector = new Conexion();

        public List <Cliente> obtenerClientes(){
            connector.conectar();
            Connection conexion= connector.getConnection();
            Statement stmt = null;
            List<Cliente> clientes = new ArrayList<Cliente>() ;


            try {
                String sql = "SELECT * FROM cliente ";
                stmt = conexion.createStatement();
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

        public Cliente ciente(String cedulacliente){
            Cliente cliente=null;
            List <Cliente> clientes= obtenerClientes();
            for (Cliente cl : clientes) {
                if (cedulacliente.equals(cl.getCedula())) {
                    cliente =cl;
                }
            }
            return cliente;
        }


    }
