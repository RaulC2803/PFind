package com.fcorp.pfind.ServiciosRest;

import java.util.ArrayList;
import java.util.List;

import com.fcorp.pfind.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.fcorp.pfind.Repositorio.RolesRepositorio;
import com.fcorp.pfind.Servicios.ServicioBodega;
import com.fcorp.pfind.Servicios.ServicioCliente;
import com.fcorp.pfind.Servicios.ServicioProducto;
import com.fcorp.pfind.Servicios.UsuarioService;


@RestController
@RequestMapping("/bodega")
public class BodegaRest {
	@Autowired
	private ServicioBodega servicioBodega;
	@Autowired
	private ServicioCliente servicioCliente;
	@Autowired
	private ServicioProducto servicioProducto;
	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private RolesRepositorio rolesRepositorio;
	

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

	@Transactional
	@PostMapping("/registrar")
	public Bodega registrarBodega(@RequestBody Bodega bodega) {
		Bodega b = null;
		Usuario u = new Usuario();
		Usuario aux = null;
		try {
			b = servicioBodega.registrarBodega(bodega);
			u.setUsername(bodega.getRuc());
			u.setEnabled(true);
			u.setEmail(bodega.getEmail());
			u.setPassword(encoder.encode(bodega.getPassword()));
			u.setModalidad("bodega");
			u.setIdEntity(bodega.getCodigo());
			List<Role> roles = new ArrayList<Role>();
			Long id = (long) 1;
			roles.add(rolesRepositorio.findById(id).get());
			u.setRoles(roles);
			aux = servicioUsuario.registrarUsuario(u);
			//falta roles
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se puede registrar bodega");
		}
		return b;
	}

	@GetMapping("/resena/obtener")
	public List<Resena> obtenerResena(){
		return servicioBodega.obtenerResenas();
	}


	@PostMapping("/upload_imagen")
	public void CargarImagen(@RequestParam("imageFile") MultipartFile file) throws Exception {
		servicioBodega.cargarImagen(file);
	}

	@PostMapping("/upload_imagen_producto/{id}/{bpid}")
	public Bodega_Producto CargarImagenProducto(@RequestParam("imageFile") MultipartFile file, @PathVariable(value = "id")Long id, @PathVariable(value = "bpid")Long bpid)throws Exception{
		return servicioBodega.cargarImagenProducto(file,id,bpid);
	}

	@GetMapping("/get_imagen/{id}")
	public Bodega getImagen(@PathVariable(value = "id") Long id) throws Exception {
		return servicioBodega.getBodegaImagen(id);
	}

	@GetMapping("/get_imagenProducto/{id}/{bpid}")
	public byte[] getImagenProducto(@PathVariable(value = "id")Long id, @PathVariable(value = "bpid")Long bpid) throws Exception{
		return servicioBodega.getBodegaImagenProducto(id,bpid);
	}

	@PutMapping("/actualizar")
	public Bodega actualizarBodega(@RequestBody Bodega bodega) {
		Bodega b = bodega;
		try {
			Bodega aux = servicioBodega.obtenerBodega(bodega.getCodigo());
			b.setProductos(aux.getProductos());
			b.setResena(aux.getResena());
			b = servicioBodega.actualizarBodega(b);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede encontrar");
		}
		return b;
	}	

	 @PostMapping("/producto/registrar/{id}")
	    public Bodega_Producto registrarBodega_Producto(@RequestBody Bodega_Producto bodega_producto,
														@PathVariable(value = "id")Long id) {
	        try {
	            Bodega_Producto  bp = bodega_producto;
	          	return servicioBodega.registrarBodega_Producto(bp,id);
	        } catch(Exception e) {
	        	throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
	        }
	    }

	 @GetMapping("/producto/buscarBodega/{id}")
	 public List<Bodega_Producto> buscarPorBodega(@PathVariable(value = "id") Long id){
		 return servicioBodega.obtenerBPporBodega(id);
	 }

	 @GetMapping("/producto/buscarBPCtg/{cid}")
	 public List<Bodega_Producto> buscarBPporCategoria(@PathVariable(value = "cid") Long cid){
		 return servicioBodega.obtenerBPporCategoria(cid);
	 }

	 @GetMapping("/Bodega_Producto_Id/{id}")
	 public Bodega_Producto buscarBPporId(@PathVariable(value = "id") Long id) throws Exception {
		 return servicioBodega.obtenerBodegaProducto(id);
	 }
	 @GetMapping("/Bodega_Producto_Nombre/nom={nombre}")
	 public List<Bodega_Producto> ListarBPs(@PathVariable(value = "nombre")String nombre){
		 return servicioBodega.obtenerBPporNombre(nombre);
	 }

	@GetMapping("/producto/Rango/p_min={pmin}/p_max={pmax}")
	public List<Bodega_Producto> buscarPorRangoPrecio(@PathVariable(value = "pmin") Double pmin, @PathVariable(value="pmax") Double pmax){
		if (pmin == null) pmin = -1.0;
		if (pmax == null) pmax = 99999999.0;
		return servicioBodega.obtenerBPporMaxMin(pmin, pmax);
	}
	 
	 @GetMapping("/producto/buscarBPCtgNombre/{cid}")
	 public List<Bodega_Producto> buscarBPporNombreYCategoria_ifNnull(@PathVariable(value = "cid") Long cid){
		 return buscarBPporCategoria(cid);
	 }
	 
	 private List<Bodega_Producto> inters (List<Bodega_Producto> a, List<Bodega_Producto> b) {
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
	 
     @GetMapping("/producto/busqueda/cat_id={cid}/nom={nombre}/marc={marca}/bod={bodega}/min={pmin}/max={pmax}/searchbar={sbar}")
     public List<Bodega_Producto> test(@PathVariable(value = "cid") Long cid, 
                         @PathVariable(value = "nombre") String nombre,
                         @PathVariable(value = "marca") String marca,
                         @PathVariable(value = "bodega") String bodega,
                         @PathVariable(value = "pmin") Double pmin,
                         @PathVariable(value = "pmax") Double pmax,
                         @PathVariable(value = "sbar") Long sbar){
    	 
         List<Bodega_Producto> lCat;
         List<Bodega_Producto> lMxmn;
         List<Bodega_Producto> lBase;// = new ArrayList<Bodega_Producto>();
         if (sbar == null || sbar != (long)1) sbar = (long)0;
         if (nombre.length() == 0) nombre = "";
         if (marca.length() == 0) marca = "";
         if (bodega.length() == 0) bodega = "";
         lBase = servicioBodega.obtenerBPporNombreMarcaYBodega(nombre, marca, bodega);
         
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

	@DeleteMapping("/quitar/{lt}")
	public void quitarLista(@PathVariable(value = "lt") Long lt) throws Exception {
		servicioBodega.borrarBodegaProducto(lt);
	}

	 @GetMapping("/producto/buscarBPn/{nombre}")
	 public List<Bodega_Producto> buscarBPporNombre(@PathVariable(value = "nombre") String nombre){
		 return servicioBodega.obtenerBPporNombre(nombre);
	 }
	 @GetMapping("/producto/buscarBPn")
	 public List<Bodega_Producto> ListarBPs(){
		 return servicioBodega.obtenerBPporNombre("");
	 }
}
