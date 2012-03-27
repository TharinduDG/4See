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

/**
 *
 * @author tharindu_DG
 */
public class LyricsHandler {

    private SAXBuilder builder;
    private String url;
    private String artistNodeTxt;
    private String songNodeTxt;
    private String lyrics;
    private String lyricsWikiLink;

    // constructor
    LyricsHandler() {
        builder = new SAXBuilder(); // initialising the DOM buider object
    }

    // function to read data from the internet

    public void read(String artist, String song) {

        artist = artist.replace(" ", "_"); // setting the artist name
        song = song.replace(" ", "_");  // setting the song name
        // creating the URL to download lyrics
        url = "http://lyrics.wikia.com/api.php?func=getSong&artist=" + artist + "&song=" + song + "&fmt=xml";

        List<Element> subtitle = null;
        try {
            // extracting elements from the DOM structure
            Document d = builder.build(url);
            Element element = d.getRootElement();
            Element artistNode = element.getChild("artist");
            artistNodeTxt = artistNode.getText();
            songNodeTxt = element.getChild("song").getText();
            lyrics = element.getChild("lyrics").getText();
            lyricsWikiLink = element.getChild("url").getText();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Could Not Connect to Server\nPlease Check Your Connection.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

    // function returns the full link for the appropriate lyrics file
    public String getLyricsWikiLink() {
        if (lyricsWikiLink == null) {
            return "Couldn't Find Lyrics";
        }
        return lyricsWikiLink;
    }

    // function to get the name of the artist
    public String getArtistNodeText() {
        return artistNodeTxt;
    }

    // function to get the name of the song
    public String getSongNodeText() {
        return songNodeTxt;
    }

    // function to get the lyrics
    public String getLyrics() {
        if (lyrics == null) {
            return "Could Not Find Lyrics!";
        } else {
            return lyrics;
        }
    }
}
