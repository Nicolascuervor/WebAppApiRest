package com.cuervo.Preparcial.service;


import com.cuervo.Preparcial.model.Empresa;
import com.cuervo.Preparcial.model.OfertaEmpleo;
import com.cuervo.Preparcial.repository.EmpresaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    @Transactional
    public List<Empresa> findAllempresas() {
        return (List<Empresa>) empresaRepository.findAll();
    }


    @Transactional
    public Optional<Empresa> findEmpresaById(Long id) {
        return empresaRepository.findById(id);
    }



    @Transactional
    public Empresa guardarEmpresa(Empresa empresa) {
        if (empresa.getFecha_registro() == null) {
            empresa.setFecha_registro(LocalDateTime.now());
        }
        return empresaRepository.save(empresa);
    }


    @Transactional
    public void eliminarEmpresa(Long id) {
        if (!empresaRepository.existsById(id)) {
            throw new EntityNotFoundException("Empresa no encontrada con ID: " + id);
        }
        empresaRepository.deleteById(id);
    }


    @Transactional
    public Empresa actualizarEmpresa(Long id, Empresa empresaActualizado) {
        return empresaRepository.findById(id)
                .map(empresaExistente -> {
                    // Actualizar campos
                    empresaExistente.setNombre(empresaActualizado.getNombre());
                    empresaExistente.setDescripcion(empresaActualizado.getDescripcion());
                    empresaExistente.setSector(empresaActualizado.getSector());
                    empresaExistente.setUbicacion(empresaActualizado.getUbicacion());
                    empresaExistente.setCorreo_contacto(empresaActualizado.getCorreo_contacto());



                    return empresaRepository.save(empresaExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + id));
    }

}
