package com.fcorp.pfind.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

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

    @Column(name = "imagen", length = 100000)
    private byte[] imagen;
    @JsonIgnoreProperties(value = {"resena"}, allowSetters = true)
    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;

    @OneToMany(mappedBy = "bodega_producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Lista_Producto> lista_productos;

    public List<Lista_Producto> getLista_productos() {
        return lista_productos;
    }

    public void setLista_productos(List<Lista_Producto> lista_productos) {
        this.lista_productos = lista_productos;
    }

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

}
