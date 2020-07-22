package com.fcorp.pfind.Servicios;

import com.fcorp.pfind.entity.Usuario;

public interface IUsuarioService {

	public Usuario findByUsername(String username);
}
