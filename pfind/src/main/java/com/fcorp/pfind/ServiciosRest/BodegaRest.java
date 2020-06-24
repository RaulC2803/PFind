package com.fcorp.pfind.ServiciosRest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fcorp.pfind.Servicios.ServicioBodega;
import com.fcorp.pfind.Servicios.ServicioCliente;
import com.fcorp.pfind.Servicios.ServicioProducto;
import com.fcorp.pfind.entity.Bodega;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Cliente;
import com.fcorp.pfind.entity.Producto;
import com.fcorp.pfind.entity.Resena;


@RestController
@RequestMapping("/bodega")
public class BodegaRest {
	@Autowired
	private ServicioBodega servicioBodega;
	@Autowired
	private ServicioCliente servicioCliente;
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
	@PostMapping("/resena/registrar/{bid}/{cid}")
	public Resena registrarResena(@RequestBody Resena re, @PathVariable(value = "bid") Long bid ,@PathVariable(value = "cid") Long cid ) {
		try {
			Bodega b = servicioBodega.obtenerBodega(bid);
            Cliente c = servicioCliente.buscarCliente(cid);
            Resena r = new Resena();
            r.setBodega(b);
            r.setCliente(c);
            r.setCalificacion(re.getCalificacion());
            r.setContenido(re.getContenido());
           
			return servicioBodega.registrarResena(r);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se puede registrar reseña");
		}
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

	@PostMapping("/upload_imagen")
	public void CargarImagen(@RequestParam("imageFile") MultipartFile file) throws Exception {
		servicioBodega.cargarImagen(file);
	}

	@GetMapping("/get_imagen/{id}")
	public Bodega getImagen(@PathVariable(value = "id") Long id) throws Exception {
		return servicioBodega.getBodegaImagen(id);
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
