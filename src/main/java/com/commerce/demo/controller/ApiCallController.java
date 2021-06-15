package com.commerce.demo.controller;


import com.commerce.demo.model.Producto;
import com.commerce.demo.service.ApiCallService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RequestMapping("/api")
@RestController
public class ApiCallController {

    @Autowired
    ApiCallService apiCallService;

    @GetMapping
    @Operation(summary = "Endpoints API geocoding (latitud, longitud)")
    public Object callAPI() {
        try {
            return apiCallService.callApi("El Carmen, Argentina");
        } catch (Throwable  e) {
            e.printStackTrace();
        }
        return null;
    }

}