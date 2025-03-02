package com.cuervo.Preparcial.service.OfertaEmpleo;

import com.cuervo.Preparcial.model.Empresa;
import com.cuervo.Preparcial.model.OfertaEmpleo;

import java.util.List;
import java.util.Optional;

public interface OfertaEmpleoService {

    // Crear un ofertaEmpleo

    OfertaEmpleo guardarOfertaEmpleo(OfertaEmpleo ofertaEmpleo);

    // Actualizar un ofertaEmpleo existente
    OfertaEmpleo actualizarOfertaEmpleo(Long id, OfertaEmpleo ofertaEmpleo);

    // Obtener todos los ofertaEmpleos
    List<OfertaEmpleo> obtenerTodosLosOfertaEmpleos();

    // Eliminar un ofertaEmpleo
    void eliminarOfertaEmpleo(Long id);


    // Obtener un ofertaEmpleo por su ID
    Optional<OfertaEmpleo> obtenerOfertaEmpleoPorId(Long id);


    // Buscar ofertaEmpleos por empresa
    List<OfertaEmpleo> buscarOfertaEmpleosEmpresa(String empresa);

    // Buscar ofertaEmpleos por titulo
    List<OfertaEmpleo> buscarOfertaEmpleosTitulo(String titulo);

    // Buscar ofertaEmpleos por ubicacion
    List<OfertaEmpleo> buscarOfertaEmpleosUbicacion(String ubicacion);
}
