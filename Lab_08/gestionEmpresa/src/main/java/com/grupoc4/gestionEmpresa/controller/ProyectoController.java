package com.grupoc4.gestionEmpresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupoc4.gestionEmpresa.model.Proyecto;
import com.grupoc4.gestionEmpresa.service.ProyectoService;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "http://localhost:8000")
public class ProyectoController {

    @Autowired
    private ProyectoService proyectoService;

    @GetMapping("")
    public List<Proyecto> getAllProyectos() {
        return proyectoService.getAllProyectos();
    }

    @GetMapping("/{id}")
    public Proyecto getProyectoById(@PathVariable Integer id) {
        return proyectoService.getProyectoById(id);
    }

    @PostMapping("")
    public Proyecto createProyecto(@RequestBody Proyecto proyecto) {
        return proyectoService.saveProyecto(proyecto);
    }

    @PutMapping("/{id}")
    public Proyecto updateProyecto(@PathVariable Integer id, @RequestBody Proyecto proyecto) {
        proyecto.setId(id);
        return proyectoService.updateProyecto(proyecto);
    }

    @DeleteMapping("/{id}")
    public Proyecto deleteProyectoById(@PathVariable Integer id) {
        return proyectoService.deleteProyectoById(id);
    }

    @GetMapping("/departamento/{idDpto}")
    public List<Proyecto> getProyectosByDepartamento(@PathVariable Integer idDpto) {
        return proyectoService.getProyectosByDepartamento(idDpto);
    }
}