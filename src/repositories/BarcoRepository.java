/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import negocio.Barco;
import persistencia.ConnectionFactory;

/**
 *
 * @author Invitado
 */
public class BarcoRepository extends BaseRepository<Barco> {

    private final SocioRepository socios;

    public BarcoRepository(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        socios = new SocioRepository(this.getConnectionFactory()) {};
    }

    @Override
    public Barco find(int numMatricula) throws Exception {
        final String SQL = "SELECT nombre, num_amarre, cuota, socio_id "
                + "FROM barco WHERE num_matricula = ?";
        try (Connection connection = this.getConnectionFactory().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            statement.setInt(1, numMatricula);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Barco(numMatricula,
                            resultSet.getString("nombre"),
                            resultSet.getShort("num_amarre"),
                            resultSet.getDouble("cuota"),
                            socios.find(resultSet.getInt("socio_id")));
                }
                return null;
            }
        }
    }

    @Override
    public List<Barco> getAll() throws Exception {
        List<Barco> lstBarcos = new ArrayList<>();
        final String SQL = "SELECT num_matricula, nombre, num_amarre, cuota, socio_id "
                + "FROM barco";
        try (Connection connection = this.getConnectionFactory().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lstBarcos.add(new Barco(resultSet.getInt("num_matricula"),
                            resultSet.getString("nombre"),
                            resultSet.getShort("num_amarre"),
                            resultSet.getDouble("cuota"),
                            socios.find(resultSet.getInt("socio_id"))));
                }
                return lstBarcos;
            }
        }
    }

    public List<Barco> getAllWith(String referencia) throws Exception {
        List<Barco> lstBarcos = new ArrayList<>();
        final String SQL = "SELECT num_matricula, nombre, num_amarre, cuota, socio_id "
                + "FROM barco WHERE nombre LIKE ?";
        try (Connection connection = this.getConnectionFactory().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1, "%" + referencia + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    lstBarcos.add(new Barco(resultSet.getInt("num_matricula"),
                            resultSet.getString("nombre"),
                            resultSet.getShort("num_amarre"),
                            resultSet.getDouble("cuota"),
                            socios.find(resultSet.getInt("socio_id"))));
                }
                return lstBarcos;
            }
        }
    }

    @Override
    public void add(Barco barco) throws Exception {
        final String SQL = "INSERT INTO barco(num_matricula, nombre, num_amarre, cuota, socio_id) VALUES(?, ?, ?, ?, ?)";
        try (Connection connection = this.getConnectionFactory().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            System.out.println(barco);

            statement.setInt(1, barco.getNumMatricula());
            statement.setString(2, barco.getNombre());
            statement.setShort(3, barco.getNumAmarre());
            statement.setDouble(4, barco.getCuota());
            statement.setInt(5, barco.getSocio().getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void update(Barco barco) throws Exception {
        final String SQL = "UPDATE barco SET nombre=?, num_amarre=?, cuota=?, socio_id=? WHERE num_matricula=?";
        try (Connection connection = this.getConnectionFactory().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1, barco.getNombre());
            statement.setShort(2, barco.getNumAmarre());
            statement.setDouble(3, barco.getCuota());
            statement.setInt(4, barco.getSocio().getId());
            statement.setInt(5, barco.getNumMatricula());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int num_matricula) throws Exception {
        final String SQL = "DELETE FROM barco WHERE num_matricula=?";
        try (Connection connection = this.getConnectionFactory().getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setInt(1, num_matricula);

            statement.executeUpdate();
        }
    }
}
