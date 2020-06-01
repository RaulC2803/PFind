package com.fcorp.pfind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nombre;
    private String marca;
    private String imagenprin;


	@ManyToOne()
    @JoinColumn(name = "Categoria_Id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    List<Bodega_Producto> bodega_productos;

    public String getMarca() {
    	return marca;
    }
    
    public void setMarca(String marca) {
    	this.marca = marca;
    }
    
    public String getImagenprin() {
    	return imagenprin;
    }
    
    public void setImagenprin(String imagenprin) {
    	this.imagenprin = imagenprin;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Bodega_Producto> getBodega_productos() {
        return bodega_productos;
    }

    public void setBodega_productos(List<Bodega_Producto> bodega_productos) {
        this.bodega_productos = bodega_productos;
    }
}
