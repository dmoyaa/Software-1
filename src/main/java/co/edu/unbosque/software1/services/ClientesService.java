    package co.edu.unbosque.software1.services;

    import co.edu.unbosque.software1.dtos.ClienteDto;
    import co.edu.unbosque.software1.dtos.Conexion;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

    public class ClientesService {
        Conexion connector = new Conexion();
        Statement stmt = null;
        PreparedStatement prestmt=null;
        public List <ClienteDto> obtenerClientes(){
            connector.conectar();
            Connection conexion= connector.getConnection();

            List<ClienteDto> clientes = new ArrayList<ClienteDto>() ;


            try {
                String sql = "SELECT * FROM papeleria.cliente where estado = 'A'";
                stmt = conexion.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                String cedula;
                String nombre;
                String direccion;
                String correo;
                String telefono;
                String estado;



                while (rs.next()) {
                    cedula =rs.getString("cedula");
                    nombre = rs.getString("nombre");
                    direccion = rs.getString("apellido");
                    correo = rs.getString("direccion");
                    telefono = rs.getString("telefono");
                    estado = rs.getString("estado");

                    ClienteDto temp = new ClienteDto(cedula,nombre,direccion,correo,telefono,estado);
                    clientes.add(temp);
                    temp.toString();
                }

                rs.close();
                stmt.close();
                connector.desconectar();
            } catch (SQLException e) {
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

        public ClienteDto ciente(String cedulacliente){
            ClienteDto cliente = null;
            String rsNombre;
            String rsDireccion;
            String rsApellido;
            String rsTelefono;
            String rsEstado;
            try {
                connector.conectar();
                Connection conn = connector.getConnection();
                String sql = "Select * from cliente where cedula = ?";
                prestmt = conn.prepareStatement(sql);
                prestmt.setString(1,cedulacliente);
                ResultSet rs = prestmt.executeQuery();

                while (rs.next()){
                    rsNombre = rs.getString("nombre");
                    rsDireccion = rs.getString("direccion");
                    rsApellido = rs.getString("apellido");
                    rsTelefono = rs.getString("telefono");
                    rsEstado = rs.getString("estado");
                    cliente= new ClienteDto(cedulacliente,rsNombre,rsApellido,rsDireccion,rsTelefono,rsEstado);
                }

                System.out.println(cliente);

                rs.close();
                prestmt.close();
                conn.close();
                connector.desconectar();

            }catch (SQLException e){
               connector.desconectar();
            }
            return cliente;
        }

        public Boolean update(String cedulacliente,String name,String latname,String address, String phone){
            System.out.println("-------------------------------------los parametros enviados son "+ latname+" "+address+" "+phone);

            try {
                ClienteDto temp=ciente(cedulacliente);
                if(temp==null){
                    return false;
                }
                System.out.println(temp);connector.conectar();
                Connection conexion= connector.getConnection();
                String sql1 = "UPDATE cliente SET nombre = ?, apellido = ?,direccion = ?,telefono = ? WHERE cedula = ? AND estado = ?";
                prestmt= conexion.prepareStatement(sql1);
                prestmt.setString(1,name);
                prestmt.setString(2,latname);
                prestmt.setString(3,address);
                prestmt.setString(4,phone);
                prestmt.setString(5,temp.getCedula());
                prestmt.setString(6,temp.getEstado());
                int n=prestmt.executeUpdate();
                if(n==1){
                    return true;
                }else {
                    prestmt.close();
                    connector.desconectar();
                    return false;
                }

            } catch (SQLException e) {
                System.out.println("-------------------VALILMOS VERGA-------------------");
                e.printStackTrace();
                return false;
            }finally {
                try {
                    if (stmt != null) stmt.close();
                    connector.desconectar();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

        }

        public Boolean delete(String cedulacliente){

            try {
                ClienteDto temp=ciente(cedulacliente);
                if(temp==null){
                    return false;
                }
                temp.setEstado("I");
                System.out.println(temp);
                connector.conectar();
                Connection conexion= connector.getConnection();
                String sql1 = "UPDATE cliente SET estado = ? WHERE cedula = ?";
                prestmt= conexion.prepareStatement(sql1);
                prestmt.setString(1,temp.getEstado());
                prestmt.setString(2,temp.getCedula());
                int n=prestmt.executeUpdate();
                if(n==1){
                    return true;
                }else {
                    prestmt.close();
                    connector.desconectar();
                    return false;
                }

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally {
                try {
                    if (stmt != null) stmt.close();
                    connector.desconectar();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

        }

        public Boolean insert(String cedulacliente,String name,String latname,String address, String phone){
            try {
                connector.conectar();
                Connection conexion= connector.getConnection();
                ClienteDto temp= ciente(cedulacliente);
                if(temp !=null){
                    temp.setEstado("A");
                    System.out.println(temp);
                    String sql1 = "UPDATE cliente SET nombre = ?, apellido = ?,direccion = ?,telefono = ?, estado = ? WHERE cedula = ? ";
                    prestmt= conexion.prepareStatement(sql1);
                    prestmt.setString(1,name);
                    prestmt.setString(2,latname);
                    prestmt.setString(3,address);
                    prestmt.setString(4,phone);
                    prestmt.setString(5,temp.getEstado());
                    prestmt.setString(6,temp.getCedula());
                    int n=prestmt.executeUpdate();
                    if(n==1){
                        return true;
                    }else {
                        prestmt.close();
                        connector.desconectar();
                        return false;
                    }
                }
                String sql = "INSERT INTO cliente VALUES (?,?,?,?,?,)";
                prestmt = conexion.prepareStatement(sql);
                prestmt.setString(1, cedulacliente);
                prestmt.setString(2,name);
                prestmt.setString(3,latname);
                prestmt.setString(4,address);
                prestmt.setString(5,phone);

                int n= prestmt.executeUpdate();
                if (n==1){
                    prestmt.close();
                    connector.desconectar();
                    return true;
                }else {
                    prestmt.close();
                    connector.desconectar();
                    return false;
                }



            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }finally {
                try {
                    if (stmt != null) stmt.close();
                    connector.desconectar();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

        }

    }
