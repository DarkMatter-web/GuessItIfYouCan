package com.mycompany.guessitifyoucan;

import javax.swing.*;
import java.awt.*;

/**
 * Active gameplay board panel.
 * @author karlis
 */
public class SinglePanel extends javax.swing.JPanel {
    
    private MainGUI frame;    
    private JLabel[][] cells = new JLabel[5][5];
    private int currentRow = 0;
    private int currentCol = 0;
    private String secretWord;
    private JButton[] keyboardButtons = new JButton[26];
    
    // Inside SinglePanel.java
    // FIX: Change the signature to accept 'int difficulty'
    public SinglePanel(MainGUI frame, int difficulty) {
        initComponents();
        this.frame = frame;
        
        // 1. Force the main background container layout to use GridBagLayout explicitly
        this.setLayout(new java.awt.GridBagLayout());
        
        // 2. Setup your pristine, centered GridBagConstraints layout definitions
        java.awt.GridBagConstraints gbcGrid = new java.awt.GridBagConstraints();
        gbcGrid.gridx = 0;
        gbcGrid.gridy = 0;
        gbcGrid.gridwidth = 1;
        gbcGrid.gridheight = 1;
        gbcGrid.weightx = 1.0;
        gbcGrid.weighty = 0.5; 
        gbcGrid.anchor = java.awt.GridBagConstraints.SOUTH; 
        gbcGrid.insets = new java.awt.Insets(40, 0, 10, 0); 
        
        java.awt.GridBagConstraints gbcKbd = new java.awt.GridBagConstraints();
        gbcKbd.gridx = 0;
        gbcKbd.gridy = 1;
        gbcKbd.gridwidth = 1;
        gbcKbd.gridheight = 1;
        gbcKbd.weightx = 1.0;
        gbcKbd.weighty = 0.5;
        gbcKbd.anchor = java.awt.GridBagConstraints.NORTH; 
        gbcKbd.insets = new java.awt.Insets(10, 0, 40, 0); 
        
        // 3. Remount the panels programmatically using your clean positioning constraints
        this.removeAll();
        this.add(gridPanel, gbcGrid);
        this.add(keyboardPanel, gbcKbd);
        
        createGrid();
        createKeyboard();
        
        gridPanel.setPreferredSize(new java.awt.Dimension(400, 250));
        keyboardPanel.setPreferredSize(new java.awt.Dimension(400, 220));
        
        // FIX: Inject the chosen difficulty integer into your game word library loop!
        secretWord = WordLab.single(difficulty); 
        
        this.revalidate();
        this.repaint();
    }



    public void setSecretWord(String customWord) {
        this.secretWord = customWord.toUpperCase();
    }
      
    private void createGrid() {
        gridPanel.removeAll();
        gridPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 10));
        
        JPanel innerGrid = new JPanel(new GridLayout(5, 5, 5, 5));
        innerGrid.setOpaque(false);

        for (int row = 0; row < 5; row++) {
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

                cells[row][col] = label;
                innerGrid.add(label);
            }
        }

        gridPanel.add(innerGrid);
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
                keyboardButtons[letter - 'A'] = button;
                rowPanel.add(button);
            }
            keyboardPanel.add(rowPanel);
        }

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 0));

        JButton enterButton = new JButton("ENTER");
        JButton deleteButton = new JButton("DELETE");

        enterButton.setPreferredSize(new Dimension(80, 40));
        deleteButton.setPreferredSize(new Dimension(80, 40));
        enterButton.setMargin(new Insets(0, 0, 0, 0));
        deleteButton.setMargin(new Insets(0, 0, 0, 0));
        
        enterButton.setBackground(Color.WHITE);
        enterButton.setForeground(Color.BLACK);
        enterButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        enterButton.setOpaque(true);
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        deleteButton.setOpaque(true);
        
        enterButton.addActionListener(e -> submitGuess());
        deleteButton.addActionListener(e -> deleteLetter());
    
        controlPanel.add(enterButton);
        controlPanel.add(deleteButton);
        keyboardPanel.add(controlPanel);

        keyboardPanel.revalidate();
        keyboardPanel.repaint();
    }
    
    private void addLetter(String letter) {
        if (currentRow < 5 && currentCol < 5) {            
            cells[currentRow][currentCol].setText(letter);
            currentCol++;
        }
    }
    
    private void deleteLetter() {
        if (currentRow < 5 && currentCol > 0) {
            currentCol--;
            cells[currentRow][currentCol].setText("");
        }
    }
    
    private void submitGuess() {
        if (currentRow >= 5) return;

        if (currentCol != 5) {
            JOptionPane.showMessageDialog(this, "Enter 5 letters!");
            return;
        }

        String guess = "";
        for (int col = 0; col < 5; col++) {
            guess += cells[currentRow][col].getText();
        }

        String[] result = GuessLoop.checkGuess(secretWord, guess);
        colorGuess(result);
        colorKeyboard(guess, result);

        if (guess.equals(secretWord)) {
            currentRow = 5;
            frame.showResult(true, secretWord);
            return;
        }

        currentRow++;
        currentCol = 0;

        if (currentRow == 5) {
            frame.showResult(false, secretWord);
        }
    }
    
    private void colorGuess(String[] result) {
        for (int col = 0; col < 5; col++) {
            switch (result[col]) {
                case "green": cells[currentRow][col].setBackground(Color.GREEN); break;
                case "yellow": cells[currentRow][col].setBackground(Color.YELLOW); break;
                case "red": cells[currentRow][col].setBackground(Color.GRAY); break;
            }
        }
    }
    
    private void colorKeyboard(String guess, String[] result) {
        for (int i = 0; i < 5; i++) {
            char letter = guess.charAt(i);
            JButton button = keyboardButtons[letter - 'A'];
            Color currentColor = button.getBackground();
            
            switch (result[i]) {
                case "green":
                    button.setBackground(Color.GREEN);
                    break;
                case "yellow":
                    if (!Color.GREEN.equals(currentColor)) button.setBackground(Color.YELLOW);
                    break;
                case "red":
                    if (!Color.GREEN.equals(currentColor) && !Color.YELLOW.equals(currentColor)) button.setBackground(Color.GRAY);
                    break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridPanel = new javax.swing.JPanel();
        keyboardPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(400, 450));
        setLayout(new java.awt.GridBagLayout());

        gridPanel.setPreferredSize(new java.awt.Dimension(200, 200));
        gridPanel.setLayout(new java.awt.GridLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 104, 0, 0);
        add(gridPanel, gridBagConstraints);

        keyboardPanel.setPreferredSize(new java.awt.Dimension(400, 200));

        javax.swing.GroupLayout keyboardPanelLayout = new javax.swing.GroupLayout(keyboardPanel);
        keyboardPanel.setLayout(keyboardPanelLayout);
        keyboardPanelLayout.setHorizontalGroup(
            keyboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        keyboardPanelLayout.setVerticalGroup(
            keyboardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.ipadx = 400;
        gridBagConstraints.ipady = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 0, 38, 0);
        add(keyboardPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gridPanel;
    private javax.swing.JPanel keyboardPanel;
    // End of variables declaration//GEN-END:variables
}
