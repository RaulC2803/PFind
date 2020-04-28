package com.fcorp.pfind.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bodega_Producto")
public class Bodega_Producto {
    @ManyToOne()
    @JoinColumn(name = "Producto_Id")
    private Producto producto;

    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;

    @ManyToOne()
    @JoinColumn(name = "Listado_Id")
    private Listado listado;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
}
