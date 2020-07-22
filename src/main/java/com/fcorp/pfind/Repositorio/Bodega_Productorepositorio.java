package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Bodega_Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.websocket.server.PathParam;

public interface Bodega_Productorepositorio extends JpaRepository<Bodega_Producto, Long> {

    @Query("select bp from Bodega_Producto bp where bp.producto.categoria.codigo = :cid")
    List<Bodega_Producto> buscarPorCategoria(@Param("cid") Long cid);

    @Query("select bp from Bodega_Producto bp where bp.precio>= :pmin and bp.precio<= :pmax")
    List<Bodega_Producto> buscarPorRango(Double pmin, Double pmax);

    @Query("select bp from Bodega_Producto bp Where bp.producto.nombre like %:variable% or bp.producto.marca like %:variable% or bp.producto.categoria.nombre like %:variable% or bp.bodega.nombre like %:variable%")
    List<Bodega_Producto> busquedaFinal(@Param("variable")String variable);

    @Query("select bp from Bodega_Producto bp Where bp.bodega.codigo = :id")
    List<Bodega_Producto> busquedaPorBodega(@Param("id") Long id);

    @Query("select bp from Bodega_Producto bp where bp.producto.nombre like %?1% and bp.producto.marca like %?2% and bp.bodega.nombre like %?3%")
    List<Bodega_Producto> buscarPorNombreMarcaYBodega(String nombre, String marca, String bodega);
}
