/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitles;

import java.util.List;
import net.sf.jtmdb.CastInfo;
import net.sf.jtmdb.GeneralSettings;
import net.sf.jtmdb.Movie;

/**
 *
 * @author tharindu_DG
 */
public class ImdbDataHandler {

    // function will return movie information from IMDB for a given movie name
    public String getMovieInfo(String movieName) {
    // GeneralSettings.setApiKey("1d51cef38e888586924becd5f1cc9b1d");
        GeneralSettings.setApiKey("6b3d83eddb36e261f96fbcd5752d25ca"); // api key
        String info = null;
        movieName = movieName.trim();
        if (movieName != null && !movieName.equals("")) {
            try {
                List<Movie> movies = Movie.search(movieName); // getting the list of movies
                for (Movie movie : movies) {  // iterating through the list
                    info = movie.getName() + "\n" + movie.getOverview() + "\n" + "Cast: ";
                    Movie movieFull = Movie.getInfo(movie.getID());
                    for (CastInfo cast : movieFull.getCast()) {
                        info += "   " + cast.getName() + " as " + cast.getCharacterName() + "\n";
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            return "Couldn't find any information";
        }
        if (info == null) {
            info = "Couldn't find any information about " + movieName;
        }
        return info;
    }
}
