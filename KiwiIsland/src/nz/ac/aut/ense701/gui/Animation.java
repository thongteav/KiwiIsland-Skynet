package nz.ac.aut.ense701.gui;


import java.awt.image.BufferedImage;

/***************************************************************************************
*    Title: New-Beginner-Java-Game-Programming-Src
*    Author: CodeNMore
*    Date: 2014
*    Code version: 
*    Availability: https://github.com/CodeNMore/New-Beginner-Java-Game-Programming-Src
*
***************************************************************************************/

public class Animation {
    private int speed, index;
    private long lastTime, timer;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }
    
    public void update(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){//check for array out of bound
                index = 0;
            }
        }
    }
    
    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}
