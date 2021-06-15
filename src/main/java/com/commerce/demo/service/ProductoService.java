package com.commerce.demo.service;

import com.commerce.demo.model.Producto;
import com.commerce.demo.repository.ProductoRepository;
import com.commerce.demo.util.EntityURLBuilder;
import com.commerce.demo.util.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class ProductoService {
    private static final String PRODUCTO_PATH = "producto";//es la url q ponelmemos en postman

    @Autowired
    private ProductoRepository productoRepository;

    public PostResponse addProducto(Producto producto) {
        final Producto productoSaved = productoRepository.save(producto);
        return PostResponse.builder()
                .status(HttpStatus.CREATED)
                .url(EntityURLBuilder.buildURL(PRODUCTO_PATH, productoSaved.getCodigo().toString()))
                .build();
    }

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProducto(Integer codigo) {
        return productoRepository.findById(codigo).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    public void deleteProductoByid(Integer codigo) {
        productoRepository.deleteById(codigo);
    }

    public List<Producto> findProductoLike(String titulo) {
        return productoRepository.findProductoLike(titulo);
    }

    public Producto updateProduct(Producto producto) {
        final Producto prod = productoRepository.findById(producto.getCodigo()).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if (prod != null) {
            producto.setCodigo(prod.getCodigo());
            return productoRepository.save(producto);
        } else {
            //Si no se encuentra
            return null;
        }
    }
}

