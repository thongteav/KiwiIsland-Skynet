/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.gui.ImageLoader;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of ImageLoader class
 * 
 * @author Harindu Tillekeratna
 * @author David Balzer
 * @author George Xu
 * @author Thong Teav
 */
public class ImagaLoaderTest {
    public ImagaLoaderTest(){
        
    }
    
    /**
     * Test the imageLoading method in ImageLoader class.
     */
    @Test
    public void testImageLoading(){
        String validPath = "res/occupants/kiwi.png";
        assertNotNull(ImageLoader.loadImage(validPath));
    }
}
