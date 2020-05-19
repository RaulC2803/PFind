package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Bodega_Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Bodega_Productorepositorio extends JpaRepository<Bodega_Producto, Long> {
    @Query("SELECT bp FROM Bodega_Producto bp WHERE bp.producto = :proid and bp.bodega = :bodeid")
    Bodega_Producto buscarBodegaProducto(@Param("proid") Long Producto_Id,
                                         @Param("bodeid") Long Bodega_Id);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.categoria.codigo = :cid")
    List<Bodega_Producto> buscarPorCategoria(@Param("cid") Long cid);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.nombre like %?1%")
    List<Bodega_Producto> buscarPorNombre(String nombre);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.nombre like %:nombre% and bp.producto.categoria.codigo = :cid")
    List<Bodega_Producto> buscarPorNombreYCategoria(@Param("nombre")String nombre, @Param("cid") Long cid);
}
