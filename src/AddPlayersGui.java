
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddPlayersGui {
	public static ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	AddPlayersLogic addPlayersLogic = new AddPlayersLogic();

	public void addPlayersGui(int amountOfPlayers) {
		JFrame mainFrame = new JFrame();
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton spela = new JButton("Spela!");
		addPlayersLogic.spelaActionListener(spela, amountOfPlayers, textFields, mainFrame);

		mainFrame.add(addNameTextFields(amountOfPlayers));
		mainFrame.add(spela);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);

	}

	// Lägger till ett antal jlabels beroende på hur många spelare vi valde tidigare
	public JPanel addNameTextFields(int amountOfPlayers) {

		JPanel playerPanel = new JPanel(new GridLayout(amountOfPlayers, 2));
		JTextField textField = new JTextField();

		for (int i = 0; i < amountOfPlayers; i++) {
			System.out.println("Fixar");

			JLabel nameText = new JLabel("Player " + (i + 1) + " name ");
			JTextField enterName = new JTextField();
			textFields.add(enterName);
			textField.add(enterName);

			playerPanel.add(nameText);
			playerPanel.add(enterName);

		}

		return playerPanel;
	}

}
