package com.commerce.demo.repository;

import com.commerce.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{// clase Cliente que tiene un PK de tipo Integer

    @Query("Select p from Producto p where p.titulo like %:titulo%")
    public List<Producto> findProductoLike(@Param("titulo") String titulo);
}
