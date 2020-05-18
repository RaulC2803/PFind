package com.fcorp.pfind.ServiciosRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fcorp.pfind.Servicios.ServicioProducto;
import com.fcorp.pfind.entity.Producto;

@RestController
@RequestMapping("/producto")
public class ProductoRest {
    @Autowired
    private ServicioProducto servicioProducto;
    
    @GetMapping("/buscar/{pid}")
    public Producto buscarProducto(@PathVariable(value = "pid") Long pid) {
        try {
            return servicioProducto.obtenerProducto(pid);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado");
        }
    }
    
    @PostMapping("/registrar")
    public Producto registrarProducto(@RequestBody Producto p) {
        try {
            return servicioProducto.registrarProducto(p);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
    
    @GetMapping("/listar/{nombre}")
    public List<Producto> buscarPorNombre(@PathVariable(value = "nombre") String nombre){
    	return servicioProducto.buscarProductosPorNombre(nombre);
    }
    @GetMapping("/listar")
    public List<Producto> ListarProductos(){
    	return servicioProducto.obtenerProductos();
    }
    
}
