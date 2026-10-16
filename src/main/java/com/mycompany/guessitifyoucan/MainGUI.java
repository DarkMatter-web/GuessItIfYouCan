package com.mycompany.guessitifyoucan;

import javax.swing.*;
import java.awt.*;

/**
 * Main Frame container managing screen switches via CardLayout.
 * @author karlis
 */
public class MainGUI extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainGUI.class.getName());
    private SinglePanel singlePanel;
    private ResultPanel resultPanel;

    /**
     * Creates new form MainGUI
     */
    public MainGUI() {
        initComponents();
        
        // 1. Configure the CardLayout manager on your main container panel
        mainPanel.setLayout(new java.awt.CardLayout());
        
        // 2. Add your application panel objects to the layout structure deck
        mainPanel.add(new MenuPanel(this), "cardMainMenu");
        // FIX: Pass default difficulty '1' to satisfy the SinglePanel constructor
        mainPanel.add(new SinglePanel(this, 1), "cardSinglePlayer");
        mainPanel.add(new MultiPanel(), "cardMultiPlayer");
        mainPanel.add(new WordEntryPanel(this), "cardWordEntry");
        mainPanel.add(new ResultPanel(this, false, ""), "cardResults");
    
        // 3. Set the default initial visible view to the main start menu card
        java.awt.CardLayout cl = (java.awt.CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "cardMainMenu");
        
        // 4. FORCE EXPLICIT WINDOW EXPANSION TO PREVENT SQUISHING BUTTONS OFF-SCREEN
        this.setMinimumSize(new java.awt.Dimension(450, 650));
        this.setPreferredSize(new java.awt.Dimension(450, 650));
        this.setSize(new java.awt.Dimension(450, 650)); 
        
        mainPanel.setMinimumSize(new java.awt.Dimension(450, 650));
        mainPanel.setPreferredSize(new java.awt.Dimension(450, 650));
        
        // 5. Package layout contents together tightly and center the window on screen
        this.pack(); 
        this.setLocationRelativeTo(null); 
    }
    
    public void showPanel(String panelName) {
        java.awt.CardLayout layout = (java.awt.CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, panelName);
    }
    
    public void startSinglePlayer() {
        if (singlePanel != null) {
            mainPanel.remove(singlePanel);
        }
        // FIX: Pass default difficulty '1' here as well
        singlePanel = new SinglePanel(this, 1);
        mainPanel.add(singlePanel, "single");
        showPanel("single");
    }
    
    public void startMultiPlayer(String customSecretWord) {
        if (singlePanel != null) {
            mainPanel.remove(singlePanel);
        }
        // FIX: Pass default difficulty '1' to satisfy the compiler parameters
        singlePanel = new SinglePanel(this, 1);
        
        // Inject the word typed on your virtual keyboard into the game board engine
        singlePanel.setSecretWord(customSecretWord);
        
        mainPanel.add(singlePanel, "multiGame");
        showPanel("multiGame");
    }
    
    public void showResult(boolean win, String word) {
        if (resultPanel != null) {
            mainPanel.remove(resultPanel);
        }
        resultPanel = new ResultPanel(this, win, word);
        mainPanel.add(resultPanel, "result");
        showPanel("result");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 650));

        mainPanel.setPreferredSize(new java.awt.Dimension(450, 650));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel mainPanel;
    // End of variables declaration                   
}