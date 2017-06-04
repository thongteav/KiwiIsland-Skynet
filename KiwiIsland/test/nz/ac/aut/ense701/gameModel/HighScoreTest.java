/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author david
 */
public class HighScoreTest {
    
    HighScore highScore;
    
    public HighScoreTest() {
    }
    
    
    @Before
    public void setUp() {
         highScore = new HighScore();
    }
    
    @After
    public void tearDown() {
        highScore = null;
    }

    /**
     * Test of toString method, of class HighScore.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HighScore instance = new HighScore("ABC", 0);
        String expResult = "ABC: 0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class HighScore.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        HighScore o = new HighScore("ABC", 10);
        HighScore instance = new HighScore("DEF", 5);
        int expResult = -1;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of createHighScore method, of class HighScore.
     */
    @Test
    public void testCreateHighScore() {
        System.out.println("createHighScore");
        //Creating highscore list done in setup
        assertEquals(HighScore.getHighScores().size(), 5);
    }

    /**
     * Test of saveScores method, of class HighScore.
     */
    @Test
    public void testSaveScores() {
        System.out.println("saveScores");
        HighScore score = new HighScore("WIN", 10000);
        HighScore placeholder = HighScore.getHighScores().get(4);
        HighScore.saveScores(score);
        HighScore test = HighScore.getHighScores().get(0);
        assertEquals(score, test);
        
        //removes the score added in the text file
        HighScore.getHighScores().set(0,placeholder);
        HighScore.saveScoresToFile();
    }

    

    
    
}
