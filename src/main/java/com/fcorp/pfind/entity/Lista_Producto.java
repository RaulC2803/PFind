package com.fcorp.pfind.entity;

import javax.persistence.*;

@Entity
@Table(name = "Lista_Producto")
public class Lista_Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne()
    @JoinColumn(name = "Bodega_Producto_Id")
    private Bodega_Producto bodega_producto;

    @ManyToOne()
    @JoinColumn(name = "Cliente_Id")
    private Cliente cliente;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Bodega_Producto getBodega_producto() {
        return bodega_producto;
    }

    public void setBodega_producto(Bodega_Producto bodega_producto) {
        this.bodega_producto = bodega_producto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
