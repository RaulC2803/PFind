package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.Servicios.ServicioCliente;
import com.fcorp.pfind.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {
    @Autowired
    private ServicioCliente servicioCliente;

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente cliente) throws Exception {
        return servicioCliente.registrarCliente(cliente);
    }
}
