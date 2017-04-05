/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Thong
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
