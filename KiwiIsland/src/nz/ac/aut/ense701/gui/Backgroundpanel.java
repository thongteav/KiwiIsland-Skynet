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

/**
 *
 * @author admin
 */
public class Backgroundpanel extends JPanel {

    Image image;
    
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension screenSize = kit.getScreenSize();
    int width = screenSize.width * 4 / 5;
    int height = screenSize.height * 4 / 5;

    public Backgroundpanel() {
        // Loads the background image and stores in img object.
        this.image = Toolkit.getDefaultToolkit().createImage("res/background.jpg");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

    }
}
