package com.cuervo.Preparcial.repository;

import com.cuervo.Preparcial.model.Candidato;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatoRepository extends CrudRepository<Candidato, Long> {}
