package com.fcorp.pfind.ServiciosRest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fcorp.pfind.Servicios.ServicioBodega;
import com.fcorp.pfind.Servicios.ServicioProducto;
import com.fcorp.pfind.entity.Bodega;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Producto;


@RestController
@RequestMapping("/bodega")
public class BodegaRest {
	@Autowired
	private ServicioBodega servicioBodega;

	@Autowired
	private ServicioProducto servicioProducto;

	@GetMapping("/{codigo}")
	public Bodega obtenerBodega(@PathVariable(value ="codigo") Long codigo) {
		Bodega b = null;
		try {
			b = servicioBodega.obtenerBodega(codigo);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede encontrar");
		}
		return b;
	}
	
	@PostMapping("/registrar")
	public Bodega registrarBodega(@RequestBody Bodega bodega) {
		Bodega b = null;
		try {
			b = servicioBodega.registrarBodega(bodega);

		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se puede registrar producto");
		}
		return bodega;
	}

	@PutMapping("/actualizar")
	public Bodega actualizarBodega(@RequestBody Bodega bodega) {
		Bodega b;
		try {
			b = servicioBodega.actualizarBodega(bodega);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede encontrar");
		}
		return b;
	}	
	
	
	 @PostMapping("/producto/registrar/{bID}/{pID}/{precio}")
	    public Bodega_Producto registrarBodega_Producto(@PathVariable(value = "precio") Double precio,
	                                                     @PathVariable(value = "bID") Long bid,
	                                                     @PathVariable(value = "pID") Long pid) {
	        try {
	            Bodega b = servicioBodega.obtenerBodega(bid);
	            Producto p = servicioProducto.obtenerProducto(pid);
	            Bodega_Producto  bp = new Bodega_Producto();
	            bp.setBodega(b);
	            bp.setProducto(p);
	            bp.setPrecio(precio);
	          	return servicioBodega.registrarBodega_Producto(bp);
	        } catch(Exception e) {
	        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
	        }
	    }
	 
	 @GetMapping("/producto/buscarBPCtg/{cid}")
	 public List<Bodega_Producto> buscarBPporCategoria(@PathVariable(value = "cid") Long cid){
		 return servicioBodega.obtenerBPporCategoria(cid);
	 }

	 @GetMapping("/Bodega_Producto_Id/{id}")
	 public Bodega_Producto buscarBPporId(@PathVariable(value = "id") Long id) throws Exception {
		 return servicioBodega.obtenerBodegaProducto(id);
	 }
	 @GetMapping("/Bodega_Producto_Nombre/{nombre}")
	 public List<Bodega_Producto> ListarBPs(@PathVariable(value = "nombre")String nombre){
		 return servicioBodega.obtenerBPporNombre(nombre);
	 }
	 
	 @GetMapping("/producto/Rango/p_min={pmin}/p_max={pmax}")
	 public List<Bodega_Producto> buscarPorRangoPrecio(@PathVariable(value = "pmin") Double pmin, @PathVariable(value="pmax") Double pmax){
		 return servicioBodega.obtenerBPporMaxMin(pmin, pmax);
	 }
	 
	 @GetMapping("/producto/buscarBPCtgNombre/{cid}")
	 public List<Bodega_Producto> buscarBPporNombreYCategoria_ifNnull(@PathVariable(value = "cid") Long cid){
		 return buscarBPporCategoria(cid);
	 }
}
