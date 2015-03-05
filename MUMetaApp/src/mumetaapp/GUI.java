/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mumetaapp;

import interfaces.*;
import interfaces.events.*;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author sdmiller2015
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form MUMetaApp
     */
    public GUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        WorkingPaneLabel = new javax.swing.JLabel();
        FeedbackFlatererLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainWorkArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        FeedbackFlatererOutput = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MainMenuOpen = new javax.swing.JMenuItem();
        MainMenuClose = new javax.swing.JMenuItem();
        MainMenuCreate = new javax.swing.JMenuItem();
        MainMenuRename = new javax.swing.JMenuItem();
        MainMenuDelete = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        WorkingPaneLabel.setText("Working Pane");
        WorkingPaneLabel.setName(""); // NOI18N

        FeedbackFlatererLabel.setText("Feedback Flaterer");

        MainWorkArea.setColumns(20);
        MainWorkArea.setRows(10);
        jScrollPane1.setViewportView(MainWorkArea);

        FeedbackFlatererOutput.setColumns(20);
        FeedbackFlatererOutput.setRows(5);
        jScrollPane2.setViewportView(FeedbackFlatererOutput);

        jMenuBar1.setName("MainMenuBar"); // NOI18N

        jMenu1.setText("File");

        MainMenuOpen.setText("Open");
        MainMenuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(MainMenuOpen);

        MainMenuClose.setText("Close");
        jMenu1.add(MainMenuClose);

        MainMenuCreate.setText("New");
        jMenu1.add(MainMenuCreate);

        MainMenuRename.setText("Rename");
        MainMenuRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuRenameActionPerformed(evt);
            }
        });
        jMenu1.add(MainMenuRename);

        MainMenuDelete.setText("Delete");
        MainMenuDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MainMenuDeleteActionPerformed(evt);
            }
        });
        jMenu1.add(MainMenuDelete);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(241, 241, 241)
                .addComponent(WorkingPaneLabel)
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(FeedbackFlatererLabel)
                .addGap(214, 214, 214))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(WorkingPaneLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(FeedbackFlatererLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MainMenuRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuRenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMenuRenameActionPerformed

    private void MainMenuDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MainMenuDeleteActionPerformed

    private void MainMenuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MainMenuOpenActionPerformed
        // TODO add your handling code here:
        
//        JFileChooser fc = new JFileChooser();
//        String newline = "\n";
        
        Controller cr = new Controller();
        Event OE = new OpenEvent("User wishes to open a file", "open", null);
        
        
//        int returnVal = fc.showOpenDialog(GUI.this);
 
//            if (returnVal == JFileChooser.APPROVE_OPTION) {
//                File file = fc.getSelectedFile();
//                //This is where a real application would open the file.
//                FeedbackFlatererOutput.append("Opening: " + file.getName() +"." + newline);
//                FeedbackFlatererOutput.append("Loaction: " + file.getPath() +"." + newline);
//            } else {
//                FeedbackFlatererOutput.append("Open command cancelled by user." + newline);
//            }
//                FeedbackFlatererOutput.setCaretPosition(FeedbackFlatererOutput.getDocument().getLength());
        
        cr.manageEvent(OE);
        
    }//GEN-LAST:event_MainMenuOpenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FeedbackFlatererLabel;
    private javax.swing.JTextArea FeedbackFlatererOutput;
    private javax.swing.JMenuItem MainMenuClose;
    private javax.swing.JMenuItem MainMenuCreate;
    private javax.swing.JMenuItem MainMenuDelete;
    private javax.swing.JMenuItem MainMenuOpen;
    private javax.swing.JMenuItem MainMenuRename;
    private javax.swing.JTextArea MainWorkArea;
    private javax.swing.JLabel WorkingPaneLabel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
