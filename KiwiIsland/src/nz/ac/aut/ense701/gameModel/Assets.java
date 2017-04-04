/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gameModel;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Thong Teav
 */
public class Assets {
    public static BufferedImage[] player_down;
    public static BufferedImage[] player_up;
    public static BufferedImage[] player_left;
    public static BufferedImage[] player_right;
    public static BufferedImage sand, forest, wetland, scrub, water;
    public static BufferedImage kiwi, tui, crab, fernbird, heron, oystercatcher, robin;
    public static HashMap<String, BufferedImage> faunaMap;
    
    public static int player_width = 180, player_height = 269;
    public static int tile_size = 32;
    
    public static void init(){
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("res/player.png"));        
        player_down = new BufferedImage[4];
        player_down[0] = playerSheet.crop(0, 0, player_width, player_height);
        player_down[1] = playerSheet.crop(player_width, 0, player_width, player_height);
        player_down[2] = playerSheet.crop(player_width * 2, 0, player_width, player_height);
        player_down[3] = playerSheet.crop(player_width * 3, 0, player_width, player_height);
        
        player_up = new BufferedImage[4];
        player_up[0] = playerSheet.crop(0, player_height, player_width, player_height);
        player_up[1] = playerSheet.crop(player_width, player_height, player_width, player_height);
        player_up[2] = playerSheet.crop(player_width * 2, player_height, player_width, player_height);
        player_up[3] = playerSheet.crop(player_width * 3, player_height, player_width, player_height);
        
        player_left = new BufferedImage[4];
        player_left[0] = playerSheet.crop(0, player_height * 2, player_width, player_height);
        player_left[1] = playerSheet.crop(player_width, player_height * 2, player_width, player_height);
        player_left[2] = playerSheet.crop(player_width * 2, player_height * 2, player_width, player_height);
        player_left[3] = playerSheet.crop(player_width * 3, player_height * 2, player_width, player_height);
        
        player_right = new BufferedImage[4];
        player_right[0] = playerSheet.crop(0, player_height * 3, player_width, player_height);
        player_right[1] = playerSheet.crop(player_width, player_height * 3, player_width, player_height);
        player_right[2] = playerSheet.crop(player_width * 2, player_height * 3, player_width, player_height);
        player_right[3] = playerSheet.crop(player_width * 3, player_height * 3, player_width, player_height);
        
        SpriteSheet terrainSheet = new SpriteSheet(ImageLoader.loadImage("res/tile.png"));
        sand = terrainSheet.crop(tile_size * 6, tile_size, tile_size * 2, tile_size * 2);
        forest = terrainSheet.crop(tile_size * 2, tile_size * 4, tile_size * 2, tile_size * 2);
        wetland = terrainSheet.crop(0, tile_size * 7, tile_size * 2, tile_size * 2);
        scrub = terrainSheet.crop(tile_size * 4, tile_size * 4, tile_size * 2, tile_size * 2);
        water = new SpriteSheet(ImageLoader.loadImage("res/terrain.png")).crop(tile_size * 2, 0, tile_size, tile_size);
        
        kiwi = ImageLoader.loadImage("res/occupants/kiwi.png");
        tui = ImageLoader.loadImage("res/occupants/tui.png");
        crab = ImageLoader.loadImage("res/occupants/crab.png");
        fernbird = ImageLoader.loadImage("res/occupants/fernbird.png");
        heron = ImageLoader.loadImage("res/occupants/heron.png");
        oystercatcher = ImageLoader.loadImage("res/occupants/oystercatcher.png");
        robin = ImageLoader.loadImage("res/occupants/robin.png");
        
        faunaMap = new HashMap<String, BufferedImage>();
        faunaMap.put("Kiwi", kiwi);
        faunaMap.put("Tui", tui);
        faunaMap.put("Crab", crab);
        faunaMap.put("Fernbird", fernbird);
        faunaMap.put("Heron", heron);
        faunaMap.put("Oystercatcher", oystercatcher);
        faunaMap.put("Robin", robin);
    }
}
