package view;
import controller.*;
import model.RealPlayer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
/**
 * @author Luca Micarelli
 * this class manage the form of the application
 */
public class PlayerPanel extends Panel
{

	private JButton submit;
	private ButtonGroup buttonGroup;
	private JRadioButton radioButton1;
	private JRadioButton radioButton2;
	private JRadioButton radioButton3;
	private JComboBox<Integer> comboBox;
	private PlayerController controller;
	private MainController mainController;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private RealPlayer realPlayer;
	private Menu menu;
	
	public PlayerPanel(JPanel mainPanel, CardLayout cardLayout, MainController mainController)
	{
		super("src/Data/Images/game_background.jpg");
		this.mainController = mainController;
		this.cardLayout = cardLayout;
		this.mainPanel = mainPanel;
		menu = new Menu(mainPanel,cardLayout,mainController);
	}
	
	/**
	 * remove and draw the component
	 */
	public void draw()
	{
		removeAll();
		repaint(); 
		
		submit = new JButton("Submit");
		submit.setForeground(Color.WHITE);
		submit.setBackground(new Color(255,165,0));
		submit.setFont(submit.getFont().deriveFont(Font.BOLD, 32));
		
		menu.setBounds(0,0,100,30);
        add(menu);
		
        JLabel playerText = new JLabel("Select the players number");
        playerText.setForeground(Color.WHITE);
		playerText.setFont(playerText.getFont().deriveFont(Font.BOLD, 22));
		
		JLabel levelText = new JLabel("Select the levels number");
		levelText.setForeground(Color.WHITE);
		levelText.setFont(playerText.getFont().deriveFont(Font.BOLD, 22));
        
        radioButton1 = new JRadioButton("1 player");
        radioButton1.setBackground(new Color(245,245,220));
        radioButton1.setForeground(Color.BLACK);
        radioButton1.setFont(getFont().deriveFont(Font.BOLD,16));
        
        radioButton2 = new JRadioButton("2 players");
        radioButton2.setBackground(new Color(245,245,220));
        radioButton2.setForeground(Color.BLACK);
        radioButton2.setFont(getFont().deriveFont(Font.BOLD,16));
        
        radioButton3 = new JRadioButton("3 players");
        radioButton3.setBackground(new Color(245,245,220));
        radioButton3.setForeground(Color.BLACK);
        radioButton3.setFont(getFont().deriveFont(Font.BOLD,16));
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        
        add(radioButton1);
        add(radioButton2);
        add(radioButton3);
		add(submit);
		add(playerText);
		add(levelText);
		
		int width = (super.screenWidth-250)/2;
		setLayout(null);
		playerText.setBounds(width-10, 50, 300, 50);
        radioButton1.setBounds(width, 100, 250, 50);
        radioButton2.setBounds(width, 150, 250, 50);
        radioButton3.setBounds(width, 200, 250, 50);
        submit.setBounds((super.screenWidth-200)/2, 550, 200, 80); 
        
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        comboBox = new JComboBox<>(numbers);
        comboBox.setBackground(new Color(245,245,220));
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(getFont().deriveFont(Font.BOLD,16));
        add(comboBox);
        comboBox.setBounds(width, 390, 250, 50);
        levelText.setBounds(width-5, 340, 300, 50);
        
        ImageIcon image = new ImageIcon(realPlayer.getAvatar());
        JLabel avatarLabel = new JLabel(image);
        avatarLabel.setBounds(50, 110, 50, 50);
        add(avatarLabel);
        
        JLabel nameLabel = new JLabel("Username: " + realPlayer.getUsername());
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 24));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(50, 150, 400, 100);
        add(nameLabel);
        
        JLabel winLabel = new JLabel("Won Game: " + realPlayer.getGamesWon());
        winLabel.setFont(winLabel.getFont().deriveFont(Font.BOLD, 24));
        winLabel.setForeground(Color.WHITE);
        winLabel.setBounds(50, 200, 400, 100);
        add(winLabel);
        
        JLabel loseLabel = new JLabel("Lose Game: " + realPlayer.getGamesLost());
        loseLabel.setFont(loseLabel.getFont().deriveFont(Font.BOLD, 24));
        loseLabel.setForeground(Color.WHITE);
        loseLabel.setBounds(50, 250, 400, 100);
        add(loseLabel);
        
        JLabel scoreLabel = new JLabel("Score: " + realPlayer.getScore());
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(50, 300, 400, 100);
        add(scoreLabel);
        
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	int level = (int) comboBox.getSelectedItem();
            	if (radioButton1.isSelected()) {
                    controller.playerInstance(1,level);
                    controller.show(cardLayout, mainPanel, "GamePanel");
                } else if (radioButton2.isSelected()) {
                    controller.playerInstance(2,level);
                    controller.show(cardLayout, mainPanel, "GamePanel");
                } else if (radioButton3.isSelected()) {
                   controller.playerInstance(3,level);;
                   controller.show(cardLayout, mainPanel, "GamePanel");
                }
                else
                {JOptionPane.showMessageDialog(null, "Please select a number of player");}
            }});
	}
	
	/**
	 * @param controller to set like a controller of the class
	 */
	public void setController(PlayerController controller)
	{this.controller = controller;}
	
	/**
	 * @param player to set like a real player of the class
	 */
	public void setRealPlayer(RealPlayer player)
	{this.realPlayer = player;}
}
