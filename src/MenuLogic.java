import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class MenuLogic {
	int setNumberOfPlayers;

	// Lyssnare för fortsätt knappen
	public void okActionListener(JButton ok, JTextField amountOfPlayers, JFrame mainFrame) {

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Lyssnaren sätter värdet för "setNumberOfPlayers".

				setNumberOfPlayers = (Integer.parseInt(amountOfPlayers.getText()));

				// stänger fönstert när
				closeWindow(mainFrame);

				// skickar med setNumbersOfPlayers vidare för att bygga nästa gui
				new AddPlayersGui().addPlayersGui(setNumberOfPlayers);

			}

		});

	}

	// metoden nedanför ser till att vi inte kan mata in något annat än
	// bokstäver i fältet för spelarnamn
	public void textFieldKeyListener(JTextField amountOfPlayers) {

		amountOfPlayers.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent ke) {

				char c = ke.getKeyChar();
				if ((!(Character.isDigit(c)))) {

					ke.consume();
				}

			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
			}

		});

	}

	// Stänger fönstret
	public void closeWindow(JFrame mainFrame) {

		if (setNumberOfPlayers > 0) {

			mainFrame.dispose();
		}

	}

}
