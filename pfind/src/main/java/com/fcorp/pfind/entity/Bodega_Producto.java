package com.fcorp.pfind.entity;

import javax.persistence.*;

@Entity
@Table(name = "Bodega_Producto")
public class Bodega_Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    private Double precio;
    
    private String Descripcion;
    @ManyToOne()
    @JoinColumn(name = "Producto_Id")
    private Producto producto;

    @OneToOne(cascade = CascadeType.ALL)
    private Listado listado;

    @Column(name = "imagen", length = 100000)
    private byte[] imagen;

    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;
    
    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Listado getListado() {
        return listado;
    }

    public void setListado(Listado listado) {
        this.listado = listado;
    }
}
