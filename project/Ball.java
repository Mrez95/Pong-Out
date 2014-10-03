import greenfoot.Actor;
import greenfoot.Greenfoot;
import java.awt.Color;

/**
 * The ball of the game. It moves and bounces off the walls and the paddle.
 * 
 * @author Eddie Zhang
 * @version 1.4
 */
public class Ball extends Square
{
    private int deltaX;
    private int deltaY;
    private int count = 2;
    private boolean stuck = true;

    private Paddle lastPaddleTouched;
    private Paddle otherPaddle;

    public Ball(Paddle p)
    {
        super(10, Color.white);
        lastPaddleTouched = p;
    }

    /**
     * Act. Move if we're not stuck.
     */
    public void act() 
    {
        makeTrail();
        if (!stuck)
            move();
    }

    /**
     * Move the ball. Then check what we've hit.
     */
    public void move()
    {
        setLocation (getX() + deltaX, getY() + deltaY);
        checkPaddle();
        checkBrick();
        checkBall();
        checkWalls();
    }

    /**
     * Check whether we've hit one of the three walls. Reverse direction if necessary.
     */
    private void checkWalls()
    {
        if (getY() == 0 || getY() == getWorld().getHeight()-1)
        {
            deltaY = -deltaY;
        }
        if (getX() == 0 || getX() == getWorld().getWidth()-1)
        {
            try
            {
                otherPaddle.newBall();
            }
            catch (NullPointerException e)
            {
                lastPaddleTouched.newBall();
            }
            getWorld().removeObject(this);
        }
    }

    private void checkPaddle()
    {
        Actor paddle = getOneIntersectingObject(Paddle.class);
        if (paddle != null)
        {
            deltaX = -deltaX;
            int offset = getY() - paddle.getY();
            deltaY = deltaY + (offset/10);
            if (deltaY > 3) {
                deltaY = 3;
            }
            if (deltaY < -3) {
                deltaY = -3;
            }
            otherPaddle = lastPaddleTouched;
            lastPaddleTouched = (Paddle)paddle;
        }            
    }

    //The code in this method was made by mjrb4
    private void checkBrick()
    {
        Actor brick = getOneIntersectingObject(Brick.class);
        if (brick != null)
        {
            int offset = Math.abs(getX() - brick.getX());
            if(offset>=brick.getImage().getWidth()/2) {
                deltaX = -deltaX;
            }
            else {
                deltaY = -deltaY;
            }
            ((Brick)brick).removeSelf(lastPaddleTouched.getID());
            ((PongOut)getWorld()).addScore(lastPaddleTouched.getID());
        }      
    }

    private void checkBall()
    {
        Actor ball = getOneIntersectingObject(Ball.class);
        if (ball != null)
        {
            int offset = Math.abs(getX() - ball.getX());
            if(offset>=ball.getImage().getWidth()/2) {
                deltaX = -deltaX;
            }
            else {
                deltaY = -deltaY;
            }
        }     
    }

    /**
     * Makes a trail behind each ball
     */
    private void makeTrail()
    {
        if (count <= 0)
        {
            getWorld().addObject(new TransparentShrinking(10, Color.gray, 1, 2), getX(), getY());
            count = 2;
        }
        else
            count--;
    }

    /**
     * Release the ball from the paddle.
     */
    public void releaseLeft(boolean up, boolean down)
    {
        if ((!up && !down) || (up && down))
            deltaY = 0;
        else if (up && !down)
            deltaY = -(Greenfoot.getRandomNumber(3)+1);
        else if (!up && down)
            deltaY = Greenfoot.getRandomNumber(3)+1;
        deltaX = -5;
        stuck = false;
    }

    /**
     * Release the ball from the paddle to the right
     */
    public void releaseRight(boolean up, boolean down)
    {
        releaseLeft(up, down);
        deltaX = -deltaX;
    }
}
