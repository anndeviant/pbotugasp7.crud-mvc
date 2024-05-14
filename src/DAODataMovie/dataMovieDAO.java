/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAODataMovie;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Connection.Connector;
import DAOImplement.dataMovieImplement;
import Model.dataMovie;
import javax.swing.JOptionPane;

/**
 *
 * @author L E N O V O
 */
public class dataMovieDAO implements dataMovieImplement {

    Connection connection;

    final String select = "SELECT * FROM movie";
    final String insert = "INSERT INTO movie (judul, alur, penokohan, akting, nilai) VALUES (?, ?, ?, ?, ?);";
    final String update = "UPDATE movie SET alur = ?, penokohan = ?, akting = ?, nilai = ? WHERE judul = ?;";
    final String delete = "DELETE FROM movie WHERE judul = ?";

    public dataMovieDAO() {
        connection = Connector.connection();
    }

    @Override
    public void insert(dataMovie dbMovie) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(insert, statement.RETURN_GENERATED_KEYS);
            statement.setString(1, dbMovie.getJudul());
            statement.setDouble(2, dbMovie.getAlur());
            statement.setDouble(3, dbMovie.getPenokohan());
            statement.setDouble(4, dbMovie.getAkting());
            statement.setDouble(5, dbMovie.getNilai());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: Data dengan primary key tersebut sudah ada.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Data gagal ditambahkan: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(dataMovie dbMovie) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(update);
            statement.setDouble(1, dbMovie.getAlur());
            statement.setDouble(2, dbMovie.getPenokohan());
            statement.setDouble(3, dbMovie.getAkting());
            statement.setDouble(4, dbMovie.getNilai());
            statement.setString(5, dbMovie.getJudul());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(String judul) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(delete);
            statement.setString(1, judul);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<dataMovie> getAll() {
        List<dataMovie> dbMovie = null;
        try {
            dbMovie = new ArrayList<dataMovie>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while (resultSet.next()) {
                dataMovie movie = new dataMovie();
                movie.setJudul(resultSet.getString("judul"));
                movie.setAlur(resultSet.getDouble("alur"));
                movie.setPenokohan(resultSet.getDouble("penokohan"));
                movie.setAkting(resultSet.getDouble("akting"));
                movie.setNilai(resultSet.getDouble("nilai"));
                dbMovie.add(movie);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(dataMovieDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dbMovie;
    }
}
