package com.grupoc4.gestionEmpresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupoc4.gestionEmpresa.model.Departamento;
import com.grupoc4.gestionEmpresa.service.DepartamentoService;

import java.util.List;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin(origins = "http://localhost:8000")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("")
    public List<Departamento> getAllDepartamentos() {
        return departamentoService.getAllDepartamentos();
    }

    @GetMapping("/{id}")
    public Departamento getDepartamentoById(@PathVariable Integer id) {
        return departamentoService.getDepartamentoById(id);
    }

    @PostMapping("")
    public int createDepartamento(@RequestBody Departamento departamento) {
        return departamentoService.saveDepartamento(departamento);
    }

    @PutMapping("{id}")
    public int updateDepartamento(@PathVariable Integer id, @RequestBody Departamento departamento) {
        return departamentoService.updateDepartamento(departamento);
    }

    @DeleteMapping("/{id}")
    public int deleteDepartamentoById(@PathVariable Integer id) {
        return departamentoService.deleteDepartamentoById(id);
    }
}