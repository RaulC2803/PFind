package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Categoriarepositorio  extends JpaRepository<Categoria, Long> {
}
