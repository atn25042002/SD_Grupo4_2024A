package com.grupoc4.gestionEmpresa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.grupoc4.gestionEmpresa.model.Ingeniero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class IngenieroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class IngenieroMapper implements RowMapper<Ingeniero> {
        @Override
        public Ingeniero mapRow(ResultSet rs, int rowNum) throws SQLException {
            Ingeniero ingeniero = new Ingeniero();
            ingeniero.setId(rs.getInt("IDIng"));
            ingeniero.setNombre(rs.getString("Nombre"));
            ingeniero.setEspecialidad(rs.getString("Especialidad"));
            ingeniero.setCargo(rs.getString("Cargo"));
            return ingeniero;
        }
    }

    public List<Ingeniero> findAll() {
        String sql = "SELECT * FROM Ingenieros";
        return jdbcTemplate.query(sql, new IngenieroMapper());
    }

    public Ingeniero findById(int id) {
        String sql = "SELECT * FROM Ingenieros WHERE IDIng = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new IngenieroMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Ingeniero save(Ingeniero ingeniero) {
        String sql = "INSERT INTO Ingenieros (Nombre, Especialidad, Cargo) VALUES (?, ?, ?) RETURNING IDIng";
        try {
            int id = jdbcTemplate.queryForObject(sql, Integer.class, ingeniero.getNombre(), ingeniero.getEspecialidad(), ingeniero.getCargo());
            return findById(id);
        } catch (DataAccessException e) {
            return null; // Or handle as needed
        }
    }

    public Ingeniero update(Ingeniero ingeniero) {
        String sql = "UPDATE Ingenieros SET Nombre = ?, Especialidad = ?, Cargo = ? WHERE IDIng = ?";
        try {
            jdbcTemplate.update(sql, ingeniero.getNombre(), ingeniero.getEspecialidad(), ingeniero.getCargo(), ingeniero.getId());
            return findById(ingeniero.getId());
        } catch (DataAccessException e) {
            return null; // Or handle as needed
        }
    }

    public Ingeniero deleteById(int id) {
        String sql = "DELETE FROM Ingenieros WHERE IDIng = ?";
        try {
            Ingeniero ingeniero = findById(id); // Get the entity before deleting
            jdbcTemplate.update(sql, id);
            return ingeniero;
        } catch (DataAccessException e) {
            return null; // Or handle as needed
        }
    }
}
