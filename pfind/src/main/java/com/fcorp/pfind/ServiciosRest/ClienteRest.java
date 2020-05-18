package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.Servicios.ServicioCliente;
import com.fcorp.pfind.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteRest {
    @Autowired
    private ServicioCliente servicioCliente;

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente cliente) throws Exception {
        return servicioCliente.registrarCliente(cliente);
    }

    @PostMapping("/actualizar/{id}")
    public Cliente actualizarCliente(@RequestBody Cliente new_cliente, @PathVariable(value = "id") Long id) throws Exception{
        Cliente c = servicioCliente.buscarCliente(id);
        c.setNombre(new_cliente.getNombre());
        c.setModalidad(new_cliente.getModalidad());
        c.setApellido(new_cliente.getApellido());
        c.setDistrito(new_cliente.getDistrito());
        c.setEdad(new_cliente.getEdad());
        c.setPassword(new_cliente.getPassword());
        c.setEmail(new_cliente.getEmail());
        return servicioCliente.registrarCliente(c);
    }
}
