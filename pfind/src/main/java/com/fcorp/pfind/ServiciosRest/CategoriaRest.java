package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.Servicios.ServicioCategoria;
import com.fcorp.pfind.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
