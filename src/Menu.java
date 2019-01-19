import javax.swing.*;
import java.awt.*;

public class Menu {

	// Så jag kan komma åt logicen bakom menuGui
	MenuLogic menuLogic = new MenuLogic();

	// Bygger upp ett enkelt gui för att välja antalet spelare
	public void menuGui() {
		
		JFrame mainFrame = new JFrame();
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel(new GridLayout(1, 3));

		JLabel textLabel = new JLabel("Hur många vill spela?");
		JTextField amountOfPlayers = new JTextField();
		JButton ok = new JButton("Fortsätt");
		
		menuLogic.textFieldKeyListener(amountOfPlayers);
		menuLogic.okActionListener(ok, amountOfPlayers, mainFrame);

		mainPanel.add(textLabel);
		mainPanel.add(amountOfPlayers);
		mainPanel.add(ok);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}

}
