package com.grupoc4.gestionEmpresa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Proyecto_Ingeniero")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ProyectoIngeniero {

    @Column(name = "IDProy", nullable = false)
    private Integer idProy;

    @Column(name = "IDIng", nullable = false)
    private Integer idIng;
}