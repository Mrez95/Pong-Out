import greenfoot.World;
import greenfoot.Greenfoot;
import java.awt.Color;

/**
 * An explosion creates an explosion
 * 
 * @author Eddie Zhang
 * @version 0.2.1
 */
public class Explosion  
{
    public static void createExplosion(int x, int y, World w)
    {
        int rot = 360;
        for (int i = 1; i <= 12; i++)
        {
            rot -= 36;
            w.addObject(new ExplosionParticle(rot, Greenfoot.getRandomNumber(4)+2),x,y);
        }
    }
    
    public static void createColorfulExplosion(int x, int y, World w)
    {
        for (int i = 1; i <= 40; i++)
            w.addObject(new ExplosionParticle(Greenfoot.getRandomNumber(360), Greenfoot.getRandomNumber(5)+3, randomColor()),x,y);
    }
    
    private static Color randomColor()
    {
        int r = Greenfoot.getRandomNumber(255);
        int g = Greenfoot.getRandomNumber(255);
        int b = Greenfoot.getRandomNumber(255);
        
        return new Color(r,g,b);
    }
}
