/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.assets;

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

/**
 * This class provides a static method to produce an image from the file
 */
public class ImageLoader {
    /**
     * Returns a buffered image of the file if path is valid and null if otherwise.
     * @param path a String representing the path to the image file
     * @return Buffered image if path is valid and null if not.
     */
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File(path));
        } catch (IOException ex) {
            //if the path is invalid
            ex.printStackTrace();//print the stack trace
            System.exit(1);//close the application with an error
        }
        return null;
    } 
}
