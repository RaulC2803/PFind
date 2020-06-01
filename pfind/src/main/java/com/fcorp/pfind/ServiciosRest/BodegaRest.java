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
	 
	 @GetMapping("/producto/buscarBPCtg/{cid}")
	 public List<Bodega_Producto> buscarBPporCategoria(@PathVariable(value = "cid") Long cid){
		 return servicioBodega.obtenerBPporCategoria(cid);
	 }
	 @GetMapping("/producto/buscarBPCtg")
	 public List<Bodega_Producto> buscarBPCtg_ifnull(){
		 return ListarBPs();
	 } 


	 @GetMapping("/producto/buscarBPn/{nombre}")
	 public List<Bodega_Producto> buscarBPporNombre(@PathVariable(value = "nombre") String nombre){
		 return servicioBodega.obtenerBPporNombre(nombre);
	 }
	 @GetMapping("/producto/buscarBPn")
	 public List<Bodega_Producto> ListarBPs(){
		 return servicioBodega.obtenerBPporNombre("");
	 }
	 
	 @GetMapping("/producto/buscarBPCtgNombre/{cid}/{nombre}")
	 public List<Bodega_Producto> buscarBPporNombreYCategoria(@PathVariable(value = "cid") Long cid, @PathVariable(value = "nombre") String nombre){
		 return servicioBodega.obtenerBPporNombreYCategoria(nombre, cid);
	 }
	 
	 private List<Bodega_Producto> inters (List<Bodega_Producto> a, List<Bodega_Producto> b)
     {
         List<Bodega_Producto> f = new ArrayList<Bodega_Producto>();
         for(int i = 0; i < a.size(); i++){
             for(int j = 0; j < b.size(); j++) {
                 if(a.get(i).getCodigo() == b.get(j).getCodigo()){
                        f.add(a.get(i));
                    }
             }
         }
         
         return f;
     }
     
     
     @GetMapping("/busqueda/cat_id={cid}/nom={nombre}/marc={marca}/min={pmin}/max={pmax}")
     public List<Bodega_Producto> test(@PathVariable(value = "cid") Long cid, 
                         @PathVariable(value = "nombre") String nombre,
                         @PathVariable(value = "marca") String marca,
                         @PathVariable(value = "pmin") Double pmin,
                         @PathVariable(value = "pmax") Double pmax){
    	 
         List<Bodega_Producto> lCat;
         List<Bodega_Producto> lMxmn;
         List<Bodega_Producto> lBase;
         if (nombre.length() == 0) nombre = "";
         if (marca.length() == 0) marca = "";
        
         lBase = servicioBodega.obtenerBPporNombreYMarca(nombre, marca);
         if (cid != null) {
             lCat = servicioBodega.obtenerBPporCategoria(cid);
             lBase = inters(lCat,lBase);
         } 
         if (pmin != null || pmax != null) {
             if(pmin == null) {
                 pmin = -1.0;
             }
             if(pmax == null) {
                 pmax = 9999999999.99;
             }
             lMxmn = servicioBodega.obtenerBPporMaxMin(pmin,pmax);
             lBase = inters(lMxmn,lBase);
         }
        
         return lBase;
         
     }
	 
	 @GetMapping("/producto/Rango/p_min={pmin}/p_max={pmax}")
	 public List<Bodega_Producto> buscarPorRangoPrecio(@PathVariable(value = "pmin") Double pmin, @PathVariable(value="pmax") Double pmax){
		 return servicioBodega.obtenerBPporMaxMin(pmin, pmax);
	 }
	 
	 @GetMapping("/producto/buscarBPCtgNombre/{cid}")
	 public List<Bodega_Producto> buscarBPporNombreYCategoria_ifNnull(@PathVariable(value = "cid") Long cid){
		 return buscarBPporCategoria(cid);
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
