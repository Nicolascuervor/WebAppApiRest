package com.cuervo.Preparcial.service.Postulacion;

import com.cuervo.Preparcial.model.OfertaEmpleo;
import com.cuervo.Preparcial.model.Postulacion;

import java.util.List;
import java.util.Optional;

public interface PostulacionService {
    Postulacion guardarPostulacion(Postulacion postulacion);

    Postulacion actualizarPostulacion(Long id, Postulacion postulacion);

    List<Postulacion> findAllPostulaciones();

    void eliminarPostulacion(Long id);

    // Obtener un ofertaEmpleo por su ID
    Optional<Postulacion> obtenerPostulacionPorId(Long id);

    // Buscar ofertaEmpleos por empresa
    List<Postulacion> buscarPostulacionCandidato(Long id);

    // Buscar ofertaEmpleos por titulo
    List<Postulacion> buscarPostulacionOferta(Long id );


}
