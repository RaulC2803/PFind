package com.fcorp.pfind.entity;

import javax.persistence.*;

@Entity
@Table(name = "Bodega_Producto")
public class Bodega_Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne()
    @JoinColumn(name = "Producto_Id")
    private Producto producto;

    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;

    @ManyToOne()
    @JoinColumn(name = "Listado_Id")
    private Listado listado;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Listado getListado() {
        return listado;
    }

    public void setListado(Listado listado) {
        this.listado = listado;
    }

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
