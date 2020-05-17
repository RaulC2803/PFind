package com.fcorp.pfind.entity;

import javax.persistence.*;

@Entity
@Table(name = "Bodega_Categoria")
public class Bodega_Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;

    @ManyToOne()
    @JoinColumn(name = "Categoria_Id")
    private Categoria categoria;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        this.bodega = bodega;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
