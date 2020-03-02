/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.Destino;
import persistencia.ConnectionFactory;

/**
 *
 * @author Invitado
 */
public class DestinoDAOImpl implements DestinoDAO {

    private final ConnectionFactory connectionFactory;

    public DestinoDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Destino find(int id) throws Exception {
        final String SQL = "SELECT nombre FROM destino WHERE id = ?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Destino(id,
                            resultSet.getString("nombre"));
                }
                return null;
            }
        }
    }

    @Override
    public List<Destino> getAll() throws Exception {
        List<Destino> lstDestinos = new ArrayList<>();
        final String SQL = "SELECT id, nombre FROM destino";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lstDestinos.add(new Destino(resultSet.getInt("id"),
                            resultSet.getString("nombre")));
                }
                return lstDestinos;
            }
        }
    }

    @Override
    public List<Destino> getAllWith(String referencia) throws Exception {
        List<Destino> lstDestinos = new ArrayList<>();
        final String SQL = "SELECT id, nombre FROM destino WHERE nombre LIKE ?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1, "%" + referencia + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lstDestinos.add(new Destino(resultSet.getInt("id"),
                            resultSet.getString("nombre")));
                }
                return lstDestinos;
            }
        }
    }

    @Override
    public void add(Destino destino) throws Exception {
        final String SQL = "INSERT INTO destino(nombre) VALUES(?)";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, destino.getNombre());

            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    destino.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Destino destino) throws Exception {
        final String SQL = "UPDATE destino SET nombre=? WHERE id=?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            statement.setString(1, destino.getNombre());
            statement.setInt(2, destino.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        final String SQL = "DELETE FROM destino WHERE id=?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}
