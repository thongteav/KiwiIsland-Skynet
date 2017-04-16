/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nz.ac.aut.ense701.gui;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * This class stores static BufferedImage objects which can be used repeatedly.
 * 
 * @author Harindu Tillekeratna
 * @author David Balzer
 * @author George Xu
 * @author Thong Teav
 */

public class Assets {
    //variables-----------------------------------------------------------------
    public static BufferedImage playerDown, playerUp, playerLeft, playerRight;//player
    public static BufferedImage sand, forest, wetland, scrub, water;//terrains
    public static BufferedImage kiwi, tui, crab, fernbird, heron, oystercatcher, robin;//fauna
    public static BufferedImage sandwich, muesliBar, apple, orangeJuice;//food
    public static BufferedImage trap, screwdriver;//tools
    public static BufferedImage rat, cat, kiore, stoat, possum;//predators
    
    public static HashMap<String, BufferedImage> faunaMap, foodMap, predatorMap, toolMap;
    
    public static int playerWidth = 180, playerHeight = 269;
    public static int squareSize = 32;
    //--------------------------------------------------------------------------
    
    /**
     * This method instantiates all the variables with the images in the res folder.
     */
    public static void init(){
        //load the sprite sheet for the player
        SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage("res/player.png")); 
        //crop the corresponding section of the sprite sheet
        playerDown = playerSheet.crop(0, 0, playerWidth, playerHeight);
        playerUp = playerSheet.crop(0, playerHeight, playerWidth, playerHeight);
        playerLeft = playerSheet.crop(0, playerHeight * 2, playerWidth, playerHeight);
        playerRight = playerSheet.crop(0, playerHeight * 3, playerWidth, playerHeight);
        
        //load the sprite sheet for the terrain
        SpriteSheet terrainSheet = new SpriteSheet(ImageLoader.loadImage("res/tile.png"));
        //crop the corresponding section of the sprite sheet
        sand = terrainSheet.crop(squareSize * 6, squareSize, squareSize * 2, squareSize * 2);
        forest = terrainSheet.crop(squareSize * 2, squareSize * 4, squareSize * 2, squareSize * 2);
        wetland = terrainSheet.crop(0, squareSize * 7, squareSize * 2, squareSize * 2);
        scrub = terrainSheet.crop(squareSize * 4, squareSize * 4, squareSize * 2, squareSize * 2);
        water = new SpriteSheet(ImageLoader.loadImage("res/terrain.png")).crop(squareSize * 2, 0, squareSize, squareSize);
        
        //load the images of the occupants
        kiwi = ImageLoader.loadImage("res/occupants/kiwi.png");
        tui = ImageLoader.loadImage("res/occupants/tui.png");
        crab = ImageLoader.loadImage("res/occupants/crab.png");
        fernbird = ImageLoader.loadImage("res/occupants/fernbird.png");
        heron = ImageLoader.loadImage("res/occupants/heron.png");
        oystercatcher = ImageLoader.loadImage("res/occupants/oystercatcher.png");
        robin = ImageLoader.loadImage("res/occupants/robin.png");
        
        //create a hashmap for fanua that returns the image of a fauna by providing its name
        faunaMap = new HashMap<String, BufferedImage>();
        faunaMap.put("Kiwi", kiwi);
        faunaMap.put("Tui", tui);
        faunaMap.put("Crab", crab);
        faunaMap.put("Fernbird", fernbird);
        faunaMap.put("Heron", heron);
        faunaMap.put("Oystercatcher", oystercatcher);
        faunaMap.put("Robin", robin);
        
        //load the images of food
        sandwich = ImageLoader.loadImage("res/food/sandwich.png");
        muesliBar = ImageLoader.loadImage("res/food/muesli bar.png");
        apple = ImageLoader.loadImage("res/food/apple.png");
        orangeJuice = ImageLoader.loadImage("res/food/orange juice.png");
        
        //create a hashmap for food that returns the image of food by providing its name
        foodMap = new HashMap<String, BufferedImage>();
        foodMap.put("Sandwich", sandwich);
        foodMap.put("Muesli Bar", muesliBar);
        foodMap.put("Apple", apple);
        foodMap.put("Orange Juice", orangeJuice);
        
        //load the images of the predators
        rat = ImageLoader.loadImage("res/predators/rat.png");
        cat = ImageLoader.loadImage("res/predators/cat.png");
        kiore = ImageLoader.loadImage("res/predators/kiore.png");
        stoat = ImageLoader.loadImage("res/predators/stoat.png");
        possum = ImageLoader.loadImage("res/predators/possum.png");
        
        //create a hashmap for predators which returns the image of a predator by providing its name
        predatorMap = new HashMap<String, BufferedImage>();
        predatorMap.put("Rat", rat);
        predatorMap.put("Cat", cat);
        predatorMap.put("Kiore", kiore);
        predatorMap.put("Stoat", stoat);
        predatorMap.put("Possum", possum);
        
        //load images of the tools
        trap = ImageLoader.loadImage("res/tools/trap.png");
        screwdriver = ImageLoader.loadImage("res/tools/screwdriver.png");
        
        //create a hashmap for tools which returns the image of the tool by providing its name
        toolMap = new HashMap<String, BufferedImage>();
        toolMap.put("Trap", trap);
        toolMap.put("Screwdriver", screwdriver);
    }
}
