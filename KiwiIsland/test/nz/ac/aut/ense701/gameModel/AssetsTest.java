package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.gui.Assets;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Tests of Assets class
 *
 * @author Harindu Tillekeratna
 * @author David Balzer
 * @author George Xu
 * @author Thong Teav
 */
public class AssetsTest {
    public AssetsTest(){
    
    }
    
    /**
     * Test of getting the corresponding image by passing the name as a key in the Assets class
     */
    @Test
    public void testGetImage(){
        Assets.init();
        Island island = new Island(3, 3);
        Position postion = new Position(island, 1, 1);
        
        Fauna oystercatcher = new Fauna(postion, "Oystercatcher", "A nesting oystercatcher");
        assertEquals(Assets.oystercatcher, Assets.faunaMap.get(oystercatcher.getName()));
        
        Predator rat = new Predator(postion, "Rat", "A nasty rat");
        assertEquals(Assets.rat, Assets.predatorMap.get(rat.getName()));
        
        Tool trap = new Tool(postion, "Trap", "A trap to get rid of predators", 1, 1);
        assertEquals(Assets.trap, Assets.toolMap.get(trap.getName()));
        
        Kiwi kiwi = new Kiwi(postion, "Kiwi", "A cute kiwi");
        assertEquals(Assets.kiwi, Assets.faunaMap.get(kiwi.getName()));
    }
}
