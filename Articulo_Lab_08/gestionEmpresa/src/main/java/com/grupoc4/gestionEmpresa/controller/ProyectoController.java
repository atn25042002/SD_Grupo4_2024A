package com.grupoc4.gestionEmpresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupoc4.gestionEmpresa.model.Proyecto;
import com.grupoc4.gestionEmpresa.service.ProyectoService;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "http://localhost:8000")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping
    public ResponseEntity<List<Proyecto>> getAllProyectos() {
        List<Proyecto> proyectos = proyectoService.getAllProyectos();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> getProyectoById(@PathVariable int id) {
        Proyecto proyecto = proyectoService.getProyectoById(id);
        return new ResponseEntity<>(proyecto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Proyecto> createProyecto(@RequestBody Proyecto proyecto) {
        Proyecto createdProyecto = proyectoService.saveProyecto(proyecto);
        return new ResponseEntity<>(createdProyecto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> updateProyecto(@PathVariable int id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        Proyecto updatedProyecto = proyectoService.updateProyecto(proyecto);
        return new ResponseEntity<>(updatedProyecto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable int id) {
        proyectoService.deleteProyectoById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/departamento/{idDpto}")
    public ResponseEntity<List<Proyecto>> getProyectosByDepartamento(@PathVariable int idDpto) {
        List<Proyecto> proyectos = proyectoService.getProyectosByDepartamento(idDpto);
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }
}