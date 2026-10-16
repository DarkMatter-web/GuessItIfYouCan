package com.mycompany.guessitifyoucan;

import javax.swing.*;
import java.awt.*;

/**
 * Central Launch Menu Panel Dashboard.
 * @author karlis
 */
public class MenuPanel extends javax.swing.JPanel {

    private MainGUI frame;

    /**
     * Creates new form MenuPanel
     */
        /**
     * Creates new form MenuPanel
     */
    public MenuPanel(MainGUI frame) {
        initComponents();
        this.frame = frame;
        
        // 3. Configure GridBagLayout constraints to place it right below the Multi-Player button
        java.awt.GridBagConstraints gbcExit = new java.awt.GridBagConstraints();
        gbcExit.gridx = 0;
        gbcExit.gridy = 3; // Places it neatly in Row 3 (Title is 0, Single is 1, Multi is 2)
        gbcExit.insets = new java.awt.Insets(10, 0, 10, 0); // Adds standard 10px vertical padding
        
        // 5. Refresh the UI graphics layer to paint it onto your screen instantly
        this.revalidate();
        this.repaint();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(400, 450));
        setLayout(new java.awt.GridBagLayout());

        jButton1.setText("SINGLE PLAYER");
        jButton1.addActionListener(this::jButton1ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(93, 61, 162, 0);
        add(jButton1, gridBagConstraints);

        jButton2.setText("MULTI PLAYER");
        jButton2.addActionListener(this::jButton2ActionPerformed);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(93, 54, 162, 66);
        add(jButton2, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("GUESS IT IF YOU CAN!");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(147, 103, 0, 0);
        add(jLabel1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (frame != null) {
            frame.showPanel("cardDifficulty"); // Flips view to your difficulty menu choices!
        }
    }
                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        if (frame != null) {
            frame.showPanel("cardWordEntry");
        }
    }
    
}
