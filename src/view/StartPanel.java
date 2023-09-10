package view;
import model.*;
import controller.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Luca Micarelli
 */
public class StartPanel extends Panel
{
	
	private StartController controller;
	private MainController mainController;
	private JPanel mainPanel;
	private CardLayout cardLayout;
	private JButton startButton;
	private JButton savePlayer;
	private JButton loadPlayer;
	private String username;
	private String avatar;
	private RealPlayer realPlayer;
	private File file;
	private int index = -1;
	private Menu menu;
	private String [] avatarPaths = {
            "src/Data/Images/PlayerAvatar/avatar1.png",
            "src/Data/Images/PlayerAvatar/avatar2.png",
            "src/Data/Images/PlayerAvatar/avatar3.png",
            "src/Data/Images/PlayerAvatar/avatar4.png",
            "src/Data/Images/PlayerAvatar/avatar5.png",
            "src/Data/Images/PlayerAvatar/avatar6.png"
    };
	
	
	public StartPanel(JPanel mainPanel, CardLayout cardLayout, MainController mainController)
	{
		super("src/Data/Images/start_background.png");
		this.mainPanel = mainPanel;
		this.cardLayout = cardLayout;
		this.mainController = mainController;
		menu = new Menu(mainPanel,cardLayout,mainController);
		draw();
	}
	
	/**
	 * remove and draw the component
	 */
	public void draw() 
	{
		removeAll();
		repaint(); 
		
		startButton = new JButton("Start Game");
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(255, 130, 130));
        startButton.setFont(startButton.getFont().deriveFont(Font.BOLD, 40));
        Dimension buttonSize = new Dimension(300,100);
        startButton.setPreferredSize(buttonSize);
        startButton.setBounds((super.screenWidth-buttonSize.width)/2, 370, buttonSize.width, buttonSize.height);
        add(startButton);
        
        savePlayer = new JButton("Save Player");
        savePlayer.setForeground(Color.WHITE);
        savePlayer.setFont(savePlayer.getFont().deriveFont(Font.BOLD, 18)); 
        savePlayer.setBackground(new Color(80, 80, 255));
        Dimension savePlayerSize = new Dimension(150,50);
        savePlayer.setPreferredSize(savePlayerSize);
        savePlayer.setBounds((super.screenWidth-savePlayerSize.width)/2-120, 250, savePlayerSize.width, savePlayerSize.height);
        add(savePlayer);
        
        loadPlayer = new JButton("Load Player");
        loadPlayer.setForeground(Color.WHITE);
        loadPlayer.setFont(loadPlayer.getFont().deriveFont(Font.BOLD, 18)); 
        loadPlayer.setBackground(new Color(80, 80, 255));
        Dimension loadPlayerSize = new Dimension(150,50);
        loadPlayer.setPreferredSize(loadPlayerSize);
        loadPlayer.setBounds((super.screenWidth-loadPlayerSize.width)/2+120, 250, loadPlayerSize.width, loadPlayerSize.height);
        add(loadPlayer); 
        setLayout(null);
        
        menu.setBounds(0,0,100,30);
        add(menu);
        
        if (realPlayer!=null)
        {
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
        }
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
            	if (realPlayer == null)
            	{JOptionPane.showMessageDialog(null, "Please load an existen player or save a new one");}
            	else
                {
            		controller.start(realPlayer, file);
            		mainController.setRealPlayer(realPlayer);
            		controller.show(cardLayout, mainPanel, "PlayerPanel");
            	}
            }});
            
            savePlayer.addActionListener(new ActionListener() {
            	 @Override
                 public void actionPerformed(ActionEvent e) 
                 {savePlayer();}});
            	
            loadPlayer.addActionListener(new ActionListener() {
           	 @Override
                public void actionPerformed(ActionEvent e) 
                {loadPlayer();}});
	}
	
	/**
	 * save a new player
	 */
	public void savePlayer()
	{
		String input = JOptionPane.showInputDialog(null, "Username:");
		username = input;
		
		JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Allineamento al centro con margine di 10 pixel

        ButtonGroup buttonGroup = new ButtonGroup();
        for (int x = 0; x < avatarPaths.length; x++) {
        	int k = x;
            String path = avatarPaths[x];
            ImageIcon imageIcon = new ImageIcon(path);
            JLabel label = new JLabel(imageIcon);
            JRadioButton radioButton = new JRadioButton();
            radioButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (radioButton.isSelected()) {
                        index = k;
                    }
                }
            });

            panel.add(label);
            panel.add(radioButton);
            buttonGroup.add(radioButton);
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JPanel scrollSupportPanel = new JPanel();
        scrollSupportPanel.setLayout(new BorderLayout());
        scrollSupportPanel.add(scrollPane, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(null, scrollSupportPanel, "Select Avatar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            if (index != -1)avatar = avatarPaths[index];
        } else if (result == JOptionPane.CANCEL_OPTION) {
            System.out.println("Selection Cancelled");
        }
        if (username != null && avatar != null) {
	        RealPlayer realPlayer = new RealPlayer(username, avatar); 
	        saveFile(realPlayer);}
	}
	
	/**
	 * load an existent player 
	 */
	public void loadPlayer()
	{
		JFileChooser fileChooser = new JFileChooser();
    	fileChooser.setCurrentDirectory(new File("src/Data/Users"));
    	fileChooser.setDialogTitle("Select a file that contain a player");

    	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text File", "txt");
    	fileChooser.setFileFilter(filter);

    	int result = fileChooser.showOpenDialog(null);

    	if (result == JFileChooser.APPROVE_OPTION) 
    	{
    		file = fileChooser.getSelectedFile();
    	    try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
    	        realPlayer = (RealPlayer) inputStream.readObject();
    	    } catch (IOException | ClassNotFoundException er) {
    	        er.printStackTrace();
    	    }
    	}
    	draw();
	}
	
	/**
	 * @param player to serialize and save in a new file
	 */
	private void saveFile(RealPlayer player) {
	    String fileName = JOptionPane.showInputDialog(null, "File name:");
	    realPlayer = player;

	    if (fileName != null && !fileName.isEmpty()) {
	        String path = "src/Data/Users/" + fileName + ".txt";
	        file = new File(path);

	        while (file.exists()) {
	        	fileName = JOptionPane.showInputDialog(null, "The file already exists enter a new one:");
	            path = "src/Data/Users/" + fileName + ".txt";
	            file = new File(path);

	            if (fileName == null || fileName.isEmpty()) 
	            {return;}
	        }
	        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
	            outputStream.writeObject(player);
	            JOptionPane.showMessageDialog(null, "File successfully saved!");
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error while saving.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    }
    	draw();
	}
	
	/**
	 * @param username to set 
	 */
	public void setUsername(String username)
	{this.username = username;}
	
	/**
	 * @return the user name
	 */
	public String getUsername()
	{return username;}
	
	/**
	 * @param player to set like a real player
	 */
	public void setRealPlayer(RealPlayer player)
	{realPlayer = player;}
	
	/**
	 * @return a real player
	 */
	public RealPlayer getRealPlayer()
	{return realPlayer;}
	
	/**
	 * @param controller to set like a controller of the panels
	 */
	public void setController(StartController controller)
	{this.controller = controller;}
}