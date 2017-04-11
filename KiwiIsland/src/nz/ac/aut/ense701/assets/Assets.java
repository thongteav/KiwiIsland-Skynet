/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 *
 * @author Thong Teav
 */
public class Assets {
    public static BufferedImage player_down, player_up, player_left, player_right;
    public static BufferedImage sand, forest, wetland, scrub, water;
    public static BufferedImage kiwi, tui, crab, fernbird, heron, oystercatcher, robin;
    public static HashMap<String, BufferedImage> faunaMap;
    
    public static int player_width = 180, player_height = 269;
    public static int tile_size = 32;
    
    public static void init(){
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("res/player.png")); 
        player_down = playerSheet.crop(0, 0, player_width, player_height);
        player_up = playerSheet.crop(0, player_height, player_width, player_height);
        player_left = playerSheet.crop(0, player_height * 2, player_width, player_height);
        player_right = playerSheet.crop(0, player_height * 3, player_width, player_height);
        
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
