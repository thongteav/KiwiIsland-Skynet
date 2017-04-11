/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/***************************************************************************************
*    Title: New-Beginner-Java-Game-Programming-Src
*    Author: CodeNMore
*    Date: 2014
*    Code version: 
*    Availability: https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src
*
***************************************************************************************/
public class ImageLoader {
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
        return null;
    } 
}
