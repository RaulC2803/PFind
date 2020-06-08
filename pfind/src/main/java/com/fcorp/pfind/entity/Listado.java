package com.fcorp.pfind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Listado")
public class Listado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Cliente_Id")
    @JsonIgnore
    private Cliente cliente;

    private int cantidad;
    private double precio_total;

    @OneToOne(cascade = CascadeType.ALL)
    private Bodega_Producto bodega_producto;

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

    public Bodega_Producto getBodega_producto() {
        return bodega_producto;
    }

    public void setBodega_producto(Bodega_Producto bodega_producto) {
        this.bodega_producto = bodega_producto;
    }

    public double getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(double precio_total) {
        this.precio_total = precio_total;
    }
}
