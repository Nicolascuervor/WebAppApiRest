package com.cuervo.Preparcial.repository;

import com.cuervo.Preparcial.model.OfertaEmpleo;
import com.cuervo.Preparcial.model.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByCandidatoId(Long CandidatoId);
    List<Postulacion> findByOfertaEmpleoId(Long ofertaId);

}
