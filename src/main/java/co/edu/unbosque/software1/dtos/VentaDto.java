package co.edu.unbosque.software1.dtos;

import java.util.Date;

public class VentaDto {
    private int id_venta;
    private Date fecha_venta;
    private Date fecha_pago;
    private int forma_de_pago;
    private String cliente;
    private int total;

    public VentaDto() {

    }

    public VentaDto(Date fecha_venta, Date fecha_pago, int forma_de_pago, String cliente, int total) {
        this.fecha_venta = fecha_venta;
        this.fecha_pago = fecha_pago;
        this.forma_de_pago = forma_de_pago;
        this.cliente = cliente;
        this.total = total;
    }

    public VentaDto(Date fecha_venta, String cliente, int total) {
        this.fecha_venta = fecha_venta;
        this.cliente = cliente;
        this.total = total;
    }

    public VentaDto(Date fecha_venta, int total) {
        this.fecha_venta = fecha_venta;
        this.total = total;
    }

    public VentaDto(int id_venta, Date fecha_venta, Date fecha_pago, int forma_de_pago, String cliente, int total) {
        this.id_venta = id_venta;
        this.fecha_venta = fecha_venta;
        this.fecha_pago = fecha_pago;
        this.forma_de_pago = forma_de_pago;
        this.cliente = cliente;
        this.total = total;
    }

    @Override
    public String toString() {
        return "VentaDto{" +
                "id_venta=" + id_venta +
                ", fecha_venta=" + fecha_venta +
                ", fecha_pago=" + fecha_pago +
                ", forma_de_pago=" + forma_de_pago +
                ", cliente='" + cliente + '\'' +
                ", total=" + total +
                '}';
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public int getForma_de_pago() {
        return forma_de_pago;
    }

    public void setForma_de_pago(int forma_de_pago) {
        this.forma_de_pago = forma_de_pago;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
