package nz.ac.aut.ense701.gameModel;

import java.awt.Graphics;
import nz.ac.aut.ense701.gui.Assets;

/**
 * This class represents food that can be found on the island
 * and supplies energy when being consumed (used) by the player.
 * 
 * @author AS
 * @version July 2011
 */
public class Food extends Item
{
    private double energy;

    /**
     * Construct a food object with known attributes.
     * @param pos the position of the food object
     * @param name the name of the food object
     * @param description a longer description of the food object
     * @param weight the weight of the food object
     * @param size the size of the food object
     * @param energy stamina contribution of the food object
     *               when the player uses the object
     */
    public Food(Position pos, String name, String description, double weight, double size, double energy) 
    {
        super(pos, name, description,weight, size);
        this.energy = energy;
    }

    /**
     * Gets the energy of the food.
     * @return the energy of the food
     */
    public double getEnergy() {
        return this.energy;
    }
    
    /**
     * @return string representation of food
     */
    @Override
    public String getStringRepresentation() 
    {
        return "E";
    }
    
    /**
     * Rendering the image of the food in its position of the island
     * 
     * @param g a Graphics object used to draw the image
     */
    public void render(Graphics g){
        //draw the image using the assets by passing in the name of the food
        //the position of the food times the size of the gridsquare to get the position in terms of pixels
        g.drawImage(Assets.foodMap.get(this.getName()), this.getPosition().getColumn() * GridSquare.width, this.getPosition().getRow() * GridSquare.height, GridSquare.width, GridSquare.height, null);
    }
}
