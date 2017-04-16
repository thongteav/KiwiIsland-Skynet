package nz.ac.aut.ense701.gameModel;

import java.awt.Graphics;
import nz.ac.aut.ense701.assets.Assets;

/**
 * Predator represents a predator on the island.
 * If more specific behaviour is required for particular predators, descendants 
 * for this class should be created
 * @author AS
 * @version July 2011
 */
public class Predator extends Fauna
{

    /**
     * Constructor for objects of class Predator
     * @param pos the position of the predator object
     * @param name the name of the predator object
     * @param description a longer description of the predator object
     */
    public Predator(Position pos, String name, String description) 
    {
        super(pos, name, description);
    } 
 
    @Override
    public String getStringRepresentation() 
    {
        return "P";
    }    
    
    /**
     * Rendering the images of predators
     * 
     * @param g a graphics object used to draw images
     */
    public void render(Graphics g){
        //draw the image using the assets by passing in the name of the predator
        //the position of the predator times the size of the gridsquare to get the position in terms of pixels
        g.drawImage(Assets.predatorMap.get(this.getName()), this.getPosition().getColumn() * GridSquare.width, this.getPosition().getRow() * GridSquare.height, GridSquare.width, GridSquare.height, null);
    }
}
