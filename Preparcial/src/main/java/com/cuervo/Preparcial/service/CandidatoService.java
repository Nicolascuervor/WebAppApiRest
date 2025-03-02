package com.cuervo.Preparcial.service;

import com.cuervo.Preparcial.model.Candidato;
import com.cuervo.Preparcial.repository.CandidatoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidatoService {
    @Autowired
    CandidatoRepository candidatoRepository;

    @Transactional
    public List<Candidato> findAllCandidatos() {
        return (List<Candidato>) candidatoRepository.findAll();
    }


    @Transactional
    public Optional<Candidato> findCandidatoById(Long id) {
        return candidatoRepository.findById(id);
    }

    @Transactional
    public Candidato guardarCandidato(Candidato candidato) {
        if (candidato.getFecha_registro() == null) {
            candidato.setFecha_registro(LocalDateTime.now());
        }
        return candidatoRepository.save(candidato);
    }


    @Transactional
    public void eliminarCandidato(Long id) {
        if (!candidatoRepository.existsById(id)) {
            throw new EntityNotFoundException("Candidato no encontrado con ID: " + id);
        }
        candidatoRepository.deleteById(id);
    }

    @Transactional
    public Candidato actualizarCandidato(Long id, Candidato candidatoActualizado) {
        return candidatoRepository.findById(id)
                .map(candidatoExistente -> {
                    // Actualizar campos
                    candidatoExistente.setNombre(candidatoActualizado.getNombre());
                    candidatoExistente.setApellido(candidatoActualizado.getApellido());
                    candidatoExistente.setCorreo(candidatoActualizado.getCorreo());
                    candidatoExistente.setTelefono(candidatoActualizado.getTelefono());
                    candidatoExistente.setCv(candidatoActualizado.getCv());
                    candidatoExistente.setHabilidades(candidatoActualizado.getHabilidades());
                    candidatoExistente.setExperienciaLaboral(candidatoActualizado.getExperienciaLaboral());
                    candidatoExistente.setEduacion(candidatoActualizado.getEduacion());
                    candidatoExistente.setFecha_registro(LocalDateTime.now());


                    return candidatoRepository.save(candidatoExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Candidato no encontrado con ID: " + id));
    }


}
