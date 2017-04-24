/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.Graphics;
import nz.ac.aut.ense701.gui.Assets;

/**
 *
 * @author ZJ
 */
public class Inventory {
    
	public boolean active = false;
	
	private final int invX = 570, invY = 4;
        private final int invWidth=512, invHeight=384;
		
                

	
	public void render(Graphics g){
		g.drawImage(Assets.inventory, invX, invY, invWidth, invHeight, null);
        }
        
		
	
}
