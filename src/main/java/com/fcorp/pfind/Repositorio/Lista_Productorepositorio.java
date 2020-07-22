package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Lista_Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Lista_Productorepositorio extends JpaRepository<Lista_Producto,Long> {
    @Query("select lp from Lista_Producto lp Where lp.cliente.codigo = :id")
    List<Lista_Producto> busquedaPorCliente(@Param("id") Long id);

}
