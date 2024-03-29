/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/***************************************************************************************
*    Title: New-Beginner-Java-Game-Programming-Src
*    Author: CodeNMore
*    Date: 2014
*    Code version: 
*    Availability: https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src
*
***************************************************************************************/

/**
 * This class manages key input from the user.
 */
public class KeyManager implements KeyListener {
    //variables-----------------------------------------------------------------
    private boolean[] keys, justPressed, cantPress;
    //--------------------------------------------------------------------------
    
    //constructor---------------------------------------------------------------
    public KeyManager() {
        keys = new boolean[256];
        justPressed = new boolean[256];
        cantPress = new boolean[256];
    }
    //--------------------------------------------------------------------------
    
    //helper methods------------------------------------------------------------
    /**
     * Updates the status of the key input
     */
    public void update() {
        for (int i = 0; i < keys.length; ++i) {
            if (cantPress[i] && !keys[i]) {//if key can't be pressed and key is not pressed
                cantPress[i] = false;//key should be able to be pressed
            } else if (justPressed[i]) {//if key has just been pressed
                cantPress[i] = true;//key can't be pressed
                justPressed[i] = false;//key has not just been pressed
            }

            if (!cantPress[i] && keys[i]) {//if key can be pressed and key is being pressed
                justPressed[i] = true;//then key has just been pressed
            }
        }
    }

    /**
     * Checks whether if a key has just been pressed
     *
     * @param keyCode an integer representation of the key on the keyboard
     * @return true if the specified key has just been pressed and false if otherwise
     */
    public boolean keyJustPressed(int keyCode) {
        //check for array out of bound
        if (keyCode < 0 || keyCode >= keys.length) {
            return false;
        }
        return justPressed[keyCode];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e != null) {
            if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
                return;
            }

            keys[e.getKeyCode()] = true;//set the value of the key pressed as true
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e != null) {
            if (e.getKeyCode() < 0 || e.getKeyCode() >= keys.length) {
                return; //to avoid array out of bound exception
            }
            keys[e.getKeyCode()] = false;//if key has been released, then key is no longer pressed
            justPressed[e.getKeyCode()] = false;//if key has been released, then it has not just been pressed
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //not implementing
    }
    //--------------------------------------------------------------------------
}
