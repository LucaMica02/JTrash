package controller;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JPanel;
import javax.swing.Timer;

import model.*;
import view.*;

/**
 * @author Luca Micarelli
 * this class manage the interactions between the game class
 * and their view
 */
public class GameController 
{	
	private Game game;
	private GamePanel panel;
	private RealPlayer player;
	
	public GameController(Game game)
	{this.game = game;}
	
	/**
	 * call the method playAP of the class game
	 */
	public void playAP()
	{game.playAP();}
	
	/**
	 * @param p player that has to draw by the deck
	 */
	public void drawDeck(Player p)
	{game.drawDeck(p);}
	
	/**
	 * @param p player that has to draw by the waste
	 */
	public void drawWaste(Player p)
	{game.drawWaste(p);}
	
	/**
	 * @param p player that has to play his hand
	 */
	public void playHand(Player p)
	{game.playHand(p);}
	
	/**
	 * @param p real player that has to play his hand
	 * @param i index where he has to put the card
	 */
	public void playRealHand(Player p, int i)
	{game.playRealHand(p,i);}
	
	/**
	 * @param p player that has to discard 
	 */
	public void discard(Player p)
	{game.discard(p);}
	
	/**
	 * @return the list of the waste from the game
	 */
	public ArrayList<Card> getWaste()
	{return game.getWaste();}
	
	/**
	 * restart the game and continue with the next level 
	 */
	public void restart()
	{game.play();}
	
	/**
	 * @return the number of game level
	 */
	public int getLevel()
	{return game.getLevel();}
	
	/**
	 * serialize the real player of the game
	 */
	public void serializePlayer()
	{player.serialize();}
	
	/**
	 * @param panel to set like a game panel
	 */
	public void setPanel(GamePanel panel)
	{this.panel = panel;}
	
	/**
	 * start the game
	 */
	public void startGame()
	{
		game.addObserver(panel);
		game.startGame();
	}
	
	/**
	 * restart the game
	 */
	public void restartGame()
	{game.startGame();}
	
	/**
	 * set the victory
	 */
	public void setVictory()
	{player.setVictory();}
	
	/**
	 * se the defeat
	 */
	public void setDefeat()
	{player.setDefeat();}
	
	/**
	 * @param player to set like a real player
	 */
	public void setRealPlayer(RealPlayer player)
	{this.player = player;}
	
	/**
	 * exit from the game
	 */
	public void exit()
	{game.exit();}
	
	/**
	 * @param cl takes a card layout that it will use to display the
     * next screen
	 * @param mainPanel a main panel containing the various screens
	 * @param name which represents the name of the next screen
	 */
	public void show(CardLayout cl, JPanel mainPanel, String name)
	{cl.show(mainPanel, name);}
}
