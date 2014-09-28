import java.awt.Color;
import greenfoot.Greenfoot;

/**
 * An explosion particle for an explosion
 * 
 * @author Eddie Zhang
 * @version 0.2.1
 */
public class ExplosionParticle extends Square
{
    private int rot;
    private int lifeCounter = 0;
    private int falling = 0;

    private int gravity = -Greenfoot.getRandomNumber(10) - 5;

    public ExplosionParticle(int rotation, int size)
    {
        this(rotation, size, Color.yellow);
    }
    
    public ExplosionParticle(int rotation, int size, Color col)
    {
        super(size, col);
        rot = rotation;
    }

    public void act() 
    {
        move();	
        fall();
        if (lifeCounter < 30)
            lifeCounter++;
        if (getY() >= getWorld().getHeight()-1)
            getWorld().removeObject(this);
    }

    private void move()
    {
        setRotation(rot);
        move(2);
        setRotation(0);
    }

    private void fall()
    {
        setLocation(getX(), getY() + gravity);
        setRotation(getRotation() + 10);
        if (getX() <= 5 || getX() >= getWorld().getWidth() - 5)
            rot = -rot;
        gravity++;
    }
}
