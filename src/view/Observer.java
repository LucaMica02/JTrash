package view;
import model.*;
import java.util.*;

/**
 * @author Luca Micarelli
 * this interface makes an object an observer
 */
public interface Observer 
{
	public void update(ArrayList<Player> players, Deck deck, ArrayList<Card> waste, 
			Player winTurn, Player winGame, int gameLevel, int turn);
}
