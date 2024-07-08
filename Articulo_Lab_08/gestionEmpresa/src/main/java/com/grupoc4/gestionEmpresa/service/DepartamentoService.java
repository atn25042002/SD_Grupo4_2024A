package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.error.ResourceNotFoundException;
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

    public Departamento getDepartamentoById(int id) {
        Departamento departamento = departamentoRepository.findById(id);
        if (departamento.getId() == 0) {
            throw new ResourceNotFoundException("Departamento no encontrado con id " + id);
        }
        return departamento;
    }

    public Departamento saveDepartamento(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    public Departamento updateDepartamento(Departamento departamento) {
        Departamento updatedDepartamento = departamentoRepository.update(departamento);
        if (updatedDepartamento.getId() == 0) {
            throw new ResourceNotFoundException("No se pudo actualizar el departamento con id " + departamento.getId());
        }
        return updatedDepartamento;
    }

    public Departamento deleteDepartamentoById(int id) {
        Departamento departamento = departamentoRepository.deleteById(id);
        if (departamento.getId() == 0) {
            throw new ResourceNotFoundException("No se pudo eliminar el departamento con id " + id);
        }
        return departamento;
    }
}