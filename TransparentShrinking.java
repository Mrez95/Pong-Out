import java.awt.Color;

/**
 * Makes it's trail transparent
 * 
 * @author Eddie Zhang
 * @version 1.0.0
 */
public class TransparentShrinking extends ShrinkingSquare
{
    private int trans;

    public TransparentShrinking(int size, Color col, int shrinkingSpeed, int delay)
    {
        super(size, col, shrinkingSpeed, delay);
        trans = 200/(size*delay)-shrinkingSpeed;
    }

    public void shrink()
    {
        super.shrink();
        Color transparent = moreTransparent();
        changeShape(getImage().getWidth(), transparent);
    }
    
    private Color moreTransparent()
    {
        Color col = getImage().getColor();
        int r = col.getRed();
        int g = col.getGreen();
        int b = col.getBlue();
        int a = col.getAlpha()-trans;
        
        return new Color(r,g,b,a);
    }
}
