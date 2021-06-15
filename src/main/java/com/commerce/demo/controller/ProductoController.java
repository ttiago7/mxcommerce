package com.commerce.demo.controller;

import com.commerce.demo.model.Producto;
import com.commerce.demo.service.ProductoService;
import com.commerce.demo.util.PostResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // le decimos q es un controller
@RequestMapping("/productos") //
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @CrossOrigin
    @PostMapping
    @Operation(summary = "Post a product")// notation de Swagger para la documentacion
    @ApiResponses(value = {// Documentacion
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> addProducto(@RequestBody Producto producto) {//con el ? es para no especificar el tipo de objeto q vamos a devolver
        //con final no lo podes redefinir
        final PostResponse postResponse = productoService.addProducto(producto);
        return new ResponseEntity(postResponse.getUrl(), postResponse.getStatus());
    }


    @GetMapping("/all")
    @Operation(summary = "Complete list of products")
    public List<Producto> getAll() {//get all armara los hijos segun el tipo de cada uno d los objetos
        return productoService.getAllProductos();
    }


    @GetMapping("/{codigo}")
    @Operation(summary = "Get product by codigo")
    public Producto getProducto(@PathVariable Integer codigo) {
        Producto response = productoService.getProducto(codigo);
        if (response != null) {
            return response;
        } else {
            return null;
        }
    }

    @CrossOrigin
    @DeleteMapping("/{codigo}")
    @Operation(summary = "Delete product by codigo")
    public String  deleteProducto(@PathVariable Integer codigo) {
        productoService.deleteProductoByid(codigo);
        return ("Producto con codigo: " + codigo + " borrado exitosamente");
    }

    @GetMapping("/search/{titulo}")
    @Operation(summary = "Search product by title")
    public List<Producto> getProductoLike (@PathVariable String titulo){
        return productoService.findProductoLike(titulo);
    }

    @CrossOrigin
    @PutMapping()
    @Operation(summary = "Update product")
    public String updateProduct(@RequestBody Producto producto) {
        productoService.updateProduct(producto);
        return ("Pedido number: " +producto.getCodigo()+" change ");
    }

}
