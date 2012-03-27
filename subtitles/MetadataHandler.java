/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitles;

import java.io.File;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;

/**
 *
 * @author tharindu_DG
 */
public class MetadataHandler {

    private MP3File mp3File;   // keep track of the current file

    // constructor
    public MetadataHandler(File mp3) {
        try {
            mp3File = new MP3File(mp3, MP3File.LOAD_ALL);
        } catch (Exception ex) {
        }
    }

    // function to get the name of the artist
    public String getArtist() {
        return mp3File.getTag().getFirst(FieldKey.ARTIST);
    }

    // function to get the name of the album
    public String getAlbum() {
        return mp3File.getTag().getFirst(FieldKey.ALBUM);
    }

    // function to get the title of the song
    public String getSongTitle() {
        return mp3File.getTag().getFirst(FieldKey.TITLE);
    }

    // function to get the release year of the song
    public String getYear() {
        return mp3File.getTag().getFirst(FieldKey.YEAR);
    }
}
