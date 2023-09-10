package view;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Luca Micarelli
 * this class manage the menu of the application 
 */
public class Menu extends JMenuBar
{

	public Menu(JPanel mainPanel, CardLayout cardLayout, MainController controller)
	{
		String rules = "1. The goal of the game is do a straight by ace to ten\n" +
				"2. If you select one level, when a player do a straight, the game\n" +
				"ends up, else the game'll continue and the player that did the\n" +
				"straight ll'have a one card less. The game continue until a one\n" +
				"player win the number of level that you have selected.\n" +
				"3. You can draw the card from the deck or the waste by clicking on it\n" +
				"and you can only put it in the position correspondent at the card value,\n" +
				"by clik on it.\n" +
				"4. When a card is discovered you can play also that and continue\n" +
				"so until you can't put the card in a regular position.\n" +
				"5. For discard you can clik on the card.\n" +
				"6. The cards from ace to ten can be put in their correspondent value.\n" +
				"7. The King and the joker can be put in any free position.\n" +
				"8. The Jack and the Queen can only be discard.\n";
        
		JMenuBar menuBar = new JMenuBar();
		JMenu menuFile = new JMenu("Settings");
		menuFile.setFont(menuFile.getFont().deriveFont(Font.BOLD, 20)); 
		JMenuItem rulesMenu = new JMenuItem("Rules");
		JMenuItem exitMenu = new JMenuItem("Exit");
		menuFile.add(rulesMenu);
		menuFile.add(exitMenu);
		menuBar.add(menuFile);
	
		rulesMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {JOptionPane.showMessageDialog(null,rules);}
        });
		
		exitMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) 
            {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the game?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) 
            {controller.exit(cardLayout, mainPanel, "StartPanel");}
            }
        });
		
		this.add(menuBar);
	}
}
