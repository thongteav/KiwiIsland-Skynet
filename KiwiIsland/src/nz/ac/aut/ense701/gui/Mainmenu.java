/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author admin
 */
public class Mainmenu {

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.draw3DRect(100, 100, 110, 110, true);
        g.setPaintMode();
    }
}
