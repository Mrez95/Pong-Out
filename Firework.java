import java.awt.Color;

/**
 * Creates a firework
 * 
 * @author Eddie Zhang
 * @version 1.0.0
 */
public class Firework extends greenfoot.Actor
{
    private int x;
    private int y;
    
    private boolean setLoc = false;
    
    private int count = 0;

    public Firework(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void setStartLocation()
    {
        setLocation(x, getWorld().getHeight());
        setLoc = true;
    }
    
    public void act()
    {
        if (!setLoc)
            setStartLocation();
        setLocation(x, getY()-10);
        makeTrail();
        if (getY() <= y)
        {
            Explosion.createColorfulExplosion(x, y, getWorld());
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Makes a trail behind each ball
     */
    private void makeTrail()
    {
        if (count <= 0)
        {
            getWorld().addObject(new ShrinkingSquare(10, Color.gray, 2, 2), getX(), getY());
            count = 0;
        }
        else
            count--;
    }
}
