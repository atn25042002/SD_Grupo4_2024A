package com.grupoc4.gestionEmpresa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupoc4.gestionEmpresa.model.Ingeniero;
import com.grupoc4.gestionEmpresa.repository.IngenieroRepository;

import java.util.List;

@Service
@Transactional
public class IngenieroService {

    @Autowired
    private IngenieroRepository ingenieroRepository;

    public List<Ingeniero> getAllIngenieros() {
        return ingenieroRepository.findAll();
    }

    public Ingeniero getIngenieroById(int id) {
        return ingenieroRepository.findById(id);
    }

    public Ingeniero saveIngeniero(Ingeniero ingeniero) {
        return ingenieroRepository.save(ingeniero);
    }

    public Ingeniero updateIngeniero(Ingeniero ingeniero) {
        return ingenieroRepository.update(ingeniero);
    }

    public Ingeniero deleteIngenieroById(int id) {
        return ingenieroRepository.deleteById(id);
    }

    public List<Ingeniero> getIngenierosByProyecto(int idProy) {
        return ingenieroRepository.findByIdProy(idProy);
    }
}
