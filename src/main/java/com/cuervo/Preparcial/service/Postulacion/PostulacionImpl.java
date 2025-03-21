package com.cuervo.Preparcial.service.Postulacion;

import com.cuervo.Preparcial.model.Postulacion;
import com.cuervo.Preparcial.model.Postulacion;
import com.cuervo.Preparcial.repository.PostulacionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostulacionImpl implements PostulacionService {
    private final PostulacionRepository postulacionRepository;
    
    @Autowired
    public PostulacionImpl(PostulacionRepository postulacionRepository) {
        this.postulacionRepository = postulacionRepository;
    }


    @Override
    @Transactional
    public Postulacion guardarPostulacion(Postulacion postulacion) {
        // Establecer fechas de creación y actualización
        if (postulacion.getFecha_postulacion() == null) {
            postulacion.setFecha_postulacion(LocalDateTime.now());
        }

        return postulacionRepository.save(postulacion);
    }

    @Override
    @Transactional
    public Postulacion actualizarPostulacion(Long id, Postulacion postulacionActualizado) {
        return postulacionRepository.findById(id)
                .map(postulacionExistente -> {
                    // Actualizar campos
                    postulacionExistente.setCandidato(postulacionActualizado.getCandidato());
                    postulacionExistente.setOfertaEmpleo(postulacionActualizado.getOfertaEmpleo());
                    postulacionExistente.setEstado(postulacionActualizado.getEstado());
                    postulacionExistente.setComentarios(postulacionActualizado.getComentarios());


                    return postulacionRepository.save(postulacionExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Postulacion para empleo no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Postulacion> findAllPostulaciones() {
        return postulacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Postulacion> obtenerPostulacionPorId(Long id) {
        return postulacionRepository.findById(id);
    }

    @Override
    @Transactional
    public void eliminarPostulacion(Long id) {
        if (!postulacionRepository.existsById(id)) {
            throw new EntityNotFoundException("Postulacion para empleo no encontrada con ID: " + id);
        }

        postulacionRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<Postulacion> buscarPostulacionCandidato(Long candidato) {
        return postulacionRepository.findByCandidatoId(candidato);
    }


    @Transactional(readOnly = true)
    public List<Postulacion> buscarPostulacionOferta(Long id) {
        return postulacionRepository.findByOfertaEmpleoId(id);
    }

}
