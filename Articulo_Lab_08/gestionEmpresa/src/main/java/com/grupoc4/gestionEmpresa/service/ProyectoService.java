package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.error.ResourceNotFoundException;
import com.grupoc4.gestionEmpresa.model.Departamento;
import com.grupoc4.gestionEmpresa.model.Proyecto;
import com.grupoc4.gestionEmpresa.repository.DepartamentoRepository;
import com.grupoc4.gestionEmpresa.repository.ProyectoRepository;

import java.util.List;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    public Proyecto getProyectoById(int id) {
        Proyecto proyecto = proyectoRepository.findById(id);
        if (proyecto == null) {
            throw new ResourceNotFoundException("Proyecto no encontrado con id " + id);
        }
        return proyecto;
    }

    public Proyecto saveProyecto(Proyecto proyecto) {
        Integer idDpto= proyecto.getIdDpto();
        Departamento departamento= departamentoRepository.findById(idDpto);
        if (departamento == null) {
            throw new ResourceNotFoundException("Departamento no encontrado con id " + idDpto);
        }
        return proyectoRepository.save(proyecto);
    }

    public Proyecto updateProyecto(Proyecto proyecto) {
        Integer idDpto= proyecto.getId();
        Departamento departamento= departamentoRepository.findById(idDpto);
        if (departamento == null) {
            throw new ResourceNotFoundException("Departamento no encontrado con id " + idDpto);
        }

        Proyecto updatedProyecto = proyectoRepository.update(proyecto);
        if (updatedProyecto == null) {
            throw new ResourceNotFoundException("No se pudo actualizar el proyecto con id " + proyecto.getId());
        }
        return updatedProyecto;
    }

    public Proyecto deleteProyectoById(int id) {
        Proyecto proyecto = proyectoRepository.deleteById(id);
        if (proyecto == null) {
            throw new ResourceNotFoundException("No se pudo eliminar el proyecto con id " + id);
        }
        return proyecto;
    }

    public List<Proyecto> getProyectosByDepartamento(int idDpto) {
        Departamento departamento= departamentoRepository.findById(idDpto);
        if (departamento == null) {
            throw new ResourceNotFoundException("Departamento no encontrado con id " + idDpto);
        }
        return proyectoRepository.findByDptoId(idDpto);
    }
}