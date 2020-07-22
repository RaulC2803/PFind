package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Productorepositorio extends JpaRepository<Producto, Long> {
	@Query("select p from Producto p where p.nombre like %?1%")
	public List<Producto> buscarPorNombre(String nombre);
	
	@Query("select p from Producto p where p.categoria.codigo = ?1")
	public List<Producto> buscarPorCategoria(Long cid);
}

