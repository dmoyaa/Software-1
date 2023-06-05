package co.edu.unbosque.software1.dtos;

import java.util.Date;

public class CompraDto {

    private int id;
    private Date date;
    private String  id_proveedor;
    private double total;
    private String forma_pago;
    private Date fecha_pago;
    private String detalle;
    private char estado;

    public CompraDto(int id, Date date, String nit, double total, String forma_pago, Date fecha_pago, String detalle, char estado){

        this.id = id;
        this.date = date;
        this.id_proveedor = nit;
        this.total = total;
        this.forma_pago = forma_pago;
        this.fecha_pago = fecha_pago;
        this.detalle = detalle;
        this.estado = estado;

    }

    public Date getDate() {
        return date;
    }

    public char getEstado() {
        return estado;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public double getTotal() {
        return total;
    }

    public int getId() {
        return id;
    }

    public String getDetalle() {
        return detalle;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public String getId_proveedor() {
        return id_proveedor;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public void setId_proveedor(String id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
