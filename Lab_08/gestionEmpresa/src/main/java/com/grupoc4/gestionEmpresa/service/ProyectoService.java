package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.model.Proyecto;
import com.grupoc4.gestionEmpresa.repository.ProyectoRepository;

import java.util.List;

@Service
@Transactional
public class ProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> getAllProyectos() {
        return proyectoRepository.findAll();
    }

    public Proyecto getProyectoById(int id) {
        return proyectoRepository.findById(id);
    }

    public Proyecto saveProyecto(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public Proyecto updateProyecto(Proyecto proyecto) {
        return proyectoRepository.update(proyecto);
    }

    public Proyecto deleteProyectoById(int id) {
        return proyectoRepository.deleteById(id);
    }
}
