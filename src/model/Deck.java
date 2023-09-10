package model;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Luca Micarelli
 * This class represents the playing deck
 */
public class Deck
{

	private ArrayList<Card> deck;
	
	/**
	 * Builds a complete playing deck through the use of streams 
	 * with all the combinations of seed and rank
	 */
	public Deck() {
	    deck = Stream.of(Seed.values())
	            .flatMap(s -> Stream.of(Rank.values()).map(r -> new Card(r, s)))
	            .collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Method for shuffle the cards of the deck
	 */
	public void shuffle()
	{Collections.shuffle(deck);}
	
	/**
	 * @param deck to add to the current deck
	 */
	public void addDeck(Deck deck)
	{this.deck.addAll(deck.getDeck());}
	
	/**
	 * @return the deck
	 */
	public ArrayList<Card> getDeck()
	{return deck;}
	
	/**
	 * @param i index of the card 
	 * @return the card at index i
	 */
	public Card getCard(int i)
	{return deck.get(i);}
	
	/**
	 * @param i index of the card 
	 * remove the card at index i
	 */
	public void removeCard(int i)
	{deck.remove(i);}
	
	/**
	 * @return the size of the deck
	 */
	public int getSize()
	{return deck.size();}
}
