package com.fcorp.pfind.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fcorp.pfind.Repositorio.Bodega_Productorepositorio;
import com.fcorp.pfind.Repositorio.Bodegarepositorio;
import com.fcorp.pfind.entity.Bodega;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Producto;

@Service
public class ServicioBodega {
	@Autowired
	private Bodegarepositorio bodegaRepositorio;
	private Bodega_Productorepositorio bodega_productoRepositorio;
	
	public Bodega obtenerBodega(Long codigo)throws Exception {
		Bodega b;
		b = bodegaRepositorio.buscarBodega(codigo);
		if(b == null) throw new Exception("La bodega no existe");
		return b;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bodega registrarBodega(Bodega bodega) {
		return bodegaRepositorio.save(bodega);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bodega actualizarBodega(Bodega bodega) throws Exception {
		Bodega p = bodegaRepositorio.findById(bodega.getCodigo()).orElseThrow(() -> new Exception("No se encontró esa bodega"));
		return bodegaRepositorio.save(bodega);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Bodega_Producto registrarBodega_Producto(Bodega_Producto BPinput) {
		return bodega_productoRepositorio.save(BPinput);
	}
	

	public Bodega_Producto obtenerBodega_Producto(Bodega bodega, Producto producto) throws Exception{
		Bodega_Producto bp;
		bp = bodega_productoRepositorio.buscarBodega_Producto(bodega.getCodigo(),producto.getCodigo());
		if(bp == null) throw new Exception("No se encontró relación");
		return bp;
	}
	
	@Transactional(rollbackFor = Exception.class)
    public Bodega_Producto actualizarBodega_Producto(Bodega_Producto BPinput) throws Exception {
        Bodega_Producto bp = bodega_productoRepositorio.findById(BPinput.getCodigo()).orElseThrow(()-> new Exception("No se encontró bodega_producto"));
        bp.setCodigo(BPinput.getCodigo());
        bp.setBodega(BPinput.getBodega());
        bp.setListado(BPinput.getListado());
        bp.setProducto(BPinput.getProducto());
        return bodega_productoRepositorio.save(bp);
    }
}