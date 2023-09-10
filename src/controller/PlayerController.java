package controller;
import java.awt.CardLayout;

import javax.swing.JPanel;

import model.*;
import view.*;

/**
 * @author Luca Micarelli
 * this class manage the interaction between the player
 * panel and the game class
 */
public class PlayerController 
{

	private Game game;
	private PlayerPanel panel;
	private GameController gameController;

	public PlayerController(Game game, GameController gameController)
	{
		this.game = game; 
		this.gameController = gameController;
	}
	
	/**
	 * @param n number of the players to instantiate
	 * @param level number to set like a game level
	 */
	public void playerInstance(int n, int level)
	{
		for (int i = 1; i<n+1; i++)
		{
			Player p1 = new ArtificialPlayer("Player" + i, "src/Data/Images/GameAvatar/Avatar"+i+".png");
			game.addPlayer(p1);
		}
		game.setLevel(level);
	}
	
	/**
	 * @param cl takes a card layout that it will use to display the
     * next screen
	 * @param mainPanel a main panel containing the various screens
	 * @param name which represents the name of the next screen
	 */
	public void show(CardLayout cl, JPanel mainPanel, String name)
	{
		cl.show(mainPanel, name);
		gameController.startGame();
	}
	
	/**
	 * @param panel to set like a player panel
	 */
	public void setPanel(PlayerPanel panel)
	{this.panel = panel;}
	
	/**
	 * @param player to set like a real player
	 */
	public void setRealPlayer(RealPlayer player)
	{
		panel.setRealPlayer(player);
		drawPanel();
	}
	
	/**
	 * call the method draw of the player panel
	 */
	public void drawPanel()
	{panel.draw();}
}
