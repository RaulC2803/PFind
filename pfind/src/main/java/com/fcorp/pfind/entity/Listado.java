package com.fcorp.pfind.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Listado")
public class Listado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @OneToOne(mappedBy = "listado")
    @Column(name = "cliente_id")
    private Cliente cliente;
    private int cantidad;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
