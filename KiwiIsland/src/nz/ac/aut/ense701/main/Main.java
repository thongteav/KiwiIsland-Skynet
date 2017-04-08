package nz.ac.aut.ense701.main;

import nz.ac.aut.ense701.gameModel.Game;

import nz.ac.aut.ense701.gui.KiwiCountUI;
import nz.ac.aut.ense701.gui.KiwiIslandUI;


/**
 * Kiwi Count Project
 *
 * @author Thong,Harindu,David,George
 * @version 2017
 */
public class Main {

    /**
     * Main method of Kiwi Count.
     *
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // create the game object

        /*
        final Game game = new Game();
        // create the GUI for the game
        final KiwiCountUI  gui  = new KiwiCountUI(game);
        // make the GUI visible
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                gui.setVisible(true);
            }
        });*/
        new KiwiIslandUI();
        

    }

}
