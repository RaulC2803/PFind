package com.fcorp.pfind.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcorp.pfind.Repositorio.Productorepositorio;
import com.fcorp.pfind.entity.Producto;

@Service
public class ServicioProducto {
    @Autowired
    private Productorepositorio productoRepositorio;
    
    public Producto obtenerProducto(Long id) throws Exception {
        return productoRepositorio.findById(id).orElseThrow(()->new Exception("No se encontró producto"));
    }
    @Transactional(rollbackFor = Exception.class)
    public Producto registrarProducto(Producto p) {
        return productoRepositorio.save(p);
    }
    public List<Producto> buscarProductosPorNombre(String nombre){
    	return productoRepositorio.buscarPorNombre(nombre);
    }
    public List<Producto> obtenerProductos(){
    	return productoRepositorio.findAll();
    }
}
