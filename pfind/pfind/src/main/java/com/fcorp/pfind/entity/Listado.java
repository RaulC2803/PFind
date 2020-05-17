package com.fcorp.pfind.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Listado")
public class Listado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Cliente_Id")
    private Cliente cliente;

    private int cantidad;
    private double precio_total;

    @OneToMany(mappedBy = "listado", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "producto_id")
    private List<Bodega_Producto> bodega_producto;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Bodega_Producto> getBodega_producto() {
        return bodega_producto;
    }

    public void setBodega_producto(List<Bodega_Producto> bodega_producto) {
        this.bodega_producto = bodega_producto;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }
}
