package com.fcorp.pfind.Servicios;

import com.fcorp.pfind.Repositorio.Bodega_Productorepositorio;
import com.fcorp.pfind.Repositorio.Clienterepositorio;
import com.fcorp.pfind.Repositorio.Listadorepositorio;
import com.fcorp.pfind.entity.Bodega_Producto;
import com.fcorp.pfind.entity.Cliente;
import com.fcorp.pfind.entity.Listado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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

    public static byte[] compressBytes(MultipartFile file) throws IOException {
        byte[] data = file.getBytes();
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
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

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public Cliente getCliente(Long id) throws Exception {
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
