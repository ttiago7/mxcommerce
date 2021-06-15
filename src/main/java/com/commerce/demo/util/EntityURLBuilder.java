package com.commerce.demo.util;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class EntityURLBuilder {
    // hoy en dia esto se soluciona con @Builder en la clase
    public static String buildURL (final String entity, final String id){//entity es por ej /noticias y el id es el q fue asignado al objeto luego d hacer el post
        // devuelve la url del servidor donde esta parado
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/{entity}/{id}")
                .buildAndExpand(entity , id) //inyectar las 2 variables en orden
                .toUriString();
    }
}
