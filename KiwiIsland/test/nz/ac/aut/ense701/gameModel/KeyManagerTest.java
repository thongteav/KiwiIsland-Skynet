/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Harindu
 */
public class KeyManagerTest {
    
    public KeyManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of update method, of class KeyManager.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        KeyManager instance = new KeyManager();
        instance.update();
             
    }

    /**
     * Test of keyJustPressed method, of class KeyManager.
     */
    @Test
    public void testKeyJustPressed() {
        System.out.println("keyJustPressed");
        int keyCode = 0;
        KeyManager instance = new KeyManager();
        boolean expResult = false;
        boolean result = instance.keyJustPressed(keyCode);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of keyTyped method, of class KeyManager.
     */
    @Test
    public void testKeyTyped() {
        System.out.println("keyTyped");
        KeyEvent e = null;
        KeyManager instance = new KeyManager();
        instance.keyTyped(e);
        
    }

    /**
     * Test of keyPressed method, of class KeyManager.
     */
    
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent e = null;
        KeyManager instance = new KeyManager();
        instance.keyPressed(e);
     }

    /**
     * Test of keyReleased method, of class KeyManager.
     */
    @Test
    public void testKeyReleased() {
        System.out.println("keyReleased");
        KeyEvent e = null;
        KeyManager instance = new KeyManager();
        instance.keyReleased(e);
     }
    
}
