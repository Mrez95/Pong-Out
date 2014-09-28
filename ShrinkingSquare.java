import java.awt.Color;

/**
 * A square that shrinks in size
 * 
 * @author Eddie Zhang
 * @version 1.0.0
 */
public class ShrinkingSquare extends Square
{
    private int speed;
    private int delayCounter = 0;
    private int delay;

    public ShrinkingSquare(int size, Color col, int shrinkingSpeed, int delay)
    {
        super(size, col);
        this.delay = delay;
        speed = shrinkingSpeed;
    }

    /**
     * Shrinks itself until there is no more, than it dissapears
     */
    public void act() 
    {
        if (getImage().getWidth() - speed <= 0)
            getWorld().removeObject(this);
        else
            shrink();
    }    

    public void shrink()
    {
        if (delayCounter >= delay)
        {
            changeShape(getImage().getWidth()-speed, getImage().getColor());
            delayCounter = 0;
        }
        else
            delayCounter++;
    }
}
