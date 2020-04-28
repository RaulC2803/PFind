package com.fcorp.pfind.entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import sun.security.util.Length;

import javax.persistence.*;
import java.util.List;

public class Bodega extends Usuario{
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
    @Column(name = "categoria_id")
    private List<Categoria> categorias;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "productos_id")
    private List<Producto> productos;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "reseñas_id")
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

    public List<Categorias> getCate_productos() {
        return cate_productos;
    }

    public void setCate_productos(List<Categorias> cate_productos) {
        this.cate_productos = cate_productos;
    }

    public boolean isAgente_bancario() {
        return agente_bancario;
    }

    public void setAgente_bancario(boolean agente_bancario) {
        this.agente_bancario = agente_bancario;
    }

    @Override
    public Long getCodigo() {
        return codigo;
    }

    @Override
    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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
