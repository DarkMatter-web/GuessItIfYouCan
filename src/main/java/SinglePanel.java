
import javax.swing.*;
import java.awt.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */

/**
 *
 * @author karlis
 */
public class SinglePanel extends javax.swing.JPanel {
    
    private MainGUI frame;    
    private JLabel[][] cells = new JLabel[5][5];
    private int currentRow = 0;
    private int currentCol = 0;
    private String secretWord;
    private JButton[] keyboardButtons = new JButton[26];
    
    /**
     * Creates new form SinglePanel
     */
    public SinglePanel(MainGUI frame) {
        initComponents();
        
        this.frame = frame;
        
        gridPanel.setLayout(new GridLayout(5, 5, 5, 5));
        createGrid();
        createKeyboard();
        
        secretWord = WordLab.single(1);
    }
      
    private void createGrid() {

        gridPanel.removeAll();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {

                JLabel label = new JLabel("", SwingConstants.CENTER);

                label.setOpaque(true);
                label.setBackground(Color.WHITE);
                label.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                cells[row][col] = label;
                gridPanel.add(label);
            }
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
                
                button.addActionListener(e -> {
                addLetter(String.valueOf(letter));
                });
                
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
        
        enterButton.addActionListener(e -> {
        submitGuess();
        });

        deleteButton.addActionListener(e -> {
        deleteLetter();
        });
    
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

        if (currentRow >= 5) {
            return;
        }

        if (currentCol != 5) {
            JOptionPane.showMessageDialog(this, "Enter 5 letters!");
            return;
        }

        // Collect the letters from the current row
        String guess = "";

        for (int col = 0; col < 5; col++) {
            guess += cells[currentRow][col].getText();
        }

        // Check the guess
        String[] result = GuessLoop.checkGuess(secretWord, guess);

        // Color the squares
        colorGuess(result);
        colorKeyboard(guess, result);

        // Check for a win
        if (guess.equals(secretWord)) {
            currentRow = 5;
            frame.showResult(true, secretWord);
            return;
        }

        // Move to the next row
        currentRow++;
        currentCol = 0;

        // Check for a loss
        if (currentRow == 5) {
            frame.showResult(false, secretWord);
        }
    }
    
    private void colorGuess(String[] result) {

        for (int col = 0; col < 5; col++) {

            switch (result[col]) {

                case "green":
                    cells[currentRow][col].setBackground(Color.GREEN);
                    break;

                case "yellow":
                    cells[currentRow][col].setBackground(Color.YELLOW);
                    break;

                case "red":
                    cells[currentRow][col].setBackground(Color.GRAY);
                    break;
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
                    if (!Color.GREEN.equals(currentColor)) {
                        button.setBackground(Color.YELLOW);
                    }
                    break;
                    
                    case "red":
                    if (!Color.GREEN.equals(currentColor)
                        && !Color.YELLOW.equals(currentColor)) {
                        button.setBackground(Color.GRAY);
                    }
                    break;
            }
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridPanel = new javax.swing.JPanel();
        keyboardPanel = new javax.swing.JPanel();

        setPreferredSize(new java.awt.Dimension(400, 450));

        gridPanel.setPreferredSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout gridPanelLayout = new javax.swing.GroupLayout(gridPanel);
        gridPanel.setLayout(gridPanelLayout);
        gridPanelLayout.setHorizontalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        gridPanelLayout.setVerticalGroup(
            gridPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addComponent(keyboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gridPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(keyboardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gridPanel;
    private javax.swing.JPanel keyboardPanel;
    // End of variables declaration//GEN-END:variables
}
