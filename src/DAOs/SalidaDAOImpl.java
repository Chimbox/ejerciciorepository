/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import objetosServicio.Fecha;
import java.util.List;
import negocio.Salida;
import persistencia.ConnectionFactory;
import repositories.BarcoRepository;

/**
 *
 * @author Invitado
 */
public class SalidaDAOImpl implements SalidaDAO {

    private final ConnectionFactory connectionFactory;
    private final DestinoDAO destinos;
    private final BarcoRepository barcos;

    public SalidaDAOImpl(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        destinos = new DestinoDAOImpl(connectionFactory);
        barcos = new BarcoRepository(connectionFactory);
    }

    @Override
    public Salida find(int id) throws Exception {
        final String SQL = "SELECT fecha_hora, barco_num_matricula, destino_id FROM salida WHERE id = ?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String strFecha = resultSet.getString("fecha_hora");
                    String strHora = strFecha.split(" ")[1];
                    Fecha fecha = new Fecha(strFecha.split(" ")[0]);
                    fecha.setHora(Integer.parseInt(strHora.split(":")[0]));
                    fecha.setMinuto(Integer.parseInt(strHora.split(":")[1]));
                    return new Salida(id, fecha,
                            barcos.find(resultSet.getInt("barco_num_matricula")),
                            destinos.find(resultSet.getInt("destino_id")));
                }
                return null;
            }
        }
    }

    @Override
    public List<Salida> getAll() throws Exception {
        List<Salida> lstSalidas = new ArrayList<>();

        final String SQL = "SELECT id, fecha_hora, barco_num_matricula, destino_id FROM salida";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String strFecha = resultSet.getString("fecha_hora");
                    String strHora = strFecha.split(" ")[1];

                    Fecha fecha = new Fecha(strFecha.split(" ")[0]);
                    fecha.setHora(Integer.parseInt(strHora.split(":")[0]));
                    fecha.setMinuto(Integer.parseInt(strHora.split(":")[1]));

                    lstSalidas.add(new Salida(resultSet.getInt("id"),
                            fecha,
                            barcos.find(resultSet.getInt("barco_num_matricula")),
                            destinos.find(resultSet.getInt("destino_id"))));
                }
                return lstSalidas;
            }
        }
    }

    public List<Salida> getAllWith(String referencia) throws Exception {
        List<Salida> lstSalidas = new ArrayList<>();

        final String SQL = "SELECT s.id, s.fecha_hora, s.barco_num_matricula, "
                + "s.destino_id FROM salida s INNER JOIN barco b "
                + "ON b.num_matricula=s.barco_num_matricula INNER JOIN destino d "
                + "ON d.id=s.destino_id WHERE b.nombre LIKE ? OR d.nombre LIKE ?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {
            statement.setString(1, "%" + referencia + "%");
            statement.setString(2, "%" + referencia + "%");

            System.out.println(SQL);
            try (ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    String strFecha = resultSet.getString("fecha_hora");
                    String strHora = strFecha.split(" ")[1];

                    Fecha fecha = new Fecha(strFecha.split(" ")[0]);
                    fecha.setHora(Integer.parseInt(strHora.split(":")[0]));
                    fecha.setMinuto(Integer.parseInt(strHora.split(":")[1]));

                    lstSalidas.add(new Salida(resultSet.getInt("id"),
                            fecha,
                            barcos.find(resultSet.getInt("barco_num_matricula")),
                            destinos.find(resultSet.getInt("destino_id"))));
                }
                return lstSalidas;
            }
        }
    }

    @Override
    public void add(Salida salida) throws Exception {
        final String SQL = "INSERT INTO salida(fecha_hora, barco_num_matricula, destino_id) VALUES(?, ?, ?)";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, salida.getFechaHora() + ":" + salida.getFechaHora().getCadenaHora());
            statement.setInt(2, salida.getBarco().getNumMatricula());
            statement.setInt(3, salida.getDestino().getId());

            statement.executeUpdate();
            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    salida.setId(rs.getInt(1));
                }
            }
        }
    }

    @Override
    public void update(Salida salida) throws Exception {
        final String SQL = "UPDATE salida SET fecha_hora=?, barco_num_matricula=?, destino_id=? WHERE id=?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            statement.setString(1, salida.getFechaHora() + ":" + salida.getFechaHora().getCadenaHora());
            statement.setInt(2, salida.getBarco().getNumMatricula());
            statement.setInt(3, salida.getDestino().getId());
            statement.setInt(4, salida.getId());

            statement.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        final String SQL = "DELETE FROM salida WHERE id=?";
        try (Connection connection = this.connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(SQL);) {

            statement.setInt(1, id);

            statement.executeUpdate();
        }
    }
}
