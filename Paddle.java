import greenfoot.Greenfoot;
import java.awt.Color;

/**
 * The game paddle. It is keyboard controlled (up, down, space, or w, s, e). It also 
 * generates a new ball when it is created itself.
 * 
 * @author Eddie Zhang
 * @version 2.0
 */
public class Paddle extends Rectangle
{
    protected Ball myBall;
    private String[] controls;
    protected int id;
    private int score = 0;
    private boolean waitingBall = false;
    private int waitingCounter = 30;
    
    protected boolean movingUp = false;
    protected boolean movingDown = false;

    /**
     * The constructor for Paddle. Creates a white paddle 10x50.
     * @param player This specifies what player it is. It can only be 1-2.
     * 1 is left, 2 is right.
     */
    public Paddle(int player)
    {
        super(10,50,Color.white);
        if (player > 2)
            throw new IllegalArgumentException("There can't be more than two players!");
        if (player < 1)
            throw new IllegalArgumentException("You can't have a negative amount of players!");
        id = player;
        createControls();
    }

    private void createControls()
    {
        if (id == 2)
            controls = new String[]{"up", "down", "space"};
        else if (id == 1)
            controls = new String[]{"w","s","e"};
        else
            throw new IllegalStateException("Unknown error!");
    }

    /**
     * The act method for Paddle
     */
    public void act() 
    {
        movingUp = false;
        movingDown = false;
        if (((PongOut)getWorld()).gameOver())
            return;
        if (Greenfoot.isKeyDown(controls[0]))
        {
            move(-4);
            movingUp = true;
        }
        if (Greenfoot.isKeyDown(controls[1]))
        {
            move(4);
            movingDown = true;
        }
        if (haveBall() && Greenfoot.isKeyDown(controls[2]))
            releaseBall();
        waitForBall();
    }
    
    protected void waitForBall()
    {
        if (waitingCounter < 30 && waitingBall)
            waitingCounter++;
        if (waitingCounter >= 30 && waitingBall)
        {
            waitingBall = false;
            newBall();
            waitingCounter = 0;
        }
    }

    public void move(int dist)
    {
        setLocation(getX(), getY()+dist);
        try
        {
            if (myBall != null)
            {
                myBall.setLocation(myBall.getX(), myBall.getY()+dist);
            }
        }
        catch (IllegalStateException e){}
    }

    public void newBall()
    {
        if (myBall != null)
        {
            waitingBall = true;
            return;
        }
        myBall = new Ball(this);
        if (id == 1)
            getWorld().addObject(myBall, getX()+10, getY());
        else
            getWorld().addObject(myBall, getX()-10, getY());
    }

    public boolean haveBall()
    {
        return myBall != null;
    }

    public void releaseBall()
    {
        if (id == 1)
            myBall.releaseRight(movingUp, movingDown);
        else
            myBall.releaseLeft(movingUp, movingDown);
        myBall = null;
    }
    
    public int getID()
    {
        return id;
    }
}
