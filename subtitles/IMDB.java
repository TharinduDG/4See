/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package subtitles;

import java.io.*;
import java.net.*;
import javax.swing.*;
//import mediaManager.database.*;

class IMDB {

    /**
     * The IMDB key for the movie.
     **/
    private String key = "";
    /**
     * The date of the movie
     **/
    private String date = "";
    /**
     * The date of the movie.
     **/
    private String title = "";
    /**
     * Directed by.
     **/
    private String directedBy = "";
    /**
     * Written by.
     **/
    private String writtenBy = "";
    /**
     * Genre.
     **/
    private String genre = "";
    private String country = "";
    private String mpaa = "";
    /**
     * The rating.
     **/
    private String rating = "";
    /**
     * The plot.
     **/
    private String plot = "";
    /**
     * The cast.
     **/
    private String cast = "";
    /**
     * The cover URL
     **/
    private String coverURL = "";
    /**
     * Reading OK
     **/
    private boolean coverOK = false;
    //private Database database;

    /**
     * The constructor initializes all variables with the data read from the Internet for
     * the movie with key.
     **/
    protected IMDB(String key) {


        try {
            /* Creates the url... */
            URL url = new URL("http://www.imdb.com/Title?" + key);

            /* Gets the input stream from the connection... */
            InputStream stream = url.openStream();

            /* Saves the page data in a string buffer... */
            StringBuffer data = new StringBuffer();
            int buffer;

            /* reading data from the url */
            while ((buffer = stream.read()) != -1) {
                data.append((char) buffer);
            }
            stream.close();
            int start = 0;
            int stop = 0;
            int end = 0;

            /* Processes the data... */
            this.key = key;

            /* Gets the title... */
            if ((start = data.indexOf("class=\"title\">", start) + 14) != 13
                    && (end = data.indexOf(" <small>", start)) != -1) {
                title = decodeHTML(data.substring(start, end));
            }

            /* Gets the date... */
            if ((start = data.indexOf("\">", start) + 2) != 1
                    && (end = data.indexOf("</", start)) != -1) {
                date = decodeHTML(data.substring(start, end));
            }

            /* Gets the cover url... */
            if ((start = data.indexOf("\"cover\"", start) + 7) != 6
                    && (start = data.indexOf("src=\"", start) + 5) != 4
                    && (end = data.indexOf("\"", start)) != -1) {
                coverURL = decodeHTML(data.substring(start, end));
            }
            start = 0;
            stop = 0;
            end = 0;

            /* Gets the directed by... */
            if ((start = data.indexOf("Directed by", start) + 11) != 10
                    && (stop = data.indexOf("<br>\n<br>", start)) != -1) {
                while (true) {
                    if ((start = data.indexOf("\">", start) + 2) != 1
                            && (end = data.indexOf("</", start)) != -1) {
                        if (end > stop) {
                            break;
                        }
                        if (!directedBy.equals("")) {
                            directedBy = directedBy + ", ";
                        }
                        directedBy = directedBy + decodeHTML(data.substring(start, end));
                    }
                }
            }
            start = 0;
            stop = 0;
            end = 0;

            /* Gets the written by... */
            if ((start = Math.max(data.indexOf("Writing credits", start) + 15, data.indexOf("WGA", start) + 15)) != 14
                    && (stop = Math.min(data.indexOf("(more)", start), Math.abs(data.indexOf("</table>", start)))) != -1) {
                while (true) {
                    if ((start = data.indexOf("\">", start) + 2) != 1
                            && (end = data.indexOf("</", start)) != -1) {
                        if (end > stop) {
                            break;
                        }
                        if (!writtenBy.equals("")) {
                            writtenBy = writtenBy + ", ";
                        }
                        writtenBy = writtenBy + decodeHTML(data.substring(start, end));
                    }
                }
            }
            start = 0;
            stop = 0;
            end = 0;

            /* Gets the Genre... */
            if ((start = data.indexOf("Genre:", start) + 6) != 5
                    && (stop = Math.min(data.indexOf("(more)", start), Math.abs(data.indexOf("<br><br>", start)))) != -1) {
                while (true) {
                    if ((start = data.indexOf("\">", start) + 2) != 1
                            && (end = data.indexOf("</", start)) != -1) {
                        if (end > stop) {
                            break;
                        }
                        if (!genre.equals("")) {
                            genre = genre + " / ";
                        }
                        genre = genre + decodeHTML(data.substring(start, end));
                    }
                }
            }
            start = 0;
            stop = 0;
            end = 0;

            /* Gets the rating... */
            if ((start = data.indexOf("User Rating:", start) + 12) != 11
                    && (end = data.indexOf("/10", start)) != -1
                    && (start = data.indexOf("<b>", end - 9) + 3) != 2) {
                rating = decodeHTML(data.substring(start, end));
            }
            start = 0;
            stop = 0;
            end = 0;

            /* Gets the plot... */
            if ((start = Math.max(data.indexOf("Plot Outline:</b>", start), data.indexOf("Plot Summary:</b>", start)) + 18) != 17
                    && (end = data.indexOf("<br>", start) + 1) != 0) {
                plot = decodeHTML(data.substring(start, end));
            }

            /* Gets the cast... */
            if ((start = Math.max(data.indexOf(">Cast ", start) + 6, data.indexOf("cast:", start) + 5)) != 4
                    && (stop = Math.min(data.indexOf("(more)", start), Math.abs(data.indexOf("</table>", start)))) != -1) {
                while (true) {
                    if ((start = data.indexOf("<a href=", start)) == -1) {
                        break;
                    }
                    if ((start = data.indexOf("\">", start) + 2) == 1) {
                        break;
                    }
                    if ((end = data.indexOf("</", start)) != -1) {
                        if (end > stop) {
                            break;
                        }
                        if (!cast.equals("")) {
                            cast = cast + ", ";
                        }
                        cast = cast + decodeHTML(data.substring(start, end));
                    }
                }
            }

            start = 0;
            stop = 0;
            end = 0;

            /* Gets the MPAA... */
            //<b class="ch"><a href="/mpaa">MPAA</a>:</b> Rated PG-13 for sexuality and some thematic elements.<br>
            //  / <a href="/List?certificates=USA:R&&heading=14;USA:R">USA:R</a>
            if ((start = data.indexOf("certificates=USA", start) + 16) != 15
                    && (stop = Math.min(data.indexOf("&&", start), Math.abs(data.indexOf("<br>", start)))) != -1) {
                while (true) {
                    if ((start = data.indexOf(":", start) + 2) != 1
                            && (end = data.indexOf("&&", start)) != -1) {
                        if (end > stop) {
                            break;
                        }
                        mpaa = decodeHTML(data.substring(start - 1, end));
                        break; //failsafe
                    }
                }
            }


            start = 0;
            stop = 0;
            end = 0;

            /* Gets the Country... */
            //<b class="ch">Country:</b>
            //<a href="/Sections/Countries/USA/">USA</a> / <a href="/Sections/Countries/UK/">UK</a>
            //<br>
            if ((start = data.indexOf("Country:", start) + 8) != 7
                    && (stop = Math.min(data.indexOf("(more)", start), Math.abs(data.indexOf("<br>", start)))) != -1) {
                while (true) {
                    if ((start = data.indexOf("\">", start) + 2) != 1
                            && (end = data.indexOf("</", start)) != -1) {
                        if (end > stop) {
                            break;
                        }
                        if (!country.equals("")) {
                            country = country + " / ";
                        }
                        country = country + decodeHTML(data.substring(start, end));
                    }
                }
            }




            /* Gets a bigger plot (if it exists...)
            /* Creates the url... */
            url = new URL("http://www.imdb.com/Plot?" + key);

            /* Gets the input stream from the connection... */
            stream = url.openStream();

            /* Saves the page data in a string buffer... */
            data = new StringBuffer();
            while ((buffer = stream.read()) != -1) {
                data.append((char) buffer);
            }
            stream.close();

            /* Processes the data... */
            start = 0;
            end = 0;
            if ((start = data.indexOf("class=\"plotpar\">", start) + 16) != 15
                    && (end = data.indexOf("</p>", start)) != -1) {
                plot = decodeHTML(data.substring(start, end));
            }
        } catch (Exception e) {
            
        }
    }

