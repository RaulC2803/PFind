package com.fcorp.pfind.Servicios;

import com.fcorp.pfind.Repositorio.Clienterepositorio;
import com.fcorp.pfind.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ServicioCliente {
    @Autowired
    private Clienterepositorio clienterepositorio;

    public Cliente buscarCliente(Long id){
        return clienterepositorio.findById(id).get();
    }

    public Cliente registrarCliente(Cliente cliente) throws Exception {
        Cliente c = null;
        c = cliente;
        if (c == null){
            throw new Exception("No se pudo registrar");
        }
        else {
            return clienterepositorio.save(c);
        }
    }
}
