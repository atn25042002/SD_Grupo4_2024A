package com.grupoc4.gestionEmpresa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public ResponseEntity<List<Departamento>> getAllDepartamentos() {
        List<Departamento> departamentos = departamentoService.getAllDepartamentos();
        return new ResponseEntity<>(departamentos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Departamento> getDepartamentoById(@PathVariable int id) {
        Departamento departamento = departamentoService.getDepartamentoById(id);
        return new ResponseEntity<>(departamento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Departamento> createDepartamento(@RequestBody Departamento departamento) {
        Departamento createdDepartamento = departamentoService.saveDepartamento(departamento);
        return new ResponseEntity<>(createdDepartamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departamento> updateDepartamento(@PathVariable int id, @RequestBody Departamento departamento) {
        departamento.setId(id);
        Departamento updatedDepartamento = departamentoService.updateDepartamento(departamento);
        return new ResponseEntity<>(updatedDepartamento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartamento(@PathVariable int id) {
        departamentoService.deleteDepartamentoById(id);
        return ResponseEntity.ok().build();
    }
}