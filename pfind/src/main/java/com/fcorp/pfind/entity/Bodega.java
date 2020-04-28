package com.fcorp.pfind.entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import sun.security.util.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Bodega")
public class Bodega extends Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(length = 11)
    private String ruc;
    private String nombre;
    private String direccion;
    private int aforo;
    private String agencia_bancaria;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Producto> productos;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reseña> reseña;

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getAgencia_bancaria() {
        return agencia_bancaria;
    }

    public void setAgencia_bancaria(String agencia_bancaria) {
        this.agencia_bancaria = agencia_bancaria;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Reseña> getReseña() {
        return reseña;
    }

    public void setReseña(List<Reseña> reseña) {
        this.reseña = reseña;
    }
}
