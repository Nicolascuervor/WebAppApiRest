package com.cuervo.Preparcial.Controller;

import com.cuervo.Preparcial.model.Empresa;
import com.cuervo.Preparcial.model.Empresa;
import com.cuervo.Preparcial.service.EmpresaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    
    @Autowired
    EmpresaService empresaService;


    @GetMapping()
    public ArrayList<Empresa> findAll() {
        return (ArrayList<Empresa>) empresaService.findAllempresas();
    }

    @PostMapping()
    public Empresa guardarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.guardarEmpresa(empresa);
    }

    @GetMapping(path = "/{id}")
    public Optional<Empresa> findEmpresaById(@PathVariable Long id) {
        return this.empresaService.findEmpresaById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        Optional<Empresa> empresaExistente = empresaService.findEmpresaById(id);

        if (empresaExistente.isPresent()) {
            empresa.setId(id); // Asegurar que el ID sea el mismo
            Empresa actualizado = empresaService.guardarEmpresa(empresa);
            return ResponseEntity.ok(actualizado);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpresa(@PathVariable Long id) {
        try {
            empresaService.eliminarEmpresa(id);
            return new ResponseEntity<>(Map.of("eliminado", true), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
