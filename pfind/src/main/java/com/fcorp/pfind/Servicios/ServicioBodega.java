package com.fcorp.pfind.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcorp.pfind.Repositorio.Bodega_Productorepositorio;
import com.fcorp.pfind.Repositorio.Bodegarepositorio;
import com.fcorp.pfind.Repositorio.Resenarepositorio;
import com.fcorp.pfind.entity.Bodega;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Producto;
import com.fcorp.pfind.entity.Resena;

@Service
public class ServicioBodega {
	@Autowired
	private Bodegarepositorio bodegaRepositorio;
	@Autowired
	private Resenarepositorio resenaRepositorio;
	@Autowired
	private Bodega_Productorepositorio bodega_productoRepositorio;

	@Autowired
	private Bodegarepositorio bodegarepositorio;
	
	public Bodega obtenerBodega(Long codigo)throws Exception {
		Bodega b;
		b = bodegaRepositorio.findById(codigo).get();
		if(b == null) throw new Exception("La bodega no existe");
		return b;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bodega registrarBodega(Bodega bodega) {
		return bodegaRepositorio.save(bodega);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Resena registrarResena(Resena Rinput) throws Exception {
		Resena r = null;
		r = Rinput;
		if (r == null){ throw new Exception("No se pudo registrar");}
		else{
			return resenaRepositorio.save(Rinput);
		}
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public Bodega actualizarBodega(Bodega bodega) throws Exception {
		bodegaRepositorio.findById(bodega.getCodigo()).orElseThrow(() -> new Exception("No se encontró esa bodega"));
		return bodegaRepositorio.save(bodega);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bodega_Producto registrarBodega_Producto(Bodega_Producto BPinput) throws Exception {
		Bodega_Producto b = null;
		b = BPinput;
		if (b == null){ throw new Exception("No se pudo registrar");}
		else{
			return bodega_productoRepositorio.save(BPinput);
		}
	}

	public Bodega_Producto obtenerBodegaProducto(Long id) throws Exception {
		Bodega_Producto bp = bodega_productoRepositorio.findById(id).get();
		if(bp == null){
			throw new Exception("No se pudo encontrar lo que busca");
		}else{
			return bp;
		}
	}

	public List<Bodega_Producto> obtenerBPporCategoria(Long cid) {
		return bodega_productoRepositorio.buscarPorCategoria(cid);
	}
	public List<Bodega_Producto> obtenerBPporNombre(String nombre) {
		return bodega_productoRepositorio.busquedaFinal(nombre);
	}
	
	public List<Bodega_Producto> obtenerBPporMaxMin(Double pmin, Double pmax){
		return bodega_productoRepositorio.buscarPorRango(pmin, pmax);
	}
	@Transactional(rollbackFor = Exception.class)
    public Bodega_Producto actualizarBodega_Producto(Bodega_Producto BPinput) throws Exception {
        Bodega_Producto bp = bodega_productoRepositorio.findById(BPinput.getCodigo()).orElseThrow(()-> new Exception("No se encontró bodega_producto"));
        bp.setCodigo(BPinput.getCodigo());
        bp.setBodega(BPinput.getBodega());
        bp.setProducto(BPinput.getProducto());
        return bodega_productoRepositorio.save(bp);
    }

    public List<Bodega> obtenerBodegas(){
		return bodegaRepositorio.findAll();
	}
}
