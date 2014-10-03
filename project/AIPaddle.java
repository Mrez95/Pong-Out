import java.util.List;

/**
 * An AI that will follow the closest ball to it.
 * 
 * @author Eddie Zhang
 * @version 0.2.1
 */
public class AIPaddle extends Paddle
{
    private boolean comingTowardsMe = false;
    private List<Ball> attackingBalls;
    private Ball closestBall;
    private int closeX;
    private int timer = 20;

    public AIPaddle()
    {
        this(2);
    }

    public AIPaddle(int id)
    {
        super(id);
    }

    /**
     * The act method for AIPaddle. Releases a ball if it has one,
     * moves up if the closest ball is above it,
     * moves down if the closest ball is below it.
     */
    public void act() 
    {
        if (((PongOut)getWorld()).gameOver())
            return;
        if (haveBall() && timer <= 0)
            releaseBall();
        else if (timer > 0)
            timer--;
        if (comingTowardsMe)
            try
            {
                moveTowardsClosest();
            }
            catch (IllegalStateException e) {}
        determineBeingAttacked();
        waitForBall();
    }

    /**
     * Moves towards the closest ball
     */
    public void moveTowardsClosest()
    {
        if (closestBall.getY() > getY())
            move(4);
        if (closestBall.getY() < getY())
            move(-4);
    }

    /**
     * Determines if the AI paddle is being "attacked" (a ball is coming
     * towards it)
     */
    private void determineBeingAttacked()
    {
        findClosestBall();
        try
        {
            if (id == 2)
                comingTowardsMe = closeX < closestBall.getX();
            if (id == 1)
                comingTowardsMe = closeX > closestBall.getX();
        }
        catch (NullPointerException e){}
        if (!comingTowardsMe)
            findClosestAttackingBall(closestBall);
        closeX = closestBall.getX();
    }

    /**
     * Finds the closest ball towards the AIPaddle and will store it in
     * closestBall
     */
    private void findClosestBall()
    {
        findClosestAttackingBall(null);
    }

    /**
     * Finds the closest ball that is not the one provided.
     */
    private void findClosestAttackingBall(Ball ball)
    {
        int closestX = Integer.MAX_VALUE;
        for (Ball b : (List<Ball>)getWorld().getObjects(Ball.class))
        {
            int dist = Math.abs(getX()-b.getX());
            if (dist < closestX && !b.equals(ball))
            {
                closestX = dist;
                closestBall = b;
            }
        }
    }
    
    public void releaseBall()
    {
        movingUp = greenfoot.Greenfoot.getRandomNumber(10) > 5;
        movingDown = !movingUp;
        super.releaseBall();
    }
    
    public void newBall()
    {
        super.newBall();
        timer = 20;
    }
}
