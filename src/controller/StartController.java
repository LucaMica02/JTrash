package controller;
import model.*;
import java.awt.*;
import java.io.File;
import javax.swing.*;
import view.*;

/**
 * @author Luca Micarelli
 * this class manage the interaction between the game and
 * the start panel
 */
public class StartController 
{

	private StartPanel panel;
	private GameController controller;
	private Game game;
	
	public StartController(Game game, GameController controller) 
	{
		this.game = game;
		this.controller = controller;
	}
	
	/**
	 * @param cl takes a card layout that it will use to display the
     * next screen
	 * @param mainPanel a main panel containing the various screens
	 * @param name which represents the name of the next screen
	 */
	public void show(CardLayout cl, JPanel mainPanel, String name)
	{cl.show(mainPanel, name);}
	
	/**
	 * @param player to set and add like a real player 
	 * @param file of the player object
	 */
	public void start(RealPlayer player, File file)
	{
		panel.setRealPlayer(player);
		player.setFile(file);
		game.addPlayer(player);
		controller.setRealPlayer(player);
	}
	
	/**
	 * @param panel to set like a start panel
	 */
	public void setPanel(StartPanel panel)
	{this.panel = panel;}
	
	/**
	 * @param player to add at the game
	 */
	public void addPlayer(RealPlayer player)
	{game.addPlayer(player);}
	
	/**
	 * calls the method draw from the start panel
	 */
	public void draw()
	{panel.draw();}
}
