package com.grupoc4.gestionEmpresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupoc4.gestionEmpresa.model.ProyectoIngeniero;
import com.grupoc4.gestionEmpresa.service.ProyectoIngenieroService;

@RestController
@RequestMapping("/proyecto-ingeniero")
@CrossOrigin(origins = "http://localhost:8000")
public class ProyectoIngenieroController {

    @Autowired
    private ProyectoIngenieroService proyectoIngenieroService;

    @GetMapping("/proyecto/{idProy}")
    public List<ProyectoIngeniero> getIngenierosByProyectoId(@PathVariable Integer idProy) {
        return proyectoIngenieroService.getIngenierosByProyectoId(idProy);
    }

    @GetMapping("/ingeniero/{idIng}")
    public List<ProyectoIngeniero> getProyectosByIngenieroId(@PathVariable Integer idIng) {
        return proyectoIngenieroService.getProyectosByIngenieroId(idIng);
    }

    @PostMapping("/add")
    public void addIngenieroToProyecto(@RequestParam Integer idProy, @RequestParam Integer idIng) {
        proyectoIngenieroService.addIngenieroToProyecto(idProy, idIng);
    }

    @DeleteMapping("/remove")
    public void removeIngenieroFromProyecto(@RequestParam Integer idProy, @RequestParam Integer idIng) {
        proyectoIngenieroService.removeIngenieroFromProyecto(idProy, idIng);
    }
}