package controller;
import view.*;
import java.awt.CardLayout;
import javax.swing.JPanel;
import model.*;

/**
 * @author Luca Micarelli
 * this class manage the various controller class
 */
public class MainController 
{	
	private StartController startController;
	private PlayerController playerController;
	private GameController gameController;

	public MainController(Game game)
	{
		gameController = new GameController(game);
		startController = new StartController(game,gameController);
		playerController = new PlayerController(game,gameController);
	}
	
	/**
	 * @return the start controller
	 */
	public StartController getStartController() 
	{return startController;}

	/**
	 * @return the player controller
	 */
	public PlayerController getPlayerController() 
	{return playerController;}

	/**
	 * @return the game controller
	 */
	public GameController getGameController() 
	{return gameController;}
		
	/**
	 * @param view main view to take the various view panel
	 */
	public void setView(MainView view)
	{
		view.getStartPanel().setController(startController);
		view.getPlayerPanel().setController(playerController);
		view.getGamePanel().setController(gameController);
	}
	
	/**
	 * @param cl takes a card layout that it will use to display the
     * next screen
	 * @param mainPanel a main panel containing the various screens
	 * @param name which represents the name of the next screen
	 */
	public void exit(CardLayout cl, JPanel mainPanel, String name) 
	{
		cl.show(mainPanel, name);
		startController.draw();
		gameController.exit();
	}
	
	/**
	 * @param player to set like a real player 
	 */
	public void setRealPlayer(RealPlayer player)
	{playerController.setRealPlayer(player);}
}
