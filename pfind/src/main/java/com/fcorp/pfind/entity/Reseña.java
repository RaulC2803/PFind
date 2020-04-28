package com.fcorp.pfind.entity;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Reseña")
public class Reseña {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(length = 100)
    private String contenido;
    @Size(min = 0, max = 5)
    private int calificacion;

    @ManyToOne()
    @JoinColumn(name = "Cliente_Id")
    private Cliente cliente;

    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }
}
