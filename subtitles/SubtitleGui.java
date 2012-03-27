
/*
 * SubtitleGui.java
 *
 * Created on Dec 31, 2011, 6:59:56 PM
 */
package subtitles;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdom.Element;

/**
 *
 * @author sukhpalsingh
 */
public class SubtitleGui extends javax.swing.JFrame {

    /** Creates new form SubtitleGui */
    public SubtitleGui(String movie, File selected) {
        initComponents();
        this.selected = selected;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        subDownButton.setVisible(false);  // setting up manual serach UI
        selectedSubTxt.setVisible(false);
        jSeparator3.setVisible(false);
        downloader = new SubtitleDownload();
        subReader = new Subtitles();
        insertSubtitleData(subReader.read(movie));
        movieTF.setText(movieNodeText);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        movieTF = new javax.swing.JTextField();
        movieL = new javax.swing.JLabel();
        searchB = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        subtitleTable = new javax.swing.JTable();
        selectedSubTxt = new javax.swing.JTextField();
        subDownButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Subtitle Search");
        setBackground(new java.awt.Color(121, 158, 166));
        setResizable(false);

        movieTF.setFont(new java.awt.Font("Tahoma", 1, 12));
        movieTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieTFActionPerformed(evt);
            }
        });

        movieL.setForeground(new java.awt.Color(0, 51, 102));
        movieL.setText("Movie Name");

        searchB.setText("Search");
        searchB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBActionPerformed(evt);
            }
        });

        subtitleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Movie", "Release Name", "Rating", "Language"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        subtitleTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(subtitleTable);
        subtitleTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        selectedSubTxt.setEditable(false);
        selectedSubTxt.setFont(new java.awt.Font("Tahoma", 1, 12));
        selectedSubTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectedSubTxtActionPerformed(evt);
            }
        });

        subDownButton.setText("Download");
        subDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subDownButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(27, 27, 27)
                .add(movieL)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 13, Short.MAX_VALUE)
                .add(movieTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 292, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(searchB)
                .add(32, 32, 32))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(selectedSubTxt, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(subDownButton)))
                .addContainerGap())
            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
            .add(jSeparator3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(searchB)
                    .add(movieTF, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(movieL))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 320, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(selectedSubTxt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(subDownButton))
                .add(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void movieTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieTFActionPerformed
    }//GEN-LAST:event_movieTFActionPerformed

    private void selectedSubTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectedSubTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selectedSubTxtActionPerformed

    private void subDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subDownButtonActionPerformed
    }//GEN-LAST:event_subDownButtonActionPerformed

    // function called when the search button is pressed
    private void searchBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBActionPerformed

        subDownButton.setVisible(false); // setting up the UI
        selectedSubTxt.setVisible(false);
        selectedSubTxt.setText(null);
        if (movieTF.getText() == null || movieTF.getText().trim().equals("")) { // getting the input text by the user
            JOptionPane.showMessageDialog(null, "Name a movie name to search!", "No name found", JOptionPane.ERROR_MESSAGE);
        } else {
            String customSearch = movieTF.getText().trim();
            List<Element> results = subReader.read(customSearch);
            insertSubtitleData(results);
            movieTF.setText(movieNodeText);
        }
    }//GEN-LAST:event_searchBActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel movieL;
    private javax.swing.JTextField movieTF;
    private javax.swing.JButton searchB;
    private javax.swing.JTextField selectedSubTxt;
    private javax.swing.JButton subDownButton;
    private javax.swing.JTable subtitleTable;
    // End of variables declaration//GEN-END:variables
    private SubtitleDownload downloader;
    private Subtitles subReader;
    private String movieNodeText;
    private File selected;

    // function called when the subtitle data need to be populated to a table
    private void insertSubtitleData(final List<Element> subtitle) {

        if (subtitle == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) subtitleTable.getModel();
        int index = 0;

        for (Iterator it = subtitle.iterator(); it.hasNext();) {  // inserting data to the table
            Element matches = (Element) it.next();
            movieNodeText = matches.getChild("movie").getText();
            String releaseName = matches.getChild("releasename").getText();
            String rating = matches.getChild("subrating").getText();
            String language = matches.getChild("language").getText();
            model.insertRow(index, new String[]{movieNodeText, releaseName, rating, language});
            index++;
        }

        subDownButton.addActionListener(new ActionListener() { // setting up action listener for the download button

            public void actionPerformed(ActionEvent e) {
                downloader.downloadSubtitle(subtitle, selectedSubTxt.getText(), selected);
                JOptionPane.showMessageDialog(null, "Download Completed");
            }
        });

        subtitleTable.addMouseListener(new MouseAdapter() { // setting up mouse listener for selecting a table row

            @Override
            public void mouseClicked(MouseEvent e) {
                int row = subtitleTable.rowAtPoint(e.getPoint());
                String clickedName = (String) subtitleTable.getValueAt(row, 1);
                selectedSubTxt.setText(clickedName);
                jSeparator3.setVisible(true);
                selectedSubTxt.setVisible(true);
                subDownButton.setVisible(true);
            }
        });
    }

    // function to get the name of the movie
    public String getMovieName() {
        return movieNodeText;
    }
}
