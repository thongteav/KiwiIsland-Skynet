/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.image.BufferedImage;

/**
 *
 * @author Thong
 */
public class Assets {
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    
    public static int width = 180, height = 269;
    
    public static void init(){
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("res/player.png"));
        
        player_down = new BufferedImage[4];
        player_down[0] = sheet.crop(0, 0, width, height);
        player_down[1] = sheet.crop(width, 0, width, height);
        player_down[2] = sheet.crop(width * 2, 0, width, height);
        player_down[3] = sheet.crop(width * 3, 0, width, height);
        
        player_up = new BufferedImage[4];
        player_up[0] = sheet.crop(0, height, width, height);
        player_up[1] = sheet.crop(width, height, width, height);
        player_up[2] = sheet.crop(width * 2, height, width, height);
        player_up[3] = sheet.crop(width * 3, height, width, height);
        
        player_left = new BufferedImage[4];
        player_left[0] = sheet.crop(0, height * 2, width, height);
        player_left[1] = sheet.crop(width, height * 2, width, height);
        player_left[2] = sheet.crop(width * 2, height * 2, width, height);
        player_left[3] = sheet.crop(width * 3, height * 2, width, height);
        
        player_right = new BufferedImage[4];
        player_right[0] = sheet.crop(0, height * 3, width, height);
        player_right[1] = sheet.crop(width, height * 3, width, height);
        player_right[2] = sheet.crop(width * 2, height * 3, width, height);
        player_right[3] = sheet.crop(width * 3, height * 3, width, height);
    }
}
