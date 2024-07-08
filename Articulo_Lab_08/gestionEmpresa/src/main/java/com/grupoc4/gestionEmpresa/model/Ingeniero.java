package com.grupoc4.gestionEmpresa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ingenieros")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Ingeniero {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDIng")
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Especialidad", nullable = false)
    private String especialidad;

    @Column(name = "Cargo", nullable = false)
    private String cargo;

    @Column(name = "IDProy", nullable = false)
    private Integer IDProy;
}