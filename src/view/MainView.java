package view;
import java.awt.*;
import javax.swing.*;

import JTrash.JTrash;
import controller.*;

/**
 * @author Luca Micarelli
 * this class manage the view and instantiate and colleague 
 * all the panel classes
 */
public class MainView extends JFrame
{

	private JPanel mainPanel;
	private CardLayout cardLayout;
	private StartPanel startPanel;
	private PlayerPanel playerPanel;
	private GamePanel gamePanel;
	private MainController controller;
	
	public MainView(MainController controller)
	{
		setTitle("JTrash");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    setPreferredSize(screenSize);

	    this.controller = controller;
	    mainPanel = new JPanel();
	    cardLayout = new CardLayout();
	    mainPanel.setLayout(cardLayout);
	   
        startPanel = new StartPanel(mainPanel,cardLayout,controller);
        playerPanel = new PlayerPanel(mainPanel,cardLayout,controller);
        gamePanel = new GamePanel(mainPanel,cardLayout,controller);     

        mainPanel.add(startPanel, "StartPanel");
        mainPanel.add(playerPanel, "PlayerPanel");
        mainPanel.add(gamePanel, "GamePanel");
        
        add(mainPanel);
        pack();
        setVisible(true);
        
        AudioManager audioManager = AudioManager.getInstance();
        audioManager.play("src/Data/Music.wav");
	}
	
	/**
	 * @return the start panel
	 */
	public StartPanel getStartPanel()
	{return startPanel;}
	
	/**
	 * @return the player panel
	 */
	public PlayerPanel getPlayerPanel()
	{return playerPanel;}
	
	/**
	 * @return the game panel
	 */
	public GamePanel getGamePanel()
	{return gamePanel;}
	
	/**
	 * @param controller main controller from which it takes the 
	 * various controllers and connects them to the respective panels
	 */
	public void setController(MainController controller)
	{
		this.controller = controller;
		controller.getStartController().setPanel(startPanel);
		controller.getPlayerController().setPanel(playerPanel);
		controller.getGameController().setPanel(gamePanel);
	}
}
