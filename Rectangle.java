import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.awt.Color;

/**
 * A class that creates a rectangle for the actor. Used for graphicless games.
 * 
 * @author Eddie Zhang
 * @version 1.0.0
 */
public abstract class Rectangle extends Actor
{
    private GreenfootImage myImage;

    /**
     * The constructor for the rectangle class.
     * @param width The width of the Rectangle
     * @param height The height of the Rectangle
     * @param col The color of the Rectangle
     */
    public Rectangle(int width, int height, Color col)
    {
        changeShape(width, height, col);
    }
    
    /**
     * Creates a rectangle image for the actor. This method can be called multiple times
     * @param width The width of the image
     * @param height The height of the image
     * @param col The color of the image
     */
    public void changeShape(int width, int height, Color col)
    {
        myImage = new GreenfootImage(width, height);
        myImage.setColor(col);
        myImage.fill();
        setImage(myImage);
    }
}
