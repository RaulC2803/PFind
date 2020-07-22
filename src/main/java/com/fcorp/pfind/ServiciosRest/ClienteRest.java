package com.fcorp.pfind.ServiciosRest;

import com.fcorp.pfind.Repositorio.Lista_Productorepositorio;
import com.fcorp.pfind.Repositorio.RolesRepositorio;
import com.fcorp.pfind.Servicios.ServicioCliente;
import com.fcorp.pfind.Servicios.UsuarioService;
import com.fcorp.pfind.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/cliente")
public class ClienteRest {
    @Autowired
    private ServicioCliente servicioCliente;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private UsuarioService servicioUsuario;
	@Autowired
	private RolesRepositorio rolesRepositorio;
	@Autowired
    private Lista_Productorepositorio lista_productorepositorio;
	
	@Transactional
	@PostMapping("/registrar")
	public Cliente registrarBodega(@RequestBody Cliente cliente) {
		Cliente c = null;
		Usuario u = new Usuario();
		Usuario aux = null;
		try {
			c = servicioCliente.registrarCliente(cliente);
			u.setUsername(cliente.getEmail());
			u.setEnabled(true);
			u.setEmail(cliente.getEmail());
			u.setPassword(encoder.encode(cliente.getPassword()));
			u.setModalidad("cliente");
			u.setIdEntity(cliente.getCodigo());
			List<Role> roles = new ArrayList<Role>();
			Long id = (long) 2;
			roles.add(rolesRepositorio.findById(id).get());
			u.setRoles(roles);
			aux = servicioUsuario.registrarUsuario(u);
			
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "no se puede registrar bodega");
		}
		return c;
	}

    @PostMapping("/upload_imagen/{id}")
    public void CargarImagen(@PathVariable(value = "id")Long id, @RequestParam("imageFile") MultipartFile file) throws Exception {
        servicioCliente.cargarImagen(id,file);
    }

    @GetMapping("/get_imagen/{id}")
    public Cliente getImagen(@PathVariable(value = "id") Long id) throws Exception {
        return servicioCliente.getClienteImagen(id);
    }

    @GetMapping("/buscarCliente/{id}")
    public Cliente buscarCliente(@PathVariable(value="id") Long id) {
    	return servicioCliente.buscarCliente(id);
    }

    @PostMapping("/actualizar/{id}")
    public Cliente actualizarCliente(@RequestBody Cliente new_cliente, @PathVariable(value = "id") Long id) throws Exception{
        Cliente c = servicioCliente.buscarCliente(id);
        if (new_cliente.getNombre() != null) c.setNombre(new_cliente.getNombre());
        if (new_cliente.getApellido() != null) c.setApellido(new_cliente.getApellido());
        if (new_cliente.getDistrito() != null) c.setDistrito(new_cliente.getDistrito());
        if (new_cliente.getEdad() != null) c.setEdad(new_cliente.getEdad());
        if (new_cliente.getPassword() != null) c.setPassword(new_cliente.getPassword());
        if (new_cliente.getEmail() != null) c.setEmail(new_cliente.getEmail());
        return servicioCliente.registrarCliente(c);
    }
    
    @GetMapping("/costo/{id}")
    public Double obtenerPrecio(@PathVariable(value = "id")Long id){
        return servicioCliente.buscarCliente(id).getPrecio_lista();
    }

    @Secured({"ROLE_CLIENTE"})
    @GetMapping("/listar/{id}")
    public List<Lista_Producto> listar_Cliente(@PathVariable(value = "id") Long id){
	    return servicioCliente.obtenerLP(id);
    }
    
    @GetMapping("/buscarLP/cid={cid}/bpid={bpid}")
    public Lista_Producto buscarPorIDs(@PathVariable(value = "cid") Long cid, @PathVariable(value = "bpid") Long bpid) {
    	List<Lista_Producto> aux = servicioCliente.obtenerLP(cid);
    	for(int i = 0; i<aux.size(); i++) {
    		if (aux.get(i).getBodega_producto().getCodigo() == bpid) {
    			return aux.get(i);
    		}
    	}
    	return null;
    }
    
    @PostMapping("/registrarLP/cliente_id={cid}/bp_id={bpid}")
    public Lista_Producto registrarLista_Producto(@PathVariable(value = "cid") Long cid, @PathVariable(value = "bpid") Long bpid) {
        try {
        	List<Lista_Producto> aux = servicioCliente.obtenerLP(cid);
        	boolean exists_in_list = false;
        	Lista_Producto lp = new Lista_Producto();
        	
        	for(int i = 0; i < aux.size(); i++) {
        		if (aux.get(i).getBodega_producto().getCodigo() == bpid) {
        			exists_in_list = true;
        			lp.setCodigo(aux.get(i).getCodigo());
        			lp.setBodega_producto(aux.get(i).getBodega_producto());
        			lp.setCliente(aux.get(i).getCliente());
        			break;
        		}
        	}
        	
        	if (exists_in_list) return lp;
            return servicioCliente.registrarLista_Producto(lp, cid, bpid);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @DeleteMapping("/quitar/{lt}")
    public void borrarLista_Producto(@PathVariable(value = "lt") Long lt) throws Exception {
	    servicioCliente.borrarListado_Producto(lt);
    }
}
