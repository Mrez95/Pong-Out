import java.awt.Color;

/**
 * A brick that can be destroyed. May drop a powerup.
 * 
 * @author Eddie Zhang
 * @version 0.1.1
 */
public class Brick extends Rectangle
{
    public Brick()
    {
        super(30, 10, Color.white);
    }
    
    public void removeSelf(int id)
    {
        /*if (drop = true)
            dropPowerUp();*/
        Explosion.createExplosion(getX(),getY(),getWorld());
        ((PongOut)getWorld()).addScore(id);
        getWorld().removeObject(this);
    }
}
