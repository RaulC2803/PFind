package com.fcorp.pfind.entity;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Cliente")
public class Cliente extends Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String apellido;
    @Column(nullable = false)
    private int edad;
    private String distrito;

    @OneToOne(mappedBy = "cliente")
    Listado listado;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Reseña> reseñas;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Listado getListado() {
        return listado;
    }

    public void setListado(Listado listado) {
        this.listado = listado;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Reseña> getReseñas() {
        return reseñas;
    }

    public void setReseñas(List<Reseña> reseñas) {
        this.reseñas = reseñas;
    }
}
