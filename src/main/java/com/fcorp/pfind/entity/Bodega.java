package com.fcorp.pfind.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Bodega")
public class Bodega implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
	
    @Column(unique=true, length = 11)
    private String ruc;
    private String nombre;
    private String jefe;
    private String direccion;
    @Column(unique=true)
    private String email;
    private String password;
    private int aforo;
    private String agencia_bancaria;
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"bodega"}, allowSetters = true)
    private List<Bodega_Producto> productos;
    @JsonIgnoreProperties(value = {"bodega"}, allowSetters = true)
    @OneToMany(mappedBy = "bodega", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Resena> resena;
    
    public String getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email = email;
    }
    
    public String getPassword() {
    	return password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public Long getCodigo() {
    	return codigo;
    }
    
    public void setCodigo(Long codigo) {
    	this.codigo = codigo;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
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


    public List<Bodega_Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Bodega_Producto> productos) {
        this.productos = productos;
    }

    public List<Resena> getResena() {
        return resena;
    }

    public void setResena(List<Resena> resena) {
        this.resena = resena;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
}
