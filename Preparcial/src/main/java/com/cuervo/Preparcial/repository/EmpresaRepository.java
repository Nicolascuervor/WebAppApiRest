package com.cuervo.Preparcial.repository;

import com.cuervo.Preparcial.model.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends CrudRepository<Empresa, Long> {
}
