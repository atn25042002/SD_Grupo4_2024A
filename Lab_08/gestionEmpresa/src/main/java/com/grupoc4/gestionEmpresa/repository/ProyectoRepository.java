package com.grupoc4.gestionEmpresa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.grupoc4.gestionEmpresa.model.Proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProyectoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class ProyectoMapper implements RowMapper<Proyecto> {
        @Override
        public Proyecto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Proyecto proyecto = new Proyecto();
            proyecto.setId(rs.getInt("IDProy"));
            proyecto.setNombre(rs.getString("Nombre"));
            proyecto.setFecInicio(rs.getDate("Fec_Inicio"));
            proyecto.setFecTermino(rs.getDate("Fec_Termino"));
            proyecto.setIdDpto(rs.getInt("IDDpto"));
            proyecto.setIdIng(rs.getInt("IDIng"));
            return proyecto;
        }
    }

    public List<Proyecto> findAll() {
        String sql = "SELECT * FROM Proyectos";
        return jdbcTemplate.query(sql, new ProyectoMapper());
    }

    public Proyecto findById(int id) {
        String sql = "SELECT * FROM Proyectos WHERE IDProy = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ProyectoMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null; // Or handle as needed, e.g., throw a custom exception
        }
    }

    public Proyecto save(Proyecto proyecto) {
        String sql = "INSERT INTO Proyectos (Nombre, Fec_Inicio, Fec_Termino, IDDpto, IDIng) VALUES (?, ?, ?, ?, ?) RETURNING IDProy";
        try {
            int id = jdbcTemplate.queryForObject(sql, Integer.class, proyecto.getNombre(), proyecto.getFecInicio(), proyecto.getFecTermino(), proyecto.getIdDpto(), proyecto.getIdIng());
            return findById(id);
        } catch (DataAccessException e) {
            return null; // Or handle as needed
        }
    }

    public Proyecto update(Proyecto proyecto) {
        String sql = "UPDATE Proyectos SET Nombre = ?, Fec_Inicio = ?, Fec_Termino = ?, IDDpto = ?, IDIng = ? WHERE IDProy = ?";
        try {
            jdbcTemplate.update(sql, proyecto.getNombre(), proyecto.getFecInicio(), proyecto.getFecTermino(), proyecto.getIdDpto(), proyecto.getIdIng(), proyecto.getId());
            return findById(proyecto.getId());
        } catch (DataAccessException e) {
            return null; // Or handle as needed
        }
    }

    public Proyecto deleteById(int id) {
        String sql = "DELETE FROM Proyectos WHERE IDProy = ?";
        try {
            Proyecto proyecto = findById(id); // Get the entity before deleting
            jdbcTemplate.update(sql, id);
            return proyecto;
        } catch (DataAccessException e) {
            return null; // Or handle as needed
        }
    }
}