package com.cuervo.Preparcial.repository;

import com.cuervo.Preparcial.model.OfertaEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaEmpleoRepository extends JpaRepository<OfertaEmpleo, Long> {
    List<OfertaEmpleo> findByTitulo(String nombre);
    List<OfertaEmpleo> findByEmpresaNombre(String empresa);
    List<OfertaEmpleo> findByUbicacion(String ubicacion);

}
