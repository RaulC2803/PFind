package com.fcorp.pfind.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.zip.Deflater;

@Entity
@Table(name = "Cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String nombre;
    private String apellido;
    @Column(unique=true)
    private String email;
    private String password;
    @Column(nullable = false)
    private Long edad;
    private String distrito;
    private Double precio_lista;
    private int cantidad;
    @Column(name = "imagen",length = 100000)
    private byte[] imagen;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"cliente"}, allowSetters = true)
    private List<Lista_Producto> lista_productos;

    public Cliente() throws IOException {
        super();
        this.precio_lista = 0.0;
        this.cantidad = 0;
    }

    public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

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

    public Double getPrecio_lista() {
        return precio_lista;
    }

    public void setPrecio_lista(Double precio_lista) {
        this.precio_lista = precio_lista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @JsonIgnore()
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Resena> resenas;

    public Long getEdad() {
        return edad;
    }

    public void setEdad(Long edad) {
        this.edad = edad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public List<Lista_Producto> getLista_productos() {
        return lista_productos;
    }

    public void setLista_productos(List<Lista_Producto> lista_productos) {
        this.lista_productos = lista_productos;
    }
}
