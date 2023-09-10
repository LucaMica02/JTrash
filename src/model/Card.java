package model;
import java.io.Serializable;

/**
 * @author Luca Micarelli
 * This class represents the playing card
 */
public class Card implements Serializable
{
	
	private Rank rank;
	private Seed seed;
	private boolean hole = true;
	
	/**
	 * @param rank for the card rank
	 * @param seed for the card seed
	 */
	public Card(Rank rank, Seed seed)
	{
		this.rank = rank;
		this.seed = seed;
	}
	
	/**
	 * @return the card rank
	 */
	public int getRank()
	{return rank.getRank();}
	
	/**
	 * @return a Boolean value, true if covered, false otherwise
	 */
	public boolean isHole()
	{return hole;}
	
	/**
	 *  Set the hole value to false for discover the card
	 */
	public void showCard()
	{hole = false;}
	
	/**
	 *  @return a String value that represents the rank 
	 *  and the seed of the card
	 */
	@Override
	public String toString()
	{return rank.toString() + seed.toString();}
}
