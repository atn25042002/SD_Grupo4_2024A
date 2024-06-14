package com.grupoc4.gestionEmpresa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupoc4.gestionEmpresa.model.Ingeniero;
import com.grupoc4.gestionEmpresa.service.IngenieroService;

@RestController
@RequestMapping("/ingenieros")
@CrossOrigin(origins = "http://localhost:8000")
public class IngenieroController {

    @Autowired
    private IngenieroService ingenieroService;

    @GetMapping("")
    public List<Ingeniero> getAllIngenieros() {
        return ingenieroService.getAllIngenieros();
    }

    @GetMapping("/{id}")
    public Ingeniero getIngenieroById(@PathVariable Integer id) {
        return ingenieroService.getIngenieroById(id);
    }

    @PostMapping("")
    public Ingeniero createIngeniero(@RequestBody Ingeniero ingeniero) {
        return ingenieroService.saveIngeniero(ingeniero);
    }

    @PutMapping("/{id}")
    public Ingeniero updateIngeniero(@PathVariable Integer id, @RequestBody Ingeniero ingeniero) {
        ingeniero.setId(id);
        return ingenieroService.updateIngeniero(ingeniero);
    }

    @DeleteMapping("/{id}")
    public Ingeniero deleteIngenieroById(@PathVariable Integer id) {
        return ingenieroService.deleteIngenieroById(id);
    }
}
