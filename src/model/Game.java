package model;
import view.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.IntStream;
import javax.swing.Timer;

/**
 * @author Luca Micarelli
 * This class is the brain and manage the logic of the game 
 */
public class Game implements Observable
{
	private List<Observer> observers;
	private ArrayList<Player> players;
	private Deck deck;
	private ArrayList<Card> waste;
	private Player winTurn;
	private Player winGame;
	private int gameLevel;
	private int turn;
	
	public Game()
	{
		observers = new ArrayList<>();
		players = new ArrayList<Player>();
		waste = new ArrayList<Card>();
		turn = 0;
	}
	
	/**
	 * This method start a new game
	 */
	public void startGame()
	{
		for (Player p:players)
		{p.resetLevel();}
		play();
	}
	
	/**
	 *  This method start a current game 
	 */
	public void play()
	{
		Deck deck = new Deck();
		if (players.size()>2)
		{
			Deck deck1 = new Deck();
			deck.addDeck(deck1);
		}
		deck.shuffle();
		this.deck = deck;
		winTurn = null;
		winGame = null;
		turn = 0;
		waste.clear();
		for (Player p : players)
		{
			p.resetHand(); 
			p.removeUserCard();
		}
		this.distribute();
		notifyObserver();}
	
	/**
	 * makes artificial players play at 1 second intervals
	 */
	public void playAP()
	{
		if (!players.isEmpty()) {
		if (winTurn==null && winGame==null)
		{
		if (!players.get(turn).hasCard()) {
		Timer timer = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        drawCard();
	        takeTime();
	    }});
		timer.setRepeats(false);
	    timer.start();}
		
		else {takeTime();}
		}}
	}
	
	/**
	 * takes time before making the game move
	 */
	public void takeTime()
	{
		Timer timer = new Timer(1200, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        if (!players.isEmpty()) 
	        {
	        playHand(players.get(turn));
	        if (!players.get(turn).getIsReal()) {playAP();}
	        }
	    }});
		timer.setRepeats(false);
	    timer.start();
	}
	
	/**
	 * @param p player who has to make the move
	 */
	public void playHand(Player p) 
	{
	    Card card = p.getCard();
	    int rank = card.getRank();
	    if ((rank > 0 && rank < 11 - p.getLevel()) 
	    		&& p.getCard(rank - 1).isHole() == true)
	    {
	    	Card newCard = p.getCard(rank - 1);
            p.setCard(rank - 1, card);
            card.showCard();
            p.setUserCard(newCard);
	    } 
	    else if ((rank == 0 || rank == 13)
	    		&& p.getFirstFreePos() != -1)
	    {
	    	Card newCard = p.getCard(p.getFirstFreePos());
            p.setCard(p.getFirstFreePos(), card);
            card.showCard();
            p.setUserCard(newCard);
	    } 
	    else 
	    {discard(p);}
	    
	    winCheck(p);
	    notifyObserver();
	}
	
	/**
	 * 
	 * @param p real player who has to make the move
	 * @param i index in which to set the card
	 */
	public void playRealHand(Player p, int i)
	{
		Card c = p.getCard(i);
		p.getCard().showCard();
		p.setCard(i, p.getCard());
		p.setUserCard(c);
		winCheck(p);
		notifyObserver();
	}
	
	/**
	 * draw the card from the deck or the waste
	 */
	public void drawCard()
	{
		if (!players.isEmpty()) {
		Player p = players.get(turn);
		int value = waste.get(waste.size()-1).getRank();
		if (!waste.isEmpty() && 				
			(value < 11-p.getLevel() && value > 0 &&
			p.getCard(value-1).isHole() == true)
			|| (value == 0 || value == 13))
		{drawWaste(p);}
		else 
		{drawDeck(p);}}
	}
	
	/**
	 * @param p player that have to draw from the deck
	 */
	public void drawDeck(Player p)
	{
		p.setUserCard(deck.getCard(deck.getSize()-1)); 
		deck.removeCard(deck.getSize()-1);
		notifyObserver();
	}
	
	/**
	 * @param p player that have to draw from the waste
	 */
	public void drawWaste(Player p)
	{
		p.setUserCard(waste.get(waste.size()-1)); 
		waste.remove(waste.size()-1);
		notifyObserver();
	}
	
	/**
	 * @param p player that have to discard
	 */
	public void discard(Player p)
	{
		p.removeUserCard();
		waste.add(p.getCard());
		turn = turn==players.size()-1 ? 0 : turn+1;
		notifyObserver();
	}
	
	/**
	 * distribute the cards to the players
	 */
	public void distribute()
	{
		  players.forEach(p -> {
		        IntStream.range(0, 10 - p.getLevel())
		                .forEach(i -> {
		                    p.addCard(deck.getCard(0));
		                    deck.removeCard(0);
		                });
		    });
	}
	
	/**
	 * @param p player to check if he won
	 */
	public void winCheck(Player p)
	{
		if (p.isWinner())
		{
			if (p.getLevel() == gameLevel-1)
			{winGame = p;}
			else
			{winTurn = p; p.increaseLevel();}
		}
	}
	
	/**
	 * @param p player to add to the game
	 */
	public void addPlayer(Player p)
	{players.add(p);}
		
	/**
	 * @return the list of the players
	 */
	public ArrayList<Player> getPlayers()
	{return players;}
	
	/**
	 * @return the list of the waste
	 */
	public ArrayList<Card> getWaste()
	{return waste;}
	
	/**
	 * @return the deck
	 */
	public Deck getDeck()
	{return deck;}
	
	/**
	 * @param level of the game
	 */
	public void setLevel(int level)
	{gameLevel = level;}
	
	/**
	 * @return the number of game level
	 */
	public int getLevel()
	{return gameLevel;}
	
	/**
	 *  add an observer
	 */
	@Override
	public void addObserver(Observer observer) 
	{observers.add(observer);}
	
	/**
	 *  remove an observer
	 */
	@Override
	public void removeObserver(Observer observer) 
	{observers.remove(observer);}
	
	/**
	 * notify all the game observers
	 */
	@Override
	public void notifyObserver() 
	{
        for (Observer observer : observers) 
        {observer.update(players,deck,waste,winTurn,winGame,gameLevel,turn);}
    }
	
	/**
	 * reset the list of players and observers
	 */
	public void exit()
	{players.clear(); observers.clear();}
} 

