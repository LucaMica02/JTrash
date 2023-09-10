package model;
import java.io.Serializable;
import java.util.*;

/**
 * @author Luca Micarelli
 * this abstract class represents a generic player
 */
public abstract class Player implements Serializable
{

	private String username;
	private String avatar;
	private ArrayList<Card> hand = new ArrayList<Card>();
    private Card card;
	private int level;
	private boolean isReal;
	private boolean turn;
	private boolean hasCard;
	
	/**
	 * @param username a string that represents the player name
	 * @param avatar a relative path of the avatar image
	 * @param isReal a boolean value true if the player is real player
	 * false else
	 */
	public Player(String username, String avatar, boolean isReal)
	{
		this.username = username;
		this.avatar = avatar;
		this.isReal = isReal;
		level = 0;
		hasCard = false;
	}
	
	public Player() 
	{}
	
	/**
	 * @return the boolean value isReal
	 */
	public boolean getIsReal()
	{return isReal;}
	
	/**
	 * @param bool boolean value that set if is the player turn
	 */
	public void setTurn(boolean bool)
	{turn = bool;}
	
	/**
	 * @return the boolean value turn
	 */
	public boolean getTurn()
	{return turn;}
	
	/**
	 * @param i index 
	 * @return the card at index i of the player hand
	 */
	public Card getCard(int i)
	{return hand.get(i);}
	
	/**
	 * @param c card to add at the hand
	 */
	public void addCard(Card c)
	{hand.add(c);}
	
	/**
	 * @param i index
	 * @param c card to set in the hand at index i 
	 */
	public void setCard(int i, Card c)
	{hand.set(i, c);}
	
	/**
	 * @return the username of the player
	 */
	public String getUsername()
	{return username;}
	
	/**
	 * @return the current level of the player
	 */
	public int getLevel()
	{return level;}

	/**
	 * increase the level 
	 */
	public void increaseLevel()
	{level++;}
	
	/**
	 * reset the level
	 */
	public void resetLevel()
	{level = 0;}
	
	/**
	 * @return a boolean value true if the player is winner false else
	 */
	public boolean isWinner()
	{
		for (Card c : hand)
		{if (c.isHole() == true) {return false;}}
		return true;
	}
	
	/**
	 * @return the first free position from the hand
	 */
	public int getFirstFreePos()
	{
		for (int i = 0; i<hand.size(); i++)
		{if (hand.get(i).isHole() == true) {return i;}}
		return -1;
	}
	
	/**
	 * @return the list of the card of the hand
	 */
	public ArrayList<Card> getHand()
	{return hand;}
	
	/**
	 * @return the size of the hand
	 */
	public int getHandSize()
	{return hand.size();}
	
	/**
	 * reset the hand
	 */
	public void resetHand()
	{hand.clear();}
	

	/**
	 * @param c card to set like a user card to play
	 */
	public void setUserCard(Card c)
	{
		this.card = c;
		hasCard = true;
	}
	
	/**
	 * remove the user card
	 */
	public void removeUserCard()
	{hasCard = false;}
	
	/**
	 * @return the user card
	 */
	public Card getCard()
	{return card;}
	
	/**
	 * @return a boolean value true if the player has a card false else
	 */
	public boolean hasCard()
	{return hasCard;}
	
	/**
	 * @return the relative path of the avatar image
	 */
	public String getAvatar()
	{return avatar;}
	
}
