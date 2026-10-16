package com.mycompany.guessitifyoucan;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Clean, programmatic Word Entry screen with 100% editable code.
 * @author elash
 */
public class WordEntryPanel extends javax.swing.JPanel {

    private String secretWord = "";
    private MainGUI frame;    
    private JLabel[] cells = new JLabel[5]; 
    private int currentCol = 0;             

    private javax.swing.JPanel gridPanel;
    private javax.swing.JPanel keyboardPanel;

    /**
     * Creates new form WordEntryPanel
     */
    public WordEntryPanel(MainGUI frame) {
        this.frame = frame;
        
        // 1. Manually initialize the panel objects securely
        gridPanel = new javax.swing.JPanel();
        keyboardPanel = new javax.swing.JPanel();
        
        // 2. Clear default layouts and apply a clean BorderLayout
        this.setLayout(new java.awt.BorderLayout());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        
        // 3. Mount layout panels directly into the active viewport
        this.add(gridPanel, java.awt.BorderLayout.CENTER);
        this.add(keyboardPanel, java.awt.BorderLayout.SOUTH);
        
        // 4. Force strict dimension boundaries to prevent sizing compression
        gridPanel.setPreferredSize(new java.awt.Dimension(450, 200));
        java.awt.Dimension kbdSize = new java.awt.Dimension(450, 300);
        keyboardPanel.setPreferredSize(kbdSize);
        keyboardPanel.setMinimumSize(kbdSize);
        
        // 5. Build our cell row and typing dashboard buttons
        createGrid();
        createKeyboard();
    }
    
    private void createGrid() {
        gridPanel.removeAll();
        gridPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));
    
        for (int col = 0; col < 5; col++) {
            JLabel label = new JLabel("", SwingConstants.CENTER);
            java.awt.Dimension originalBoxSize = new java.awt.Dimension(45, 45);
            label.setPreferredSize(originalBoxSize);
            label.setMinimumSize(originalBoxSize);
            label.setMaximumSize(originalBoxSize);

            label.setOpaque(true);
            label.setBackground(Color.WHITE);
            label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            label.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 18)); 
    
            cells[col] = label;
            gridPanel.add(label);
        }
    
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    private void createKeyboard() {
        keyboardPanel.removeAll();
        keyboardPanel.setLayout(new GridLayout(4, 1, 5, 5));

        String[] rows = {
            "QWERTYUIOP",
            "ASDFGHJKL",
            "ZXCVBNM"
        };
    
        for (String row : rows) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 0));
    
            for (char letter : row.toCharArray()) {    
                JButton button = new JButton(String.valueOf(letter));
                button.setPreferredSize(new Dimension(30, 40));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                button.setOpaque(true);
                
                button.addActionListener(e -> addLetter(String.valueOf(letter)));
                rowPanel.add(button);
            }
            keyboardPanel.add(rowPanel);
        }

        // Control utility button layout row
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton backButton = new JButton("BACK");
        JButton deleteButton = new JButton("DELETE");
        JButton continueButton = new JButton("CONTINUE");

        Dimension btnSize = new Dimension(100, 40);
        backButton.setPreferredSize(btnSize);
        deleteButton.setPreferredSize(btnSize);
        continueButton.setPreferredSize(btnSize);
        
        backButton.setBackground(Color.WHITE);
        backButton.setForeground(Color.BLACK);
        backButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        backButton.setOpaque(true);

        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        deleteButton.setOpaque(true);

        continueButton.setBackground(Color.WHITE);
        continueButton.setForeground(Color.BLACK);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        continueButton.setOpaque(true);
        
        backButton.addActionListener(e -> btnBackActionPerformed(null));
        deleteButton.addActionListener(e -> deleteLetter());
        continueButton.addActionListener(e -> btnContinueActionPerformed(null));
    
        controlPanel.add(backButton);
        controlPanel.add(deleteButton);
        controlPanel.add(continueButton);
    
        keyboardPanel.add(controlPanel);
        keyboardPanel.revalidate();
        keyboardPanel.repaint();
    }
    
    private void addLetter(String letter) {
        if (currentCol < 5) {            
            cells[currentCol].setText(letter);
            secretWord += letter;
            currentCol++;
        }
    }
    
    private void deleteLetter() {
        if (currentCol > 0) {
            currentCol--;
            cells[currentCol].setText("");
            secretWord = secretWord.substring(0, secretWord.length() - 1);
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {                                        
        if (frame != null) {
            frame.showPanel("cardMainMenu"); 
        }
    }                                       

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if (secretWord.length() == 5) {
            if (frame != null) {
                frame.startMultiPlayer(secretWord); 
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Please enter a complete 5-letter word first!");
        }
    }                                           
}
