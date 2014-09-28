import java.awt.Color;

/**
 * A square actor
 * 
 * @author Eddie Zhang
 * @version 1.0.0
 */
public abstract class Square extends Rectangle
{
    /**
     * The constructor for a Square actor
     * @param size The length of each side
     * @param col The color of the Square
     */
    public Square(int size, Color col)
    {
        super(size, size, col);
    }
    
    public void changeShape(int size, Color col)
    {
        super.changeShape(size, size, col);
    }
}
