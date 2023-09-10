package model;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author Luca Micarelli
 * this class represents the real players
 */
public class RealPlayer extends Player
{

	private int gamesWon;
	private int gamesLost;
	private int score;
	private File file;
	
	/**
	 * @param username a string that represents the player name
	 * @param avatar a relative path of the avatar image
	 */
	public RealPlayer(String username, String avatar)
	{
		super(username, avatar, true);
		setGamesWon(0);
		setGamesLost(0);
		setScore(0);
	}
	
	public RealPlayer() {}
	
	/**
	 * serialize the object 
	 */
	public void serialize()
	{
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) 
		{
 	        outputStream.writeObject(this);
 	    } catch (IOException er) {
 	        er.printStackTrace();
 	    }
	}
	
	/**
	 * set the victory
	 */
	public void setVictory()
	{
		gamesWon++;
		score +=50;
	}
	
	/**
	 * set the defeat
	 */
	public void setDefeat()
	{
		gamesLost++;
		score -=10;
	}

	/**
	 * @return the number of games won
	 */
	public int getGamesWon() 
	{return gamesWon;}

	/**
	 * @param gamesWon to set like a games won
	 */
	public void setGamesWon(int gamesWon) 
	{this.gamesWon = gamesWon;}

	/**
	 * @return the number of games lost
	 */
	public int getGamesLost() 
	{return gamesLost;}

	/**
	 * @param gameLost to set like a games lost
	 */
	public void setGamesLost(int gamesLost) 
	{this.gamesLost = gamesLost;}

	/**
	 * @return the player score
	 */
	public int getScore() 
	{return score;}

	/**
	 * @param score to set like score player
	 */
	public void setScore(int score) 
	{this.score = score;}
	
	/**
	 * @param file to set like a file of the object player 
	 */
	public void setFile(File file)
	{this.file = file;}
}