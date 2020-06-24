package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.Servicios.ServicioCliente;
import com.fcorp.pfind.entity.Bodega;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Cliente;
import com.fcorp.pfind.entity.Listado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteRest {
    @Autowired
    private ServicioCliente servicioCliente;

    @PostMapping("/registrar")
    public Cliente registrarCliente(@RequestBody Cliente cliente) throws Exception {
        return servicioCliente.registrarCliente(cliente);
    }

    @PostMapping("/upload_imagen")
    public void CargarImagen(@RequestParam("imageFile") MultipartFile file) throws Exception {
        servicioCliente.cargarImagen(file);
    }

    @GetMapping("/get_imagen/{id}")
    public Cliente getImagen(@PathVariable(value = "id") Long id) throws Exception {
        return servicioCliente.getClienteImagen(id);
    }

    @PostMapping("/actualizar/{id}")
    public Cliente actualizarCliente(@RequestBody Cliente new_cliente, @PathVariable(value = "id") Long id, @RequestParam("imageFile")MultipartFile imageFile) throws Exception{
        Cliente c = servicioCliente.buscarCliente(id);
        if (new_cliente.getNombre() != null) c.setNombre(new_cliente.getNombre());
        if (new_cliente.getApellido() != null) c.setApellido(new_cliente.getApellido());
        if (new_cliente.getDistrito() != null) c.setDistrito(new_cliente.getDistrito());
        if (new_cliente.getEdad() != null) c.setEdad(new_cliente.getEdad());
        if (new_cliente.getPassword() != null) c.setPassword(new_cliente.getPassword());
        if (new_cliente.getEmail() != null) c.setEmail(new_cliente.getEmail());
        return servicioCliente.registrarCliente(c);
    }

    @PostMapping("/agregar/{id}/{bp}")
    public Cliente enlistarProducto(@PathVariable(value = "id") Long id, @PathVariable(value = "bp") Long bp) throws Exception {
        Cliente c = servicioCliente.buscarCliente(id); 
        Bodega_Producto prod = servicioCliente.obtenerProductodeBodega(bp);
        servicioCliente.registrarLista(c,prod);
        return c;
    }

    @DeleteMapping("/{id}/quitar/{lt}")
    public List<Listado> quitarLista(@PathVariable(value = "id") Long id, @PathVariable(value = "lt") Long lt) throws Exception {
        Cliente c = null;
        servicioCliente.quitarLista(lt);
        c = servicioCliente.buscarCliente(id);
        return servicioCliente.listadoCliente(c);
    }

    @GetMapping("/{id}/lista")
    public List<Listado> listaCliente(@PathVariable(value = "id") Long id) throws Exception {
        Cliente c = servicioCliente.buscarCliente(id);
        return servicioCliente.listadoCliente(c);
    }

    @GetMapping("/{bp}")
    public Bodega_Producto obtenerBodegaProducto(@PathVariable(value = "bp") Long bp){
        return servicioCliente.obtenerProductodeBodega(bp);
    }
    
    @GetMapping("/costo/{id}")
    public Double obtenerPrecio(@PathVariable(value = "id")Long id){
        return servicioCliente.buscarCliente(id).getPrecio_lista();
    }
}
