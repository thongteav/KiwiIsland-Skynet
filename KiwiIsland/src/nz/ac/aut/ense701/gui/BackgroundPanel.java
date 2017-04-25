/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;
import nz.ac.aut.ense701.gameModel.ImageLoader;

/**
 * This class draws the background image 
 * 
 * @author Harindu Tillekeratna
 * @author David Balzer
 * @author George Xu
 * @author Thong Teav
 */
public class BackgroundPanel extends JPanel {

    public BackgroundPanel() {     
    }

    @Override
    /**
     * Draw the background image onto the panel
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(ImageLoader.loadImage("res/background.jpg"), 0, 0, getWidth(), getHeight(), null);
        this.repaint();
        this.revalidate();
    }
}
