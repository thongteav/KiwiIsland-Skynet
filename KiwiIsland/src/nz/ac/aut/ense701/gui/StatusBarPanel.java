/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.Graphics;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 *
 * @author Harindu
 */
public class StatusBarPanel extends JPanel {

  

    public StatusBarPanel(int height,int width) {
        
    }

   

    @Override
    /**
     * Draw the background image onto the panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(nz.ac.aut.ense701.gameModel.ImageLoader.loadImage("res/background.jpg"), 0, 0, getWidth(), getHeight(), null);
        this.repaint();
        this.revalidate();
    }
}