    /**
     * Gets the key.
     **/
    protected String getKey() {
        return key;
    }

    /**
     * Gets the date.
     **/
    protected String getDate() {
        return date;
    }

    /**
     * Gets the title.
     **/
    protected String getTitle() {
        return title;
    }

    /**
     * Gets the directed by.
     **/
    protected String getDirectedBy() {
        return directedBy;
    }

    /**
     * Gets the written by.
     **/
    protected String getWrittenBy() {
        return writtenBy;
    }

    /**
     * Gets the genre.
     **/
    protected String getGenre() {
        return genre;
    }

    /**
     * Gets the genre.
     **/
    protected String getCountry() {
        return country;
    }

    /**
     * Gets the genre.
     **/
    protected String getMPAA() {
        return mpaa;
    }

    /**
     * Gets the rating.
     **/
    protected String getRating() {
        return rating;
    }

    /**
     * Gets the plot.
     **/
    protected String getPlot() {
        return plot;
    }

    /**
     * Gets the cast.
     **/
    protected String getCast() {
        return cast;
    }

    /**
     * Gets the cover URL
     **/
    protected String getCoverURL() {
        return coverURL;
    }

    /**
     * Gets the cover.
     **/
    protected byte[] getCover(String coversFolder) {
        byte[] data = {-1};
        //String coversFolder="";
        try {
            if (!coverURL.equals("")) {

                //coversFolder = database.getCoversFolder();

                /* Gets the covers folder... */
                if (!coversFolder.endsWith("\\")) {
                    coversFolder = coversFolder + "\\";
                }
                /* Creates the new file... */
                File tmpFile = new File(coversFolder + "tmpCover");
                if (tmpFile.exists()) {
                    if (!tmpFile.delete() && !tmpFile.createNewFile()) {
                        throw new Exception("Cannot delete temporary cover file and create a new one.");
                    }
                } else {
                    if (!tmpFile.createNewFile()) {
                        throw new Exception("Cannot create temporary cover file.");
                    }
                }
                /* Creates the output stream... */
                OutputStream outputStream = new FileOutputStream(tmpFile);

                /* Creates the url.... */
                URL url = new URL(coverURL);

                /* Gets the input stream from the connection... */
                InputStream inputStream = url.openStream();

                /* Makes the buffer and reads data... */
                int buffer;
                while ((buffer = inputStream.read()) != -1) {
                    outputStream.write(buffer);
                }
                inputStream.close();
                outputStream.close();

                /* Copies the cover to memory... */
                inputStream = new FileInputStream(tmpFile);
                data = new byte[inputStream.available()];
                inputStream.read(data);
                inputStream.close();

                /* Deletes the temporary file... */
                tmpFile.delete();
                coverOK = true;
            }
        } catch (Exception e) {
           
            coverOK = false;
        }

        /* Returns the data... */
        return data;

    }

