package com.cuervo.Preparcial.Controller;

import com.cuervo.Preparcial.model.Candidato;
import com.cuervo.Preparcial.service.CandidatoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/candidatos")
public class CandidatoController {
    @Autowired
    CandidatoService candidatoService;

    @GetMapping()
    public ArrayList<Candidato> findAll() {
        return (ArrayList<Candidato>) candidatoService.findAllCandidatos();
    }

    @PostMapping()
    public Candidato guardarCandidato(@RequestBody Candidato candidato) {
        return candidatoService.guardarCandidato(candidato);
    }

    @GetMapping(path = "/{id}")
    public Optional<Candidato> findCandidatoById(@PathVariable Long id) {
        return this.candidatoService.findCandidatoById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Candidato> actualizarCandidato(@PathVariable Long id, @RequestBody Candidato candidato) {
        Optional<Candidato> candidatoExistente = candidatoService.findCandidatoById(id);

        if (candidatoExistente.isPresent()) {
            candidato.setId(id); // Asegurar que el ID sea el mismo
            Candidato actualizado = candidatoService.guardarCandidato(candidato);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarProducto(@PathVariable Long id) {
        try {
            candidatoService.eliminarCandidato(id);
            return new ResponseEntity<>(Map.of("eliminado", true), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}