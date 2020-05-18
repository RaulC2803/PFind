package com.fcorp.pfind.Servicios;

import com.fcorp.pfind.Repositorio.Categoriarepositorio;
import com.fcorp.pfind.entity.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioCategoria {
    @Autowired
    private Categoriarepositorio categoriarepositorio;

    public List<Categoria> obtenerCategoria(){
        return categoriarepositorio.findAll();
    }
}
