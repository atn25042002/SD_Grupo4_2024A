package com.grupoc4.gestionEmpresa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.grupoc4.gestionEmpresa.error.ResourceNotFoundException;
import com.grupoc4.gestionEmpresa.model.Departamento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartamentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class DepartamentoMapper implements RowMapper<Departamento> {
        @Override
        public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            Departamento departamento = new Departamento();
            departamento.setId(rs.getInt("IDDpto"));
            departamento.setNombre(rs.getString("Nombre"));
            departamento.setTelefono(rs.getString("Telefono"));
            departamento.setFax(rs.getString("Fax"));
            return departamento;
        }
    }

    public List<Departamento> findAll() {
        String sql = "SELECT * FROM Departamentos";
        return jdbcTemplate.query(sql, new DepartamentoMapper());
    }

    public Departamento findById(int id) {
        String sql = "SELECT * FROM Departamentos WHERE IDDpto = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new DepartamentoMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Departamento no encontrado con id " + id);
        }
    }

    public Departamento save(Departamento departamento) {
        String sql = "INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES (?, ?, ?) RETURNING IDDpto";
        try {
            int lid = jdbcTemplate.queryForObject(sql, Integer.class, departamento.getNombre(), departamento.getTelefono(), departamento.getFax());
            return findById(lid);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al guardar el departamento", e);
        }
    }

    public Departamento update(Departamento departamento) {
        String sql = "UPDATE Departamentos SET Nombre = ?, Telefono = ?, Fax = ? WHERE IDDpto = ?";
        try {
            int lid = jdbcTemplate.update(sql, departamento.getNombre(), departamento.getTelefono(), departamento.getFax(), departamento.getId());
            if (lid > 0) {
                return findById(departamento.getId());
            } else {
                throw new ResourceNotFoundException("Departamento no encontrado con id " + departamento.getId());
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al actualizar el departamento", e);
        }
    }

    public Departamento deleteById(int id) {
        Departamento departamento = findById(id);
        String sql = "DELETE FROM Departamentos WHERE IDDpto = ?";
        try {
            int lid = jdbcTemplate.update(sql, id);
            if (lid > 0) {
                return departamento;
            } else {
                throw new ResourceNotFoundException("Departamento no encontrado con id " + id);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Error al eliminar el departamento", e);
        }
    }
}