/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.List;
import DAODataMovie.dataMovieDAO;
import DAOImplement.dataMovieImplement;
import Model.*;
import View.mainView;
import javax.swing.JOptionPane;

/**
 *
 * @author L E N O V O
 */
public class dataMovieController {

    mainView frame;
    dataMovieImplement implDataMovie;
    List<dataMovie> listMovie;
    private String deleteValue;
    private Double tmpAlur;
    private Double tmpPenokohan;
    private Double tmpAkting;
    private Double tmpRateResult;
    private boolean insertError = false;
    private boolean updateError = false;
    private boolean deleteError = false;

    private boolean isFormValid() {
        if (frame.getJTextJudul().getText().isEmpty()
                || frame.getJTextAlur().getText().isEmpty()
                || frame.getJTextPenokohan().getText().isEmpty()
                || frame.getJTextAkting().getText().isEmpty()) {
            return false;
        }
        try {
            double alur = Double.parseDouble(frame.getJTextAlur().getText());
            double penokohan = Double.parseDouble(frame.getJTextPenokohan().getText());
            double akting = Double.parseDouble(frame.getJTextAkting().getText());
            return alur >= 0 && alur <= 5 && penokohan >= 0 && penokohan <= 5 && akting >= 0 && akting <= 5;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public dataMovieController(mainView frame) {
        this.frame = frame;
        implDataMovie = new dataMovieDAO();
        listMovie = implDataMovie.getAll();
    }

    public void isiTabel() {
        listMovie = implDataMovie.getAll();
        modelTabelDataMovie modelTabel = new modelTabelDataMovie(listMovie);
        frame.getTableDataMovie().setModel(modelTabel);
    }

    public void insert() {
        if (isFormValid()) {
            try {
                dataMovie movie = new dataMovie();
                movie.setJudul(frame.getJTextJudul().getText());
                movie.setAlur(Double.parseDouble(frame.getJTextAlur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getJTextPenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getJTextAkting().getText()));
                tmpAlur = Double.parseDouble(frame.getJTextAlur().getText());
                tmpPenokohan = Double.parseDouble(frame.getJTextPenokohan().getText());
                tmpAkting = Double.parseDouble(frame.getJTextAkting().getText());
                tmpRateResult = (tmpAlur + tmpPenokohan + tmpAkting) / 3;
                movie.setNilai(tmpRateResult);
                implDataMovie.insert(movie);
                insertError = false;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Terdapat input yang tidak valid!", "Warning", JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                insertError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon data diisi dengan benar! Nilai angka (0 - 5).", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            insertError = true;
        }
    }

    public void update() {
        if (isFormValid()) {
            try {
                dataMovie movie = new dataMovie();
                movie.setJudul(frame.getJTextJudul().getText());
                movie.setAlur(Double.parseDouble(frame.getJTextAlur().getText()));
                movie.setPenokohan(Double.parseDouble(frame.getJTextPenokohan().getText()));
                movie.setAkting(Double.parseDouble(frame.getJTextAkting().getText()));
                tmpAlur = Double.parseDouble(frame.getJTextAlur().getText());
                tmpPenokohan = Double.parseDouble(frame.getJTextPenokohan().getText());
                tmpAkting = Double.parseDouble(frame.getJTextAkting().getText());
                tmpRateResult = (tmpAlur + tmpPenokohan + tmpAkting) / 3;
                movie.setNilai(tmpRateResult);
                implDataMovie.update(movie);
                updateError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil diubah!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Terdapat input yang tidak valid!", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                updateError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon data diisi dengan benar! Nilai angka (0 - 5).", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            updateError = true;
        }
    }

    public void delete() {
        if (!frame.getJTextJudul().getText().isEmpty()) {
            try {
                deleteValue = frame.getJTextJudul().getText();
                implDataMovie.delete(deleteValue);
                deleteError = false;
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Terdapat input yang tidak valid!", "Warning",
                        JOptionPane.WARNING_MESSAGE);
                ex.printStackTrace();
                deleteError = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Mohon isi judul film yang akan dihapus!", "Warning",
                    JOptionPane.WARNING_MESSAGE);
            deleteError = true;
        }
    }

    public boolean isInsertError() {
        return insertError;
    }

    public boolean isUpdateError() {
        return updateError;
    }

    public boolean isDeleteError() {
        return deleteError;
    }
}
