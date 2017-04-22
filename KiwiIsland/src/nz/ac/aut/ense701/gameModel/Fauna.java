
package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.gui.Assets;
import java.awt.Graphics;


/**
 * Fauna at this point represents any species that is not a kiwi or a predator on the island.
 * If we need additional endangered species this class should have descendant classes created.
 * 
 * @author AS
 * @version July 2011
 */
public class Fauna extends Occupant
{
    /**
     * Constructor for objects of class Endangered
     * @param pos the position of the kiwi
     * @param name the name of the kiwi
     * @param description a longer description of the kiwi
     */
    public Fauna(Position pos, String name, String description) 
    {
        super(pos, name, description);
    } 
    
    /**
     * Rendering the image of the fauna in its position of the island
     * 
     * @param g a Graphics object used to draw the image
     */
    public void render(Graphics g){
        //draw the image using the assets by passing in the name of the fauna
        //the position of the fauna times the size of the gridsquare to get the position in terms of pixels
        g.drawImage(Assets.faunaMap.get(this.getName()), this.getPosition().getColumn() * GridSquare.width, this.getPosition().getRow() * GridSquare.height, GridSquare.width, GridSquare.height, null);
    }

    @Override
    public String getStringRepresentation() 
    {
          return "F";
    }    
}
