package com.cuervo.Preparcial.service.OfertaEmpleo;

import com.cuervo.Preparcial.model.OfertaEmpleo;

import com.cuervo.Preparcial.repository.OfertaEmpleoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OfertaEmpleoImpl implements OfertaEmpleoService{
    private final OfertaEmpleoRepository ofertaEmpleoRepository;
    
    @Autowired
    public OfertaEmpleoImpl(OfertaEmpleoRepository ofertaEmpleoRepository) {
       this.ofertaEmpleoRepository = ofertaEmpleoRepository;
    }
    
    @Override
    @Transactional
    public OfertaEmpleo guardarOfertaEmpleo(OfertaEmpleo ofertaEmpleo) {
        // Establecer fechas de creación y actualización
        if (ofertaEmpleo.getFecha_publicacion() == null) {
            ofertaEmpleo.setFecha_publicacion(LocalDateTime.now());
        }
        
        return ofertaEmpleoRepository.save(ofertaEmpleo);
    }

    @Override
    @Transactional
    public OfertaEmpleo actualizarOfertaEmpleo(Long id, OfertaEmpleo ofertaEmpleoActualizado) {
        return ofertaEmpleoRepository.findById(id)
                .map(ofertaEmpleoExistente -> {
                    // Actualizar campos
                    ofertaEmpleoExistente.setTitulo(ofertaEmpleoActualizado.getTitulo());
                    ofertaEmpleoExistente.setDescripcion(ofertaEmpleoActualizado.getDescripcion());
                    ofertaEmpleoExistente.setSalario(ofertaEmpleoActualizado.getSalario());
                    ofertaEmpleoExistente.setRequisitos(ofertaEmpleoActualizado.getRequisitos());
                    ofertaEmpleoExistente.setJornada(ofertaEmpleoActualizado.getJornada());
                    ofertaEmpleoExistente.setModalidad(ofertaEmpleoActualizado.getModalidad());
                    ofertaEmpleoExistente.setUbicacion(ofertaEmpleoActualizado.getUbicacion());
                    ofertaEmpleoExistente.setFecha_publicacion((LocalDateTime.now()));
                    ofertaEmpleoExistente.setFecha_expiracion(ofertaEmpleoActualizado.getFecha_expiracion());
                    ofertaEmpleoExistente.setEstado(ofertaEmpleoActualizado.getEstado());
                    ofertaEmpleoExistente.setEmpresa(ofertaEmpleoActualizado.getEmpresa());
                    ofertaEmpleoExistente.setPostulaciones(ofertaEmpleoActualizado.getPostulaciones());

                    // Guardar en la base de datos
                    return ofertaEmpleoRepository.save(ofertaEmpleoExistente);
                })
                .orElseThrow(() -> new EntityNotFoundException("Oferta de empleo no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OfertaEmpleo> obtenerTodosLosOfertaEmpleos() {
        return ofertaEmpleoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OfertaEmpleo> obtenerOfertaEmpleoPorId(Long id) {
        return ofertaEmpleoRepository.findById(id);
    }

    @Override
    @Transactional
    public void eliminarOfertaEmpleo(Long id) {
        if (!ofertaEmpleoRepository.existsById(id)) {
            throw new EntityNotFoundException("Oferta de empleo no encontrada con ID: " + id);
        }

        ofertaEmpleoRepository.deleteById(id);
    }


    @Transactional(readOnly = true)
    public List<OfertaEmpleo> buscarOfertaEmpleosTitulo(String titulo) {
        return ofertaEmpleoRepository.findByTitulo(titulo);
    }


    @Transactional(readOnly = true)
    public List<OfertaEmpleo> buscarOfertaEmpleosEmpresa(String empresa) {
        return ofertaEmpleoRepository.findByEmpresaNombre(empresa);
    }


    @Transactional(readOnly = true)
    public List<OfertaEmpleo> buscarOfertaEmpleosUbicacion(String ubicacion) {
        return ofertaEmpleoRepository.findByUbicacion(ubicacion);
    }




}
