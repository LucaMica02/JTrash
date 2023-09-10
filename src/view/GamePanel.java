package view;
import model.*;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;

/**
 * @author Luca Micarelli
 * this class manage the view of the game playing
 */
public class GamePanel extends Panel implements Observer
{

	private ImageIcon icon;
	private GameController controller;
	private MainController mainController;
	private JPanel mainPanel;
	private CardLayout cardLayout;
	private JButton restart;
	private Menu menu;
	
	public GamePanel(JPanel mainPanel, CardLayout cardLayout, MainController mainController)
	{
		super("src/Data/Images/game_background.jpg");
		this.mainPanel = mainPanel;
		this.cardLayout = cardLayout;
		this.mainController = mainController;
		menu = new Menu(mainPanel,cardLayout,mainController);
		menu.setBounds(0,0,100,30);
        icon = new ImageIcon("src/Data/Images/card.jpeg");
        setLayout(null);
        
        restart = new JButton("Restart Game");
        restart.setBackground(new Color(172, 35, 40));
        restart.setForeground(Color.WHITE);
        restart.setFont(restart.getFont().deriveFont(Font.BOLD, 48)); 
        Dimension buttonSize = new Dimension(400,100);
        restart.setPreferredSize(buttonSize);
        restart.setBounds((super.screenWidth-buttonSize.width)/2, (super.screenHeight-buttonSize.height)/2, buttonSize.width, buttonSize.height);
        
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {controller.restartGame();}});
	}
	
	/**
	 * @param players list of the game players
	 * @param winTurn player that win the turn
	 * @param winGame player that win the game
	 * @param waste list of the waste cards
	 * @param turn currently turn
	 */
	public void draw(ArrayList<Player> players, Player winTurn, Player winGame, ArrayList<Card>waste, int turn) 
	{
        add(menu);
		int n = 1;
		// draw the players and their cards
		for (Player p : players)
		{
			JTextArea player = new JTextArea(p.getUsername());
			player.setBackground(new Color(3, 16, 3));
			player.setForeground(Color.WHITE);
			player.setFont(getFont().deriveFont(Font.ITALIC,18));
			player.setEditable(false);
			add(player);
			ImageIcon playerAvatar = new ImageIcon(players.get(n-1).getAvatar());
			JLabel avatar = new JLabel(playerAvatar);
        	add(avatar);
			switch (n)
			{
			case 1:
				player.setBounds(600, 100, 100, 25);
				avatar.setBounds(620, 52, 50, 50);
				break;
			case 2:
				player.setBounds(950, 350, 100, 25);
				avatar.setBounds(970, 298, 50, 50);
				break;
			case 3:
				player.setBounds(600, 550, 100, 25);
				avatar.setBounds(620, 498, 50, 50);
				break;
			case 4:
				player.setBounds(150, 350, 100, 25);
				avatar.setBounds(170, 298, 50, 50);
				break;
			}
			
			if (n == turn+1) {player.setBackground(new Color(3, 207, 3));;}
			if (players.get(turn).hasCard() && n == turn+1)
			{
				ImageIcon cardImage = new ImageIcon("src/Data/Images/Deck/" +players.get(n-1).getCard().toString()+ ".png");
				JLabel card = new JLabel(cardImage);
	        	add(card);
	        	switch (n)
				{
				case 1 -> card.setBounds(700, 50, 45, 60);
				case 2 -> card.setBounds(1050, 300, 45, 60);
				case 3 -> card.setBounds(700, 500, 45, 60);
				case 4 -> card.setBounds(250, 300, 45, 60);
				}
	        	card.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) 
		            {
		            	if (players.get(turn).getIsReal())
		            	{
		            		controller.discard(players.get(turn));
		            		controller.playAP();
		            	}
		            }});
			}
			
			for (int i = 0; i<p.getHandSize(); i++)
			{
		        ImageIcon cardImage = new ImageIcon("src/Data/Images/Deck/" +p.getCard(i).toString()+ ".png");
				JLabel card = p.getCard(i).isHole() ? new JLabel(icon) : new JLabel(cardImage);
	        	add(card);
	        	switch (n)
				{
				case 1 -> card.setBounds(430+(i*45), 130, 45, 60);
				case 2 -> card.setBounds(800+(i*45), 380, 45, 60);
				case 3 -> card.setBounds(430+(i*45), 580, 45, 60);
				case 4 -> card.setBounds(20+(i*45), 380, 45, 60);
				}
	    		if (winTurn == null && winGame == null)
	    		{card.putClientProperty("index", i+1);
	        	if (players.get(n-1).getIsReal())
	        	{
	        		card.addMouseListener(new MouseAdapter() {
			            @Override
			            public void mouseClicked(MouseEvent e) 
			            {
			            	if (players.get(turn).getIsReal() && players.get(turn).hasCard())
			            	{
			            	int ind = (int)card.getClientProperty("index");
			            	if ((players.get(turn).getCard().getRank() != 11 &&
			            			players.get(turn).getCard().getRank() != 12) &&
			            	   (players.get(turn).getCard(ind-1).isHole()) &&        	
			            	   (ind == players.get(turn).getCard().getRank() ||
			            			   players.get(turn).getCard().getRank() == 0 ||
			            			   players.get(turn).getCard().getRank() == 13))			 
			            	{controller.playRealHand(players.get(turn),ind-1);}
			            	}
			            }});
	        	}}
			}
			n++;
		}
		
		//draw the waste and the deck
		if (!waste.isEmpty()) {
	    ImageIcon wasteCard = new ImageIcon("src/Data/Images/Deck/"
		                       +controller.getWaste().get(controller.getWaste().size()-1).toString()+".png");
		JLabel labelWaste = new JLabel(wasteCard);
		add(labelWaste);
		labelWaste.setBounds(600, 380, 45, 60);
		labelWaste.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	if (players.get(turn).getIsReal())
            	{
            	if (!players.get(turn).hasCard())
            	controller.drawWaste(players.get(turn));
            	}
            }});}
		
		JLabel deck = new JLabel(icon);
		add(deck);
		deck.setBounds(650, 380, 45, 60);
		deck.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
            	if (players.get(turn).getIsReal())
            	{
            	if (!players.get(turn).hasCard())
            	controller.drawDeck(players.get(turn));
            	}
            }});
	}
	
	/**
	 * this method update the view
	 */
	@Override
	public void update(ArrayList<Player> players, Deck deck, ArrayList<Card> waste, 
			Player winTurn, Player winGame, int gameLevel, int turn)
	{
		removeAll();
		repaint();
		draw(players,winTurn,winGame,waste,turn);
		if (winGame!=null) {winGame(winGame);}
		else if (winTurn!=null) {winTurn(winTurn);}
	}
	
	/**
	 * @param p player that win the turn
	 */
	public void winTurn(Player p)
	{
		Timer timer = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) 
	        {
	        	removeAll();
	    		repaint();
	            JOptionPane.showMessageDialog(null, p.getUsername() + " go to the next level");
	            controller.restart();
	        }
	    });
		timer.setRepeats(false);
	    timer.start();
	}
	
	/**
	 * @param p player that win the game
	 */
	public void winGame(Player p)
	{
		Timer timer = new Timer(1000, new ActionListener() {
	        public void actionPerformed(ActionEvent evt) 
	        {
	        	removeAll();
	    		if (p.getIsReal())
	    		{controller.setVictory();}
	    		else
	    		{controller.setDefeat();}
	    		controller.serializePlayer();
	        	String text = p.getIsReal() ? "You Won!" : "You Lose";
	            JOptionPane.showMessageDialog(null, text);
	            add(menu);
	            add(restart);
	            repaint();
	        }
	    });
		timer.setRepeats(false);
	    timer.start();
	}

	/**
	 * @param controller to set like a game controller
	 */
	public void setController(GameController controller)
	{this.controller = controller;}

}