    /**
     * Returns if the last cover reading went ok..
     **/
    protected boolean getCoverOK() {
        return coverOK;
    }

    /**
     * Returns simple matches list...
     **/
    protected static DefaultListModel getSimpleMatches(String title) {
        return getMatches("http://www.imdb.com/Find?", title);
    }

    /**
     * Returns extended matches list...
     **/
    protected static DefaultListModel getExtendedMatches(String title) {
        return getMatches("http://www.imdb.com/Tsearch?", title);
    }

    /**
     * Returns a DefaultListModel with ModelMovie's of the movies that IMDB
     * returned for the searched title.
     *
     * urlType = http://www.imdb.com/Find? or
     *           http://www.imdb.com/Tsearch?
     **/
    private static DefaultListModel getMatches(String urlType, String title) {
        DefaultListModel listModel = new DefaultListModel();
        
        try {
            /* Creates the url... */
            URL url = new URL(urlType + title.replaceAll("[\\p{Blank}]+", "+"));

            /* Gets the input stream from the connection... */
            InputStream stream = url.openStream();

            /* Saves the page data in a string buffer... */
            StringBuilder data = new StringBuilder();
            int buffer;
            while ((buffer = stream.read()) != -1) {
                data.append((char) buffer);
            }
            stream.close();
            int start = 0;
            int end = 0;
            String key = "";
            String movieTitle = "";

            /* Makes sure there isn't only one movie for that title... */
            if (!data.substring(data.indexOf("<title>") + 7, data.indexOf("<title>") + 11).equals("IMDb")) {
                /* Gets the title..., if only one */
                //System.out.println("Only one:"+data.substring(data.indexOf("<title>")+7,data.indexOf("<title>")+11));
                if ((start = data.indexOf("class=\"title\">", start) + 14) != 13
                        && (end = data.indexOf(" <small>", start)) != -1) {

                    movieTitle = decodeHTML(data.substring(start, end));
                }
                if (((start = data.indexOf("/Title?", start) + 7) != 6 && (end = data.indexOf("\">", start)) != -1)
                        || ((start = data.indexOf("title/tt", start) + 8) != 7 && (end = data.indexOf("/\"", start)) != -1)) {
                    key = decodeHTML(data.substring(start, end));
                }

                listModel.addElement(new ModelIMDB(key, movieTitle));
                return listModel;
            }
            /* Processes the data... */
            start = 0;
            end = 0;
            while (true) {
                /* Gets the titles..., if more than one */
                //System.out.println("More than one");
                /*
                if ((start=data.indexOf("/Title?",start)+7)==6) break;
                if ((end=data.indexOf("\">",start))==-1) break;
                key = decodeHTML(data.substring(start,end));
                start += key.length() + 2;
                if ((end=data.indexOf("</",start))==-1) break;
                 */
                if ((start = data.indexOf("title/tt", start) + 8) == 7) {
                    break;
                }
                if ((end = data.indexOf("/\"", start)) == -1) {
                    break;
                }
                key = decodeHTML(data.substring(start, end));
                start += key.length() + 2;
                if ((end = data.indexOf("</", start)) == -1) {
                    break;
                }

                //System.out.println("T:"+data.substring(start,end));

                movieTitle = decodeHTML(data.substring(start, end));
                //System.out.println("K:"+key+" T:"+movieTitle);
                /* Parses the title... */
                start += movieTitle.length() + 2;
                /* Adds to the list model... */
                boolean insert = true;
                for (int i = 0; i < listModel.size(); i++) {
                    if (((ModelIMDB) listModel.elementAt(i)).getKey().equals(key)) {
                        insert = false;
                        break;
                    }
                }
                if (insert) {
                    listModel.addElement(new ModelIMDB(key, movieTitle));
                }
            }
        } catch (Exception e) {
           
        } //finally {
        /* Returns the model... */
        return listModel;
        //}
    }

    /**
     * Decodes a html string and returns its unicode string.
     **/
    protected static String decodeHTML(String toDecode) {
        String decoded = "";
        try {
            int end = 0;
            for (int i = 0; i < toDecode.length(); i++) {
                if (toDecode.charAt(i) == '&' && toDecode.charAt(i + 1) == '#' && (end = toDecode.indexOf(";", i)) != -1) {
                    decoded += (char) Integer.parseInt(toDecode.substring(i + 2, end));
                    i = end;
                } else {
                    decoded += toDecode.charAt(i);
                }
            }
        } catch (Exception e) {
           
        } 
        /* Returns the decoded string... */
        return decoded;
       
    }
}
