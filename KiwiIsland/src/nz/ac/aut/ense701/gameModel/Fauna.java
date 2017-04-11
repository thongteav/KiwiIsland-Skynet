
package nz.ac.aut.ense701.gameModel;

import nz.ac.aut.ense701.assets.Assets;
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
    
    public void render(Graphics g){
        g.drawImage(Assets.faunaMap.get(this.getName()), this.getPosition().getColumn() * 64, this.getPosition().getRow() * 64, 64, 64, null);
    }


    @Override
    public String getStringRepresentation() 
    {
          return "F";
    }    
}
