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
        if (new_cliente.getNombre() != null) c.setNombre(new_cliente.getNombre());
        if (new_cliente.getApellido() != null) c.setApellido(new_cliente.getApellido());
        if (new_cliente.getDistrito() != null) c.setDistrito(new_cliente.getDistrito());
        if (new_cliente.getEdad() != null) c.setEdad(new_cliente.getEdad());
        if (new_cliente.getPassword() != null) c.setPassword(new_cliente.getPassword());
        if (new_cliente.getEmail() != null) c.setEmail(new_cliente.getEmail());
        return servicioCliente.registrarCliente(c);
    }
}
