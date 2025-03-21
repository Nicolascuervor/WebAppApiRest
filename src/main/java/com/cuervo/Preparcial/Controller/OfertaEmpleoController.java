package com.cuervo.Preparcial.Controller;

import com.cuervo.Preparcial.model.OfertaEmpleo;
import com.cuervo.Preparcial.service.OfertaEmpleo.OfertaEmpleoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/OfertasEmpleo")
public class OfertaEmpleoController {
    OfertaEmpleoService ofertaEmpleoService;

    
    @Autowired
    public OfertaEmpleoController(OfertaEmpleoService ofertaEmpleoService) {
        this.ofertaEmpleoService = ofertaEmpleoService;

    }

    // Crear un nuevo ofertaEmpleo
    @PostMapping
    public ResponseEntity<OfertaEmpleo> crearOfertaEmpleo(@RequestBody OfertaEmpleo ofertaEmpleo) {
        OfertaEmpleo nuevoOfertaEmpleo = ofertaEmpleoService.guardarOfertaEmpleo(ofertaEmpleo);
        return new ResponseEntity<>(nuevoOfertaEmpleo, HttpStatus.CREATED);
    }

    // Obtener todos los ofertaEmpleos
    @GetMapping
    public ResponseEntity<List<OfertaEmpleo>> obtenerTodosLosOfertaEmpleos() {
        List<OfertaEmpleo> ofertaEmpleos = ofertaEmpleoService.obtenerTodosLosOfertaEmpleos();
        return new ResponseEntity<>(ofertaEmpleos, HttpStatus.OK);
    }

    // Obtener un ofertaEmpleo por su ID
    @GetMapping("/id/{id}")
    public ResponseEntity<OfertaEmpleo> obtenerOfertaEmpleoPorId(@PathVariable Long id) {
        return ofertaEmpleoService.obtenerOfertaEmpleoPorId(id)
                .map(ofertaEmpleo -> new ResponseEntity<>(ofertaEmpleo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un ofertaEmpleo existente
    @PutMapping("/{id}")
    public ResponseEntity<OfertaEmpleo> actualizarOfertaEmpleo(@PathVariable Long id, @RequestBody OfertaEmpleo ofertaEmpleo) {
        try {
            OfertaEmpleo ofertaEmpleoActualizado = ofertaEmpleoService.actualizarOfertaEmpleo(id, ofertaEmpleo);
            return new ResponseEntity<>(ofertaEmpleoActualizado, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar una oferta de empleo
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarOfertaEmpleo(@PathVariable Long id) {
        try {
            ofertaEmpleoService.eliminarOfertaEmpleo(id);
            return new ResponseEntity<>(Map.of("eliminado", true), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ubicacion/{ubicacion}")
    public ResponseEntity<List<OfertaEmpleo>> obtenerOfertaEmpleoPorUbicacion(@PathVariable String ubicacion) {
        List<OfertaEmpleo> ofertas = ofertaEmpleoService.buscarOfertaEmpleosUbicacion(ubicacion);

        if (ofertas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }


    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<OfertaEmpleo>> obtenerOfertaEmpleoPorTitulo(@PathVariable String titulo) {
        List<OfertaEmpleo> ofertas = ofertaEmpleoService.buscarOfertaEmpleosTitulo(titulo);

        if (ofertas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }


    @GetMapping("/empresa/{nombre}")
    public ResponseEntity<List<OfertaEmpleo>> buscarOfertaEmpleos(@PathVariable String nombre) {
        List<OfertaEmpleo> ofertasFiltradas = ofertaEmpleoService
                .obtenerTodosLosOfertaEmpleos()
                .stream()
                .filter(oferta -> oferta.getEmpresa().getNombre().equalsIgnoreCase(nombre))
                .collect(Collectors.toList());

        if (ofertasFiltradas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ofertasFiltradas, HttpStatus.OK);
    }




}
