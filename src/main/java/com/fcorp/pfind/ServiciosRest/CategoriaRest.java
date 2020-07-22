package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.Servicios.ServicioCategoria;
import com.fcorp.pfind.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaRest {
    @Autowired
    private ServicioCategoria servicioCategoria;

    @GetMapping("/obtener")
    public List<Categoria> obtenerCategoria(){
        return servicioCategoria.obtenerCategoria();
    }
    
    @GetMapping("/obtener/{cid}")
    public Categoria obtenerPorId(@PathVariable(value = "cid") Long codigo) {
    	try {
    		return servicioCategoria.obtenerPorId(codigo);
    	} catch (Exception e) {
    		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe categoría con ese ID");
    	}
    }
}
