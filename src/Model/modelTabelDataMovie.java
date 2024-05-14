/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class modelTabelDataMovie extends AbstractTableModel {

    List<dataMovie> listMovie;

    public modelTabelDataMovie(List<dataMovie> listMovie) {
        this.listMovie = listMovie;
    }

    @Override
    public int getRowCount() {
        return listMovie.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Judul";
            case 1:
                return "Alur";
            case 2:
                return "Penokohan";
            case 3:
                return "Akting";
            case 4:
                return "Rating";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int column) {
        switch (column) {
            case 0:
                return listMovie.get(row).getJudul();
            case 1:
                return listMovie.get(row).getAlur();
            case 2:
                return listMovie.get(row).getPenokohan();
            case 3:
                return listMovie.get(row).getAkting();
            case 4:
                return listMovie.get(row).getNilai();
            default:
                return null;
        }
    }

}
