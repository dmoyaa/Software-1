package co.edu.unbosque.software1.services;

import co.edu.unbosque.software1.dtos.ClienteDto;
import co.edu.unbosque.software1.dtos.Conexion;
import co.edu.unbosque.software1.dtos.VentaDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VentasService {
    Conexion connector = new Conexion();
    Statement stmt = null;
    PreparedStatement prestmt = null;

    public List<VentaDto> obtenerVentas(Date fecha, String cliente, boolean check) {
        System.out.println("lo que recibio es " + fecha + "elcliente" + cliente + " " + check);
        int tipoResultado = 0;
        VentaDto venta = null;
        int rsIdVenta;
        Date rsFechaVenta;
        String rsCliente;
        int rsTotal;
        int rsIdFormaPago;
        Date rsFechaPago;
        List<VentaDto> ventas = new ArrayList<>();
        try {
            connector.conectar();
            Connection conn = connector.getConnection();
            String sql = null;
            ResultSet rs = null;
            if ( !cliente.equals("") && !cliente.contains("DEFAULT") && !check) {
                System.out.println("----------------entra al 2220");
                sql = "Select * from venta where fecha_venta = ? AND cedula = ? AND estado ='A' ";
                prestmt = conn.prepareStatement(sql);
                prestmt.setString(1, String.valueOf(fecha));
                prestmt.setString(2, cliente);
                rs = prestmt.executeQuery();
                tipoResultado = 1;
            } else if (cliente.equals("") && !check || cliente.equals("DEFAULT") && !check) {
                System.out.println("-------------entra al 2221");
                sql = "Select * from venta where fecha_venta = ? ";
                prestmt = conn.prepareStatement(sql);
                prestmt.setString(1, String.valueOf(fecha));
                rs = prestmt.executeQuery();
                tipoResultado = 1;
            } else if (!cliente.equals("") && !cliente.contains("DEFAULT") && check) {
                System.out.println("------------entra al 2222");
                sql = "Select sum(total) as 'TOTAL COMPRAS POR CLIENTE',  cedula, fecha_venta  from venta where fecha_venta = ? AND cedula = ?  group  by cedula, fecha_venta";
                prestmt = conn.prepareStatement(sql);
                prestmt.setString(1, String.valueOf(fecha));
                prestmt.setString(2, cliente);
                rs = prestmt.executeQuery();
                tipoResultado = 2;
            } else if (cliente.equals("") && check || cliente.equals("DEFAULT") && check) {
                System.out.println("---------entra al 2223");
                sql = "Select fecha_venta, SUM(total) as 'TOTAL COMPRAS POR DIA' from venta where fecha_venta = ?  GROUP BY fecha_venta";
                prestmt = conn.prepareStatement(sql);
                prestmt.setString(1, String.valueOf(fecha));
                rs = prestmt.executeQuery();
                tipoResultado = 3;
            }


            while (rs.next()) {
                switch (tipoResultado) {
                    case 1:
                        System.out.println("");
                        rsIdVenta = rs.getInt("id_venta");
                        rsFechaVenta = rs.getDate("fecha_venta");
                        rsCliente = rs.getString("cedula");
                        rsTotal = rs.getInt("total");
                        rsFechaPago = rs.getDate("fecha_pago_venta");
                        rsIdFormaPago = rs.getInt("id_forma_pago");
                        venta= new VentaDto(rsIdVenta, rsFechaVenta, rsFechaPago, rsIdFormaPago, rsCliente, rsTotal);
                        break;
                    case 2:
                        rsFechaVenta = rs.getDate("fecha_venta");
                        rsCliente = rs.getString("cedula");
                        rsTotal = rs.getInt("TOTAL COMPRAS POR CLIENTE"); // Utiliza el alias en lugar del nombre original 'total'
                        venta = new VentaDto(rsFechaVenta, rsCliente, rsTotal);
                        break;
                    case 3:
                        rsFechaVenta = rs.getDate("fecha_venta");
                        rsTotal = rs.getInt("TOTAL COMPRAS POR DIA"); // Utiliza el alias en lugar del nombre original 'total'
                        venta = new VentaDto(rsFechaVenta, rsTotal);
                        break;

                    default:
                        // Código a ejecutar si no se cumple ninguno de los casos anteriores
                        break;
                }

                ventas.add(venta);
            }

            rs.close();
            prestmt.close();
            connector.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (prestmt != null) prestmt.close();
                connector.desconectar();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return ventas;
    }




    public List<VentaDto> obtenerVentasNoPagadas() {
        connector.conectar();
        Connection conexion = connector.getConnection();
        LocalDate fechaActual = LocalDate.now();
        VentaDto venta = null;
        int rsIdVenta;
        Date rsFechaVenta;
        String rsCliente;
        int rsTotal;
        int rsIdFormaPago;
        Date rsFechaPago;
        List<VentaDto> ventaspp = new ArrayList<VentaDto>();


        try {
            String sql = "SELECT * FROM venta where estado = 'A'";
            stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                rsIdVenta = rs.getInt("id_venta");
                rsFechaVenta = rs.getDate("fecha_venta");
                rsCliente = rs.getString("cedula");
                rsTotal = rs.getInt("total");
                rsFechaPago = rs.getDate("fecha_pago_venta");
                rsIdFormaPago = rs.getInt("id_forma_pago");
                venta = new VentaDto(rsIdVenta, rsFechaVenta, rsFechaPago, rsIdFormaPago, rsCliente, rsTotal);
                ventaspp.add(venta);
            }

            rs.close();
            stmt.close();
            connector.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stmt != null) stmt.close();
                connector.desconectar();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return ventaspp;
    }

    public Boolean delete(String idventa){

        try {

            connector.conectar();
            Connection conexion= connector.getConnection();
            String sql1 = "UPDATE venta SET estado = 'I' WHERE id_venta = ?";
            prestmt= conexion.prepareStatement(sql1);
            prestmt.setString(1,idventa);
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

}
