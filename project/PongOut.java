import greenfoot.World;
import greenfoot.Greenfoot;
import greenfoot.Actor;
import java.awt.Color;

/**
 * The world of Pong-Out! Keeps track of scores, bricks, and more.
 * 
 * @author Eddie Zhang
 * @version 0.1.1
 */
public class PongOut extends World
{
    private static Background bg = new Background(Color.black);
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private ScoreCounter leftScore = new ScoreCounter();
    private ScoreCounter rightScore = new ScoreCounter();

    private static Menu menu = new Menu();

    private int[] score = new int[2];

    private boolean gameover = false;

    private int counter = 150;

    //An array of 2D arrays, I'd never think I could use that for a pong-breakout mix...
    private static int[][][] patterns = {
            {
                {0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1},
                {0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1},
                {0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1},
                {0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1},
                {0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1},
                {0,1,0,1,0,1,0},
                {1,0,1,0,1,0,1}
            },
            {
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1},
                {1,0,1,0,1,0,1}
            },
            {
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0},
                {1,1,1,1,1,1,1},
                {0,0,0,0,0,0,0}
            }
        };
    private int pattern = 0;

    /**
     * The constructor for the world.
     */
    public PongOut(int normalPlayers)
    {
        super(600, 400, 1);
        setPaintOrder(Ball.class, Paddle.class, Rectangle.class, ScoreCounter.class, Background.class);
        if (normalPlayers == 0)
        {
            paddleLeft = new AIPaddle(1);
            paddleRight = new AIPaddle(2);
        }
        else if (normalPlayers == 1)
        {
            paddleLeft = new Paddle(1);
            paddleRight = new AIPaddle(2);
        }
        else if (normalPlayers == 2)
        {
            paddleLeft = new Paddle(1);
            paddleRight = new Paddle(2);
        }
        else
            throw new IllegalArgumentException("normalPlayers must be 0 through 2!");
        addObject(bg,0,0);
        bg.act();
        addObject(paddleLeft,10, getHeight()/2);
        addObject(paddleRight,getWidth()-10, getHeight()/2);
        addObject(leftScore, getWidth()/2-40, 30);
        addObject(rightScore, getWidth()/2+40, 30);
        nextPattern();
    }

    private void nextPattern()
    {
        for (int i = 0; i < patterns[pattern][0].length; i++)
            for (int j = 0; j < patterns[pattern].length; j++)
                if (patterns[pattern][j][i] == 1)
                    addObject(new Brick(), 150+i*50, 75+j*25);
        pattern++;
        removeObjects(getObjects(Ball.class));
        paddleLeft.newBall();
        paddleRight.newBall();
    }

    public void act()
    {
        if (!gameover && pattern == 3 && getObjects(Brick.class).size() == 0)
            determineWinner();
        else if (!gameover && getObjects(Brick.class).size() == 0)
            nextPattern();
        if (gameover)
            explosions();
    }

    public void addScore(int id)
    {
        if (id == 1)
            leftScore.add(1);
        else if (id == 2)
            rightScore.add(1);
    }

    public void addScore(Actor a)
    {
        if (a.getX() == 0)
            rightScore.add(1);
        else if (a.getX() == getWidth()-1)
            leftScore.add(1);
    }

    public void removeObject(Actor a)
    {
        if (a instanceof Ball)
            addScore(a);
        super.removeObject(a);
    }

    public void determineWinner()
    {
        gameover = true;
        int winner;
        if (leftScore.getScore() > rightScore.getScore())
            winner = 1;
        else if (leftScore.getScore() < rightScore.getScore())
            winner = 2;
        else
            winner = 0;
        if (winner == 0)
            javax.swing.JOptionPane.showMessageDialog(null,"It's a tie!", "And the winner is...",0);
        else
            javax.swing.JOptionPane.showMessageDialog(null,"The winner is player " + winner, "And the winner is...",0);
        removeObjects(getObjects(ScoreCounter.class));
        removeObject(bg);
        setBackground(new greenfoot.GreenfootImage("Background2.png"));
    }

    public boolean gameOver()
    {
        return gameover;
    }

    private void explosions()
    {
        if (counter >= 30)
        {
            addObject(new Firework(Greenfoot.getRandomNumber(getWidth()), Greenfoot.getRandomNumber(300)),0,0);
            counter = 0;
        }
        else
            counter++;
        if (Greenfoot.isKeyDown("space"))
            Greenfoot.setWorld(menu);
    }
}
