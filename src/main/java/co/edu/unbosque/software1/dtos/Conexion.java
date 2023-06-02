package co.edu.unbosque.software1.dtos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private String nombre;
    private String login;
    private String password;
    private String url;
    private Connection connection;

    public Conexion(){
        nombre = "papeleria";
        login = "david";
        password = "1021392025Dm";
        url = "jdbc:mysql://localhost:3306/"+nombre;
    }
    public void conectar() {
        try {
            final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(url, login, password);
        }catch (ClassNotFoundException e){
            System.out.println("Error en carga de driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexión.");
        }
    }
    public void desconectar(){
        try {
            connection.close();
            System.out.println("Conexión cerrada correctamente.");
        } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
    }
    }

    public Connection getConnection() {
        return connection;
    }
}
