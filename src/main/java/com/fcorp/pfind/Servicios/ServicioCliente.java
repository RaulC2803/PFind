package com.fcorp.pfind.Servicios;

import com.fcorp.pfind.Repositorio.Bodega_Productorepositorio;
import com.fcorp.pfind.Repositorio.Clienterepositorio;
import com.fcorp.pfind.Repositorio.Lista_Productorepositorio;
import com.fcorp.pfind.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.fcorp.pfind.Servicios.Decodificador.compressBytes;
import static com.fcorp.pfind.Servicios.Decodificador.decompressBytes;

@Service
public class ServicioCliente {
    @Autowired
    private Clienterepositorio clienterepositorio;

    @Autowired
    private Bodega_Productorepositorio bodega_productorepositorio;

    @Autowired
    private Lista_Productorepositorio lista_productorepositorio;

    public Cliente buscarCliente(Long id){
        return clienterepositorio.findById(id).get();
    }

    public Cliente registrarCliente(Cliente cliente) throws Exception {
        Cliente c = null;
        c = cliente;
        if (c == null){
            throw new Exception("No se pudo registrar");
        }
        else {
            System.out.println("cliente registrado");
            return clienterepositorio.save(c);
        }
    }

    public void cargarImagen(Long id, MultipartFile file) throws Exception {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Cliente c = null;
        c = clienterepositorio.findById(id).get();
        if(c == null){
            System.out.println("No se encontró el cliente");
            throw new Exception("No se encontró el cliente que busca");
        }
        else {
            byte[] new_image = compressBytes(file);
            c.setImagen(new_image);
            System.out.println("Imagen del Cliente Guardada");
            clienterepositorio.save(c);
        }
    }

    public Cliente getClienteImagen(Long id) throws Exception {
        Cliente c = null;
        c = buscarCliente(id);
        c.setImagen(decompressBytes(c.getImagen()));
        if(c == null){
            throw new Exception("No se pudo encontrar el cliente");
        }else {
            return c;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Lista_Producto registrarLista_Producto(Lista_Producto LPinput, Long cid, Long bpid) throws Exception {
        Lista_Producto l = null;
        l = LPinput;
        Cliente cliente = null;
        cliente = buscarCliente(cid);
        Bodega_Producto bp = bodega_productorepositorio.findById(bpid).get();
        l.setCliente(cliente);
        l.setBodega_producto(bp);
        if (l == null){ throw new Exception("No se pudo registrar");}
        else{
            return lista_productorepositorio.save(l);
        }
    }

    public Lista_Producto obtenerListaProducto(Long id) throws Exception {
        Lista_Producto lp = lista_productorepositorio.findById(id).get();
        if(lp == null){
            throw new Exception("No se pudo encontrar lo que busca");
        }else{
            return lp;
        }
    }

    public List<Lista_Producto> obtenerLP(Long id){
        return lista_productorepositorio.busquedaPorCliente(id);
    }

    public void borrarListado_Producto(Long id){
        lista_productorepositorio.deleteById(id);
    }

    public Bodega_Producto obtenerProductodeBodega(Long id){
        return bodega_productorepositorio.findById(id).get();
    }

}
