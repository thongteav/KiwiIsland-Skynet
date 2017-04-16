/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.image.BufferedImage;

/***************************************************************************************
*    Title: New-Beginner-Java-Game-Programming-Src
*    Author: CodeNMore
*    Date: 2014
*    Code version: 
*    Availability: https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src
*
***************************************************************************************/
/**
 * This class contains a buffered image representing a sprite sheet.
 */
public class SpriteSheet {
    //variables-----------------------------------------------------------------
    //a buffered image representing a sprite sheet
    private BufferedImage sheet;
    //--------------------------------------------------------------------------
    
    //constructor---------------------------------------------------------------
    /**
     * This constructor instantiates the sheet with the specified buffered image
     * @param sheet a specified buffered image
     */
    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }
    //--------------------------------------------------------------------------
    
    //helper methods------------------------------------------------------------
    /**
     * This method crops and returns the specified section of the buffered image stored 
     * @param x the beginning x position of the sub image
     * @param y the beginning y position of the sub image
     * @param width the width of the sub image
     * @param height the height of the sub image
     * @return a sub image of the specified region
     */
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x, y, width, height);
    }
    //--------------------------------------------------------------------------
}
