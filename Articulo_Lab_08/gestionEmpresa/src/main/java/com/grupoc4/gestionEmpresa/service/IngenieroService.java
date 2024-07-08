package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.error.ResourceNotFoundException;
import com.grupoc4.gestionEmpresa.model.Ingeniero;
import com.grupoc4.gestionEmpresa.model.Proyecto;
import com.grupoc4.gestionEmpresa.repository.IngenieroRepository;
import com.grupoc4.gestionEmpresa.repository.ProyectoRepository;

import java.util.List;

@Service
@Transactional
public class IngenieroService {

    @Autowired
    private IngenieroRepository ingenieroRepository;
    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Ingeniero> getAllIngenieros() {
        return ingenieroRepository.findAll();
    }

    public Ingeniero getIngenieroById(int id) {
        Ingeniero ingeniero = ingenieroRepository.findById(id);
        if (ingeniero == null) {
            throw new ResourceNotFoundException("Ingeniero no encontrado con id " + id);
        }
        return ingeniero;
    }

    public Ingeniero saveIngeniero(Ingeniero ingeniero) {
        return ingenieroRepository.save(ingeniero);
    }

    public Ingeniero updateIngeniero(Ingeniero ingeniero) {
        Ingeniero updatedIngeniero = ingenieroRepository.update(ingeniero);
        if (updatedIngeniero == null) {
            throw new ResourceNotFoundException("No se pudo actualizar el ingeniero con id " + ingeniero.getId());
        }
        return updatedIngeniero;
    }

    public Ingeniero deleteIngenieroById(int id) {
        Ingeniero ingeniero = ingenieroRepository.deleteById(id);
        if (ingeniero == null) {
            throw new ResourceNotFoundException("No se pudo eliminar el ingeniero con id " + id);
        }
        return ingeniero;
    }

    public List<Ingeniero> getIngenierosByProyecto(int idProyecto) {
        Proyecto proyecto= proyectoRepository.findById(idProyecto);
        if (proyecto == null) {
            throw new ResourceNotFoundException("Proyecto no encontrado con id " + idProyecto);
        }
        return ingenieroRepository.findByIdProy(idProyecto);
    }
}