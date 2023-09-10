package view;
import java.awt.*;
import javax.swing.*;

/**
 * @author Luca Micarelli
 * this class take a path and draw the image on the background
 * of the panel
 */
public class Panel extends JPanel
{
	private Image image;
	protected Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected int screenWidth = screenSize.width;
    protected int screenHeight = screenSize.height;
	
	public Panel(String path)
	{image = new ScreenImage(path).getImage();}
	
	/**
	 * draw the image on the background
	 */
	protected void paintComponent(Graphics g) 
	{
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
