package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Productorepositorio extends JpaRepository<Producto, Long> {
}
