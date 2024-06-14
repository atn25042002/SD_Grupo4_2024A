package com.grupoc4.gestionEmpresa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

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
        return jdbcTemplate.queryForObject(sql, new DepartamentoMapper(), id);
    }

    public int save(Departamento departamento) {
        String sql = "INSERT INTO Departamentos (Nombre, Telefono, Fax) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, departamento.getNombre(), departamento.getTelefono(), departamento.getFax());
    }

    public int update(Departamento departamento) {
        String sql = "UPDATE Departamentos SET Nombre = ?, Telefono = ?, Fax = ? WHERE IDDpto = ?";
        return jdbcTemplate.update(sql, departamento.getNombre(), departamento.getTelefono(), departamento.getFax(), departamento.getId());
    }

    public int deleteById(int id) {
        String sql = "DELETE FROM Departamentos WHERE IDDpto = ?";
        return jdbcTemplate.update(sql, id);
    }
}