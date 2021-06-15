package com.commerce.demo.service;

import com.google.gson.Gson;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiCallService {

    //java 11 nos brinda HttpClient para hacer nuestras peticiones,
    private static final HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
    private static final String URL = "https://dolarapi.p.rapidapi.com/resumen";


    public Object callApi(String ubicacion) throws Throwable {
        System.out.println(ubicacion);
        String ciudad="Monterrico";
        String pais="Argentina";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://google-maps-geocoding.p.rapidapi.com/geocode/json?address="+ciudad+"%2C%20"+pais+"&language=es"))
                .header("x-rapidapi-key", "392d7c7c2amsh14dc46d53a988afp19ac54jsn7866ab04b564")
                .header("x-rapidapi-host", "google-maps-geocoding.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.body());

        //Gson es una lib de google q nos permite parsear la response en un json
        final Object apiDolarResponse = new Gson().fromJson(response.body(), Object.class); // hacemos un json tomando el response.body() y mapeandolo aApiWeatherResponse.class q tiene los campos q necesitamos en nuestra respuesta q mostrara postman
        System.out.println(apiDolarResponse);
        return apiDolarResponse;
    }


//    public ApiDolarResponse callAPI() throws IOException, InterruptedException {
//
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(URL))
//                .header("x-rapidapi-key", "392d7c7c2amsh14dc46d53a988afp19ac54jsn7866ab04b564")
//                .header("x-rapidapi-host", "dolarapi.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//        //Gson es una lib de google q nos permite parsear la response en un json
//        final ApiDolarResponse apiDolarResponse = new Gson().fromJson(response.body(), ApiDolarResponse.class); // hacemos un json tomando el response.body() y mapeandolo aApiWeatherResponse.class q tiene los campos q necesitamos en nuestra respuesta q mostrara postman
//
//        return apiDolarResponse;
//    }

}
