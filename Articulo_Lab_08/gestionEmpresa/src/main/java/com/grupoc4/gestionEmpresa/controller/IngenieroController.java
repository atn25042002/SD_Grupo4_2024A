package com.grupoc4.gestionEmpresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupoc4.gestionEmpresa.model.Ingeniero;
import com.grupoc4.gestionEmpresa.service.IngenieroService;

@RestController
@RequestMapping("/ingenieros")
@CrossOrigin(origins = "http://localhost:8000")
public class IngenieroController {

    @Autowired
    private IngenieroService ingenieroService;

    @GetMapping
    public ResponseEntity<List<Ingeniero>> getAllIngenieros() {
        List<Ingeniero> ingenieros = ingenieroService.getAllIngenieros();
        return new ResponseEntity<>(ingenieros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingeniero> getIngenieroById(@PathVariable int id) {
        Ingeniero ingeniero = ingenieroService.getIngenieroById(id);
        return new ResponseEntity<>(ingeniero, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ingeniero> createIngeniero(@RequestBody Ingeniero ingeniero) {
        Ingeniero createdIngeniero = ingenieroService.saveIngeniero(ingeniero);
        return new ResponseEntity<>(createdIngeniero, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingeniero> updateIngeniero(@PathVariable int id, @RequestBody Ingeniero ingeniero) {
        ingeniero.setId(id);
        Ingeniero updatedIngeniero = ingenieroService.updateIngeniero(ingeniero);
        return new ResponseEntity<>(updatedIngeniero, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngeniero(@PathVariable int id) {
        ingenieroService.deleteIngenieroById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/proyecto/{idProyecto}")
    public ResponseEntity<List<Ingeniero>> getIngenierosByProyecto(@PathVariable int idProyecto) {
        List<Ingeniero> ingenieros = ingenieroService.getIngenierosByProyecto(idProyecto);
        return new ResponseEntity<>(ingenieros, HttpStatus.OK);
    }
}