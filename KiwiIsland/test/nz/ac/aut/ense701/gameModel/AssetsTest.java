package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.assets.Assets;
import org.junit.Test;
import static org.junit.Assert.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Thong
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
    }
}
