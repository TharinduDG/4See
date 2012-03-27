/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import org.jdom.Element;

/**
 *
 * @author tharindu_DG
 */
public class SubtitleDownload {

    // function to download subtitles from the internet
    public void downloadSubtitle(List<Element> subtitlesList, String clickedName, File selected) {

        try {
            /*
             * Get a connection to the URL and start up
             * a buffered reader.
             */
            String URL = null;
            String destPath = getDestPath(selected);  // getting the destination path
            for (Iterator it = subtitlesList.iterator(); it.hasNext();) {  // traversing the DOM
                Element matches = (Element) it.next();
                String releaseName = matches.getChild("releasename").getText();
                String movieName = matches.getChild("movie").getText();
                if (releaseName.equals(clickedName)) {
                    URL = matches.getChild("download").getText(); // initialising the URL to download
                    break;
                }
            }

            URL url = null;
            URLConnection openConnection;
            try {  // setting up the connection
                url = new URL(URL);
                openConnection = url.openConnection();
            } catch (MalformedURLException malformedURLException) {
            } catch (NullPointerException nullPointerException) {
            }

            InputStream reader = url.openStream();
            /*
             * Setup a buffered file writer to write
             * out what we read from the website.
             */
            FileOutputStream writer = new FileOutputStream(destPath + clickedName + ".zip"); // saving destination of the downloaded file
            byte[] buffer = new byte[153600];
            int totalBytesRead = 0;
            int bytesRead = 0;

            while ((bytesRead = reader.read(buffer)) > 0) {
                writer.write(buffer, 0, bytesRead);
                buffer = new byte[153600];
                totalBytesRead += bytesRead;
            }
            writer.flush();
            writer.close();
            
            if (totalBytesRead == 0) { // checking download status
                JOptionPane.showMessageDialog(null, "Could not find the selected file.\n Try another.", "File Not Found", JOptionPane.ERROR_MESSAGE);
            }

            File savedFile = new File(destPath + clickedName + ".zip");
            if (!savedFile.canRead()) { // checking the readability of the file
                JOptionPane.showMessageDialog(null, "File Could Not be Saved.\nPlease Try Again!", "Could'nt Save", JOptionPane.ERROR_MESSAGE);
                return;
            }

            writer.close();
            reader.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
    }

    // function that returns the destination path to save the downloaded data
    private String getDestPath(File selected) {

        String seperator = new Character(File.separatorChar).toString();
        String path = selected.getAbsolutePath();
        int lastIndex = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == File.separatorChar) {
                lastIndex = i;
            }
        }
        return path.substring(0, lastIndex + 1);
    }
}
