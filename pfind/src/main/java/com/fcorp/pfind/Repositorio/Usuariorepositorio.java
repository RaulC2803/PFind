package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Usuariorepositorio extends JpaRepository<Usuario,Long> {
}
