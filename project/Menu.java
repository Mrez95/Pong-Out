import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Asks for amount of human players and plays the game
 * 
 * @author Eddie Zhang
 * @version 0.8.1
 */
public class Menu extends World
{
    public Menu()
    {
        super(600, 400, 1);
        Greenfoot.start();
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if (key != null)
            changeWorld(key);
    }
    
    public void changeWorld(String key)
    {
        if (key.equals("0"))
            Greenfoot.setWorld(new PongOut(0));
        if (key.equals("1"))
            Greenfoot.setWorld(new PongOut(1));
        if (key.equals("2"))
            Greenfoot.setWorld(new PongOut(2));
    }
}
