/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImplement;

import java.util.List;
import Model.*;

public interface dataMovieImplement {
    public void insert(dataMovie dbMovie);
    public void update(dataMovie dbMovie);
    public void delete(String judul);
    public List<dataMovie> getAll();
}
