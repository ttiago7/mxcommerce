package com.commerce.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    @NotNull
    @Size(min = 1, max = 180)
    private String titulo;//180 c
    @NotNull
    @Size(min = 1, max = 500)
    private String descripcionCorta;//500 c
    @NotNull
    @Size(min = 1, max = 2500)
    private String descripcionLarga;//2500 c
    @NotNull
    private String ubicacion;// ciudad, estado o provincia, pais
    @NotNull
    private BigDecimal precio;

    private Integer stock; // opcional
    private String cp;//opcional
    private String latitud; //opcional
    private String longitud; //opcional
    private String urlImagen; //opcional

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt = new Date();

    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt;

    public Producto(Integer codigo, @NotNull @Size(min = 1, max = 180) String titulo, @NotNull @Size(min = 1, max = 500) String descripcionCorta, @NotNull @Size(min = 1, max = 2500) String descripcionLarga, @NotNull String ubicacion, @NotNull BigDecimal precio, Integer stock, String cp, String latitud, String longitud, String urlImagen, Date createdAt, Date updatedAt) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcionCorta = descripcionCorta;
        this.descripcionLarga = descripcionLarga;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.stock = stock;
        this.cp = cp;
        this.latitud = latitud;
        this.longitud = longitud;
        this.urlImagen = urlImagen;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @PreUpdate
    public void setUpdatedAt() {
        this.updatedAt= new Date();
    }
}
