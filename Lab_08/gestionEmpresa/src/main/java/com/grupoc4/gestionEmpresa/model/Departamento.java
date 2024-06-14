package com.grupoc4.gestionEmpresa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Departamentos")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Departamento {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDpto")
    private Integer id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Fax")
    private String fax;

    // Getters and setters
}