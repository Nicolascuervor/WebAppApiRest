package com.cuervo.Preparcial.Controller;



import com.cuervo.Preparcial.model.Postulacion;
import com.cuervo.Preparcial.service.Postulacion.PostulacionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Postulaciones")
public class PostulacionController {
    PostulacionService postulacionService;
    
    @Autowired
    public PostulacionController(PostulacionService postulacionService) {
        this.postulacionService = postulacionService;
    }


    // Crear una nueva postulacion
    @PostMapping
    public ResponseEntity<Postulacion> crearPostulacion(@RequestBody Postulacion postulacion) {
        Postulacion nuevoPostulacion = postulacionService.guardarPostulacion(postulacion);
        return new ResponseEntity<>(nuevoPostulacion, HttpStatus.CREATED);
    }

    // Obtener todas las postulaciones
    @GetMapping
    public ResponseEntity<List<Postulacion>> obtenerTodosLosPostulacions() {
        List<Postulacion> postulacions = postulacionService.findAllPostulaciones();
        return new ResponseEntity<>(postulacions, HttpStatus.OK);
    }

    // Obtener una postulacion por su ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Postulacion> obtenerPostulacionPorId(@PathVariable Long id) {
        return postulacionService.obtenerPostulacionPorId(id)
                .map(postulacion -> new ResponseEntity<>(postulacion, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un postulacion existente
    @PutMapping("/{id}")
    public ResponseEntity<Postulacion> actualizarPostulacion(@PathVariable Long id, @RequestBody Postulacion postulacion) {
        try {
            Postulacion postulacionActualizado = postulacionService.actualizarPostulacion(id, postulacion);
            return new ResponseEntity<>(postulacionActualizado, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una postulacion
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarPostulacion(@PathVariable Long id) {
        try {
            postulacionService.eliminarPostulacion(id);
            return new ResponseEntity<>(Map.of("eliminado", true), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/candidatos/{id}")
    public ResponseEntity<List<Postulacion>> buscarOfertaEmpleos(@PathVariable Long id) {
        List<Postulacion> postulacionesFiltradas = postulacionService
                .findAllPostulaciones()
                .stream()
                .filter(oferta -> Objects.equals(oferta.getCandidato().getId(), id))
                .collect(Collectors.toList());

        if (postulacionesFiltradas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postulacionesFiltradas, HttpStatus.OK);
    }

    @GetMapping("/ofertaEmpleo/{id}")
    public ResponseEntity<List<Postulacion>> buscarOferta(@PathVariable Long id) {
        List<Postulacion> postulacionesFiltradas = postulacionService
                .findAllPostulaciones()
                .stream()
                .filter(oferta -> Objects.equals(oferta.getOfertaEmpleo().getId(), id))
                .collect(Collectors.toList());

        if (postulacionesFiltradas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postulacionesFiltradas, HttpStatus.OK);
    }

}
