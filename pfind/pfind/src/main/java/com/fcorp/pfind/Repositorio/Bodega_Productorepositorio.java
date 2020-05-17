package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Bodega_Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Bodega_Productorepositorio extends JpaRepository<Bodega_Producto, Long> {
    @Query("SELECT bp FROM Bodega_Producto bp WHERE bp.producto = :proid and bp.bodega = :bodeid")
    Bodega_Producto buscarBodegaProducto(@Param("proid") Long Producto_Id,
                                         @Param("bodeid") Long Bodega_Id);
}
