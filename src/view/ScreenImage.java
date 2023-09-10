package view;
import java.awt.*;
import javax.swing.*;

/**
 * @author Luca Micarelli
 * this class take a path and create a new image 
 * with the dimension of the screen
 */
public class ScreenImage
{

	private Image image;
	
	public ScreenImage(String path)
	{
		ImageIcon imageIcon = new ImageIcon(path);
        Image newImage = imageIcon.getImage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        image = newImage.getScaledInstance(screenWidth, screenHeight, Image.SCALE_DEFAULT);
	}

	/**
	 * @return the image of the class with the full dimension
	 */
	public Image getImage() 
	{return image;}
}
