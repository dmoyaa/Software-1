package co.edu.unbosque.software1.dtos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConector {
    private static final String URL = "jdbc:mysql://localhost:3306/papeleria";
    private static final String USUARIO = "maria";
    private static final String CONTRASEÑA = "123456";
    private Connection conexión;


    public Connection obtenerConexion() {
        if (conexión == null) {
            conectar();
        }
        return conexión;
    }

    private void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexión = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error al establecer la conexión: " + e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (conexión != null && !conexión.isClosed()) {
                conexión.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
}