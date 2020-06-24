package com.fcorp.pfind.Servicios;

import com.fcorp.pfind.Repositorio.Bodega_Productorepositorio;
import com.fcorp.pfind.Repositorio.Clienterepositorio;
import com.fcorp.pfind.Repositorio.Listadorepositorio;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Cliente;
import com.fcorp.pfind.entity.Listado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private Listadorepositorio listadorepositorio;

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

    public void cargarImagen(MultipartFile file) throws Exception {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        Cliente c = null;
        int n = clienterepositorio.findAll().size();
        c = clienterepositorio.findAll().get(n-1);
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
            //System.out.println(c.getNombre());
            return c;
        }
    }

    public Listado registrarLista(Cliente cliente, Bodega_Producto bp) throws Exception{
        Cliente c = null;
        c = cliente;
        Bodega_Producto b_producto = null;
        b_producto = bp;
        Listado l = new Listado();
        if (c == null || b_producto == null)  {
            throw new Exception("No se pudo registrar");
        }else{
            l.setCantidad(1);
            l.setPrecio_total(bp.getPrecio());
            l.setCliente(c);
            l.setBodega_producto(bp);
            c.setPrecio_lista(c.getPrecio_lista() + bp.getPrecio());
            c.setCantidad(c.getCantidad() + 1);
            clienterepositorio.save(c);
        }
        return listadorepositorio.save(l);
    }

    public List<Listado>listadoCliente(Cliente cliente) throws Exception {
        Cliente c = null;
        c = cliente;
        if (c == null){
            throw new Exception("No se pudo encontrar sus productos");
        }else{
            return c.getListado();
        }
    }

    public Bodega_Producto obtenerProductodeBodega(Long id){
        return bodega_productorepositorio.findById(id).get();
    }

    public void quitarLista(Long lt){
        listadorepositorio.deleteById(lt);
    }

    public byte[] downloadImage(Long idCliente){
        Cliente cliente = clienterepositorio.findById(idCliente).get();
        return cliente.getImagen();
    }
}
