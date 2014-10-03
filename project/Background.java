import greenfoot.Actor;
import greenfoot.GreenfootImage;
import java.awt.Color;

/**
 * A background is an actor of a single color that fills the screen with it's specific color.
 * 
 * @version 1.0.5
 */
public class Background extends Actor
{
    private Color myColor;
    private boolean filledBackground = false;
    
    private int x = 0;
    private int y = 0;

    public Background(Color c)
    {
        myColor = c;
    }
    
    public void act()
    {
        if (x == 0 || y == 0)
        {
            x = getWorld().getWidth()/2;
            y = getWorld().getHeight()/2;
        }
        if (getX() != x || getY() != y)
            setLocation(x,y);
        if (!filledBackground)
            fillBackground();
    }
    
    public void fillBackground()
    {
        int width = getWorld().getWidth();
        int height = getWorld().getHeight();
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(myColor);
        img.fill();
        setImage(img);
        setLocation(width/2, height/2);
        filledBackground = true;
    }
    
    public void changeColor(Color col)
    {
        myColor = col;
        fillBackground();
    }
}
