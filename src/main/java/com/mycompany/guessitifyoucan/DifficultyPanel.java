package com.mycompany.guessitifyoucan;

import javax.swing.*;
import java.awt.*;

/**
 * Centered Difficulty Selection Screen.
 * @author elash
 */
public class DifficultyPanel extends javax.swing.JPanel {

    private MainGUI frame;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton btnEasy;
    private javax.swing.JButton btnHard;

    /**
     * Creates new form DifficultyPanel
     */
    public DifficultyPanel(MainGUI frame) {
        this.frame = frame;
        
        // Initialize our UI elements
        titleLabel = new javax.swing.JLabel("SELECT DIFFICULTY");
        btnEasy = new javax.swing.JButton("EASY");
        btnHard = new javax.swing.JButton("HARD");
        
        // 1. Force the container to use a centered GridBagLayout stack
        this.setLayout(new java.awt.GridBagLayout());
        
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.insets = new java.awt.Insets(10, 0, 30, 0); // Spacing below title
        gbc.anchor = java.awt.GridBagConstraints.CENTER;
        
        // --- 1. Title Customization ---
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 26));
        this.add(titleLabel, gbc);
        
        // --- 2. EASY Button Customization ---
        gbc.gridy = 1;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);
        btnEasy.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        btnEasy.setPreferredSize(new java.awt.Dimension(180, 45));
        btnEasy.addActionListener(evt -> {
            if (this.frame != null) this.frame.startSinglePlayer(1); // Difficulty 1 = Easy
        });
        this.add(btnEasy, gbc);
        
        // --- 3. HARD Button Customization ---
        gbc.gridy = 2;
        gbc.insets = new java.awt.Insets(10, 0, 10, 0);
        btnHard.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 18));
        btnHard.setPreferredSize(new java.awt.Dimension(180, 45));
        btnHard.addActionListener(evt -> {
            if (this.frame != null) this.frame.startSinglePlayer(2); // Difficulty 2 = Hard
        });
        this.add(btnHard, gbc);
        
        // Refresh the graphics layer
        this.revalidate();
        this.repaint();
    }
}
