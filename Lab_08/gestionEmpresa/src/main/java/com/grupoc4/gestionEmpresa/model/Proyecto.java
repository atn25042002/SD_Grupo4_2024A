package com.grupoc4.gestionEmpresa.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Proyectos")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Proyecto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDProy")
    private Integer id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Column(name = "Fec_Inicio", nullable = false)
    private Date fecInicio;

    @Column(name = "Fec_Termino")
    private Date fecTermino;

    @Column(name = "IDDpto", nullable = false)
    private Integer idDpto;

    @Column(name = "IDIng", nullable = false)
    private Integer idIng;
}