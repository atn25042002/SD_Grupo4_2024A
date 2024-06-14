package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.model.Departamento;
import com.grupoc4.gestionEmpresa.repository.DepartamentoRepository;

import java.util.List;

@Service
@Transactional
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> getAllDepartamentos() {
        return departamentoRepository.findAll();
    }

    public Departamento getDepartamentoById(int id){
        return departamentoRepository.findById(id);
    }

    public Departamento saveDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento updateDepartamento(Departamento departamento) {
        return departamentoRepository.update(departamento);
    }

    public Departamento deleteDepartamentoById(int id) {
        return departamentoRepository.deleteById(id);
    }
}