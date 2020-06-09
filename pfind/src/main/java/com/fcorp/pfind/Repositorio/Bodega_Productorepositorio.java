package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Bodega_Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface Bodega_Productorepositorio extends JpaRepository<Bodega_Producto, Long> {
    @Query("SELECT bp FROM Bodega_Producto bp WHERE bp.producto.codigo = :proid and bp.bodega.codigo = :bodeid")
    Bodega_Producto buscarBodegaProducto(@Param("proid") Long Producto_Id,
                                         @Param("bodeid") Long Bodega_Id);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.categoria.codigo = :cid")
    List<Bodega_Producto> buscarPorCategoria(@Param("cid") Long cid);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.nombre like %:nombre%")
    List<Bodega_Producto> buscarPorNombre(@Param("nombre") String nombre);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.nombre like %?1% and bp.producto.marca like %?2% and bp.bodega.nombre like %?3%")
    List<Bodega_Producto> buscarPorNombreMarcaYBodega(String nombre, String marca, String bodega);
    
    @Query("select bp from Bodega_Producto bp where bp.precio>= :pmin and bp.precio<= :pmax")
    List<Bodega_Producto> buscarPorRango(Double pmin, Double pmax);
    
    @Query("select bp from Bodega_Producto bp where bp.producto.nombre like %:nombre% and bp.producto.categoria.codigo = :cid")
    List<Bodega_Producto> buscarPorNombreYCategoria(@Param("nombre")String nombre, @Param("cid") Long cid);
}
