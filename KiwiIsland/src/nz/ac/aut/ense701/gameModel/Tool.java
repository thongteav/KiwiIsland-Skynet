package nz.ac.aut.ense701.gameModel;

import java.awt.Graphics;
import nz.ac.aut.ense701.gui.Assets;

/**
 * This class represents a tool that can be found on the island
 * and gives the player any sort of advantage. 
 * 
 * @author AS
 * @version July 2011
 */

public class Tool extends Item 
{
    private boolean broken;
    
    /**
     * Construct a tool with known attributes.
     * @param pos the position of the tool
     * @param name the name of the tool
     * @param description a longer description of the tool
     * @param weight the weight of the tool
     * @param size the size of the tool
     */
    public Tool(Position pos, String name, String description, double weight, double size) 
    {
        super(pos, name, description, weight, size);
        this.broken = false;
    }
    
    /**
     * Break the tool
     */
    public void setBroken()
    {
        broken = true;
    }
    
    /**
     * Fix the tool
     */
    public void fix()
    {
        broken = false;
    }
    
    /**
     * Is tool broken
     * @return true if broken
     */
    public boolean isBroken()
    {
        return this.broken;
    }
    
    /**
    * Check if this tool is a predator trap
    * @return true if trap
     */
    public boolean isTrap()
    {
      String name = this.getName();
      return name.equalsIgnoreCase("Trap");
    }
 
    /**
    * Check if this tool is a screwdriver
    * @return true if screwdriver
     */    
    public boolean isScrewdriver() {
       String name = this.getName();
      return name.equalsIgnoreCase("Screwdriver"); 
    }
    
    
    @Override
    public String getStringRepresentation() 
    {
        return "T";
    }
    
    /**
     * Renders the images of the tools
     * 
     * @param g a graphics object used to draw images
     */
    public void render(Graphics g){
        //draw the image using the assets by passing in the name of the tool
        //the position of the tool times the size of the gridsquare to get the position in terms of pixels
        g.drawImage(Assets.toolMap.get(this.getName()), this.getPosition().getColumn() * GridSquare.width, this.getPosition().getRow() * GridSquare.height, GridSquare.width, GridSquare.height, null);
    }
}
