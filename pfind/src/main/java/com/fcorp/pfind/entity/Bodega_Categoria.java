package com.fcorp.pfind.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Bodega_Categoria")
public class Bodega_Categoria {
    @ManyToOne()
    @JoinColumn(name = "Bodega_Id")
    private Bodega bodega;

    @ManyToOne()
    @JoinColumn(name = "Categoria_Id")
    private Categoria categoria;

}
