package com.fcorp.pfind.Repositorio;

import com.fcorp.pfind.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Clienterepositorio extends JpaRepository<Cliente, Long> {
}
