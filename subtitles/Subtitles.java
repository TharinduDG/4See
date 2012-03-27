/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitles;

import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Subtitles {

    private SAXBuilder builder = new SAXBuilder(); // Extracting object for the DOM
    private String url;

    public List<Element> read(String movieName) {

        if (movieName.endsWith("_MOVIE_HASH")) {  // selecting the appropriate URL
            // URL to search from the movie hash
            url = "http://api.opensubtitles.org/en/search/moviehash-" + movieName.substring(0, movieName.indexOf("_MOVIE_HASH")) + "/simplexml";
        } else {
            // URL to search from the movie name
            url = "http://api.opensubtitles.org/en/search/moviename-" + movieName + "/simplexml";
        }

        List<Element> subtitle = null;
        try {
            // Extrating the DOM from the returned XML
            Document d = builder.build(url);
            Element element = d.getRootElement();
            Element results = element.getChild("results");
            subtitle = results.getChildren("subtitle"); // getting all the child elements with the tag of "subtitle"
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could Not Connect to Server\nPlease Check Your Connection.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        return subtitle;
    }
}
