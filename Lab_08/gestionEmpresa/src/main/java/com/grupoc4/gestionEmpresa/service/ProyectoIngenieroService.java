package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.model.ProyectoIngeniero;
import com.grupoc4.gestionEmpresa.repository.ProyectoIngenieroRepository;

import java.util.List;

@Service
public class ProyectoIngenieroService {

    @Autowired
    private ProyectoIngenieroRepository proyectoIngenieroRepository;

    public List<ProyectoIngeniero> getIngenierosByProyectoId(Integer idProy) {
        return proyectoIngenieroRepository.findByIdProy(idProy);
    }

    public List<ProyectoIngeniero> getProyectosByIngenieroId(Integer idIng) {
        return proyectoIngenieroRepository.findByIdIng(idIng);
    }

    public void addIngenieroToProyecto(Integer idProy, Integer idIng) {
        proyectoIngenieroRepository.addIngenieroToProyecto(idProy, idIng);
    }

    public void removeIngenieroFromProyecto(Integer idProy, Integer idIng) {
        proyectoIngenieroRepository.removeIngenieroFromProyecto(idProy, idIng);
    }
}