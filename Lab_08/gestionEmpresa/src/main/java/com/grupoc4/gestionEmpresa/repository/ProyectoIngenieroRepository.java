package com.grupoc4.gestionEmpresa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import com.grupoc4.gestionEmpresa.model.ProyectoIngeniero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProyectoIngenieroRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class ProyectoIngenieroMapper implements RowMapper<ProyectoIngeniero> {
        public ProyectoIngeniero mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProyectoIngeniero proyectoIngeniero = new ProyectoIngeniero();
            proyectoIngeniero.setIdProy(rs.getInt("IDProy"));
            proyectoIngeniero.setIdIng(rs.getInt("IDIng"));
            return proyectoIngeniero;
        }
    }

    public List<ProyectoIngeniero> findByIdProy(Integer idProy) {
        String sql = "SELECT * FROM Proyecto_Ingeniero WHERE IDProy = ?";
        return jdbcTemplate.query(sql, new ProyectoIngenieroMapper(), idProy);
    }

    public List<ProyectoIngeniero> findByIdIng(Integer idIng) {
        String sql = "SELECT * FROM Proyecto_Ingeniero WHERE IDIng = ?";
        return jdbcTemplate.query(sql, new ProyectoIngenieroMapper(), idIng);
    }

    public void addIngenieroToProyecto(Integer idProy, Integer idIng) {
        String sql = "INSERT INTO Proyecto_Ingeniero (IDProy, IDIng) VALUES (?, ?)";
        jdbcTemplate.update(sql, idProy, idIng);
    }

    public void removeIngenieroFromProyecto(Integer idProy, Integer idIng) {
        String sql = "DELETE FROM Proyecto_Ingeniero WHERE IDProy = ? AND IDIng = ?";
        jdbcTemplate.update(sql, idProy, idIng);
    }
}