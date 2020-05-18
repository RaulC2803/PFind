package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.entity.Categoria;
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

import java.util.List;


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
	
	
	 @PostMapping("/producto/registrar/{bID}/{pID}")
	    public Bodega_Producto registrarBodega_Producto(@RequestBody Bodega_Producto BPinput,
	                                                     @PathVariable(value = "bID") Long bid,
	                                                     @PathVariable(value = "pID") Long pid) {
	        try {
	            Bodega b = servicioBodega.obtenerBodega(bid);
	            Producto p = servicioProducto.obtenerProducto(pid);
	            Bodega_Producto bp = BPinput;
	            bp.setBodega(b);
	            bp.setProducto(p);
	            bp.setBodega(BPinput.getBodega());
	          	bp.setProducto(BPinput.getProducto());
	          	return servicioBodega.registrarBodega_Producto(bp);
	        } catch(Exception e) {
	        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
	        }
	    }
	
	 @GetMapping("/producto/buscar/{bID}/{pID}")
	    public Bodega_Producto buscarBodega_Producto(@PathVariable(value = "bID") Long bid, @PathVariable(value = "pID") Long pid) {
	        try {
	            Bodega b = servicioBodega.obtenerBodega(bid);
	            Producto p = servicioProducto.obtenerProducto(pid);
	            Bodega_Producto bp = servicioBodega.obtenerBodega_Producto(b, p);
	            return bp;
	        } catch(Exception e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro Bodega_Producto con los datos ingresados");
	        }
	    }
	 
	 
	  @PutMapping("/producto/actualizar/{bID}/{pID}")
	    public Bodega_Producto actualizarBodega_Producto(@RequestBody Bodega_Producto BPinput,
	                                                     @PathVariable(value = "bID") Long bid,
	                                                     @PathVariable(value = "pID") Long pid) {
	        try {
	            Bodega b = servicioBodega.obtenerBodega(bid);
	            Producto p = servicioProducto.obtenerProducto(pid);
	            Bodega_Producto bp = servicioBodega.obtenerBodega_Producto(b, p);
	            bp.setCodigo(BPinput.getCodigo());
	            bp.setBodega(BPinput.getBodega());
	            bp.setListado(BPinput.getListado());
	            bp.setProducto(BPinput.getProducto());
	            return servicioBodega.actualizarBodega_Producto(bp);
	        } catch(Exception e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
	        }
	    }
}
