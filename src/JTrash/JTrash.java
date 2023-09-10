package JTrash;
import model.*;
import javax.swing.SwingUtilities;
import controller.*;
import view.*;

/**
 * @author Luca Micarelli
 * This main class instantiate the main components of the 
 * application and launch the game, we adopt a MVC pattern
 */
public class JTrash 
{

	private Game game;
	private MainController controller;
	private MainView view;
	
	/**
	 * in the constructor we instantiate a new game, a MainView and a
     * MainController, and we connect the three components among them
	 */
	public JTrash()
	{
		game = new Game();
		controller = new MainController(game);
		view = new MainView(controller);
		
		controller.setView(view);
		view.setController(controller);
	}
	
	/**
	 * in main we invoke the JTrash class using the invokeLater  
	 * method which ensures that the creation of the JTrash object 
	 * occurs in the Swing UI thread
	 */
	public static void main (String [] args)
	{
        SwingUtilities.invokeLater(() -> new JTrash());
	}
}
