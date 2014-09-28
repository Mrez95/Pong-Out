import greenfoot.GreenfootImage;
import java.util.LinkedList;

/**
 * A score counter that keeps count of scores...
 * 
 * @author Eddie Zhang
 * @version 0.2.1
 */
public class ScoreCounter extends greenfoot.Actor
{
    public static GreenfootImage one = new GreenfootImage("1.png");
    public static GreenfootImage two = new GreenfootImage("2.png");
    public static GreenfootImage three = new GreenfootImage("3.png");
    public static GreenfootImage four = new GreenfootImage("4.png");
    public static GreenfootImage five = new GreenfootImage("5.png");
    public static GreenfootImage six = new GreenfootImage("6.png");
    public static GreenfootImage seven = new GreenfootImage("7.png");
    public static GreenfootImage eight = new GreenfootImage("8.png");
    public static GreenfootImage nine = new GreenfootImage("9.png");
    public static GreenfootImage zero = new GreenfootImage("0.png");

    private int score;

    public ScoreCounter(int startingScore)
    {
        score = startingScore;
        updateImage();
    }

    public ScoreCounter()
    {
        this(0);
    }

    private void updateImage()
    {
        if (score == 0)
        {
            setImage(new GreenfootImage(zero));
            return;
        }
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int num = score;
        while(num > 0)
        {
            stack.push(num % 10);
            num /= 10;
        }
        GreenfootImage img = new GreenfootImage(stack.size()*20, 50);
        for (int i = 0; i < stack.size(); i++)
        {
            GreenfootImage img2;
            int digit = stack.get(i);
            switch(digit)
            {
                case 1: img2 = new GreenfootImage(one);
                break;
                case 2: img2 = new GreenfootImage(two);
                break;
                case 3: img2 = new GreenfootImage(three);
                break;
                case 4: img2 = new GreenfootImage(four);
                break;
                case 5: img2 = new GreenfootImage(five);
                break;
                case 6: img2 = new GreenfootImage(six);
                break;
                case 7: img2 = new GreenfootImage(seven);
                break;
                case 8: img2 = new GreenfootImage(eight);
                break;
                case 9: img2 = new GreenfootImage(nine);
                break;
                case 0: img2 = new GreenfootImage(zero);
                break;
                default:img2 =null;
                break;
            }

            img.drawImage(img2, i*20, 0);
        }
        setImage(img);
    }

    public void add(int n)
    {
        score += n;
        updateImage();
    }

    public void subtract(int n)
    {
        score -= n;
        updateImage();
    }

    public void set(int n)
    {
        score = n;
        updateImage();
    }
    
    public int getScore()
    {
        return score;
    }
}
