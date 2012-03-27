/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitles;

import de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel;
import java.io.File;
import java.io.FileWriter;
import javax.swing.UIManager;

/**
 *
 * @author tharindu_DG
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

//        SubtitleGui subtitleFrame = new SubtitleGui("Titanic");
//        subtitleFrame.setVisible(true);

//        LyricsFrame lyricsFrame = new LyricsFrame("Breaking Benjamin", "Until The End");
//        lyricsFrame.setVisible(true);

//        ImdbFrame imdbFrame = new ImdbFrame("Titanic 1997");
//        imdbFrame.setVisible(true);

//        new AboutFrame().setVisible(true);

//        try {
//            UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        MainFrame mf = new MainFrame();
//        mf.setVisible(true);
//        String selectedFile = mf.getSelectedFile().getAbsolutePath();
        FileWriter tempFile = new FileWriter("temp");
        tempFile.write("temp path");
        tempFile.close();

    }
}
