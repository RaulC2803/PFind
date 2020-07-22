package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Categoria;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Categoriarepositorio  extends JpaRepository<Categoria, Long> {
	@Query("select c from Categoria c where c.nombre like %?1%")
	public List<Categoria> buscarPorNombre(String nombre);
}
