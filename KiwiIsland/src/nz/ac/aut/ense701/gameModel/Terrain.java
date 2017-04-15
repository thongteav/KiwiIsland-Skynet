package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.assets.Assets;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Enumeration class Terrain - represents terrain types on Kiwi Island.
 * 
 * @author AS
 * @version July 2011
 * 
 * Maintenance History
 * Representation strings changed Anne July 2011
 */
public enum Terrain
{
    SAND(".", 1.0, Assets.sand),
    FOREST("*", 2.0, Assets.forest),
    WETLAND ("#", 2.5, Assets.wetland),
    SCRUB("^", 3.0, Assets.scrub),
    WATER("~", 4.0, Assets.water);
    
    private final double difficulty;
    private final String stringRep;
    private BufferedImage texture;
    
    /**
     * Creates a new terrain with a given difficulty 
     * and a string representation
     * @param stringRep the string representation of the terrain.
     * @param difficulty the difficulty of the terrain
     */
    private Terrain(String stringRep, double difficulty, BufferedImage texture)
    {
        this.stringRep  = stringRep;
        this.difficulty = difficulty;
        this.texture = texture;
    }
    
    /**
     * Gets the difficulty of the terrain
     * @return the difficulty of the terrain
     */
    public double getDifficulty()
    {
        return difficulty;
    }
    
    /**
     * Gets a string representation of the terrain to print on the console
     * @return string representation of the terrain
     */
    public String getStringRepresentation()
    {
        return stringRep;
    }
    
    public BufferedImage getTexture(){
        return texture;
    }
    
    public void render(Graphics g, int x, int y){
        g.drawImage(texture, x * GridSquare.width, y * GridSquare.height, GridSquare.width, GridSquare.height, null);
    }
    
    public void renderGrid(Graphics g, int x, int y){
        g.drawRect(x * GridSquare.width, y * GridSquare.height, GridSquare.width, GridSquare.height);
    }
    
    /**
     * Returns a terrain object from the terrain string representation.
     * @param terrainString the string to compare
     * @return the terrain that is associated with this terrain,
     *         or null if the string is invalid
     */
    public static Terrain getTerrainFromStringRepresentation(String terrainString)
    {
        Terrain terrain = null;
        for ( Terrain item : values() ) 
        {
            if ( item.getStringRepresentation().equals(terrainString) )
            {
                terrain = item;
            }
        }
        return terrain;
    }
}

