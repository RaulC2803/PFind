package com.fcorp.pfind.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcorp.pfind.entity.Role;

public interface RolesRepositorio extends JpaRepository<Role, Long> {
	
}

