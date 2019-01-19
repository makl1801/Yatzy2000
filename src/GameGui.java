import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameGui {
	GameEngine gameEngine = new GameEngine();
	// public JFrame uperFrame = new JFrame();
	// public JFrame lowerFrame = new JFrame();
	// public JFrame cheatFrame = new JFrame();

	public void prepare(ArrayList<String> playerNames) {
		gameEngine.prepareGame(playerNames);

		Gui();
	}

	public void Gui() {

		// Skapa spelfönstret och paneler för layout

		gameEngine.uperFrame.setLayout(new BoxLayout(gameEngine.uperFrame.getContentPane(), BoxLayout.Y_AXIS));
		gameEngine.uperFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameEngine.lowerFrame.setLayout(new BoxLayout(gameEngine.lowerFrame.getContentPane(), BoxLayout.Y_AXIS));
		gameEngine.lowerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameEngine.cheatFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		gameEngine.cheatFrame.add(cheats());

		// JPanel panel = new JPanel(new GridLayout(2,1));

		JPanel pointsAndPlayer = new JPanel(new GridLayout(1, 2));
		JPanel dicePanel = new JPanel();

		// Skapa panelerna, döp dem och lägg till deras muslyssnare
		JLabel dice1 = gameEngine.dice1;
		dice1.setName("dice1");
		gameEngine.mouseListenerForDiceLock(dice1);

		JLabel dice2 = gameEngine.dice2;
		dice2.setName("dice2");
		gameEngine.mouseListenerForDiceLock(dice2);

		JLabel dice3 = gameEngine.dice3;
		dice3.setName("dice3");
		gameEngine.mouseListenerForDiceLock(dice3);

		JLabel dice4 = gameEngine.dice4;
		dice4.setName("dice4");
		gameEngine.mouseListenerForDiceLock(dice4);

		JLabel dice5 = gameEngine.dice5;
		dice5.setName("dice5");
		gameEngine.mouseListenerForDiceLock(dice5);

		// Sätt tärningarnas startsidor
		gameEngine.dice1.setIcon(gameEngine.getDice(0).getDiceFace(0));
		gameEngine.dice2.setIcon(gameEngine.getDice(1).getDiceFace(1));
		gameEngine.dice3.setIcon(gameEngine.getDice(2).getDiceFace(2));
		gameEngine.dice4.setIcon(gameEngine.getDice(3).getDiceFace(3));
		gameEngine.dice5.setIcon(gameEngine.getDice(4).getDiceFace(4));

		// Sätt startvärdet på tärningarna
		gameEngine.getDice(0).setValue(1);
		gameEngine.getDice(1).setValue(2);
		gameEngine.getDice(2).setValue(3);
		gameEngine.getDice(3).setValue(4);
		gameEngine.getDice(4).setValue(5);

		// Knappen kasta och dess lyssnare
		// JButton endTurn = gameEngine.endTurnButton;
		// gameEngine.nextTurnActionListener(endTurn);
		JButton throwDice = gameEngine.throwDiceButton;

		gameEngine.throwDiceActionListener(throwDice);

		// Lägg till tärningar och knapp i dicePanelen
		dicePanel.add(gameEngine.dice1);
		dicePanel.add(gameEngine.dice2);
		dicePanel.add(gameEngine.dice3);
		dicePanel.add(gameEngine.dice4);
		dicePanel.add(gameEngine.dice5);
		dicePanel.add(gameEngine.throwDiceButton);
		// dicePanel.add(gameEngine.endTurnButton);

		pointsAndPlayer.add(pointsText());
		// pointsAndPlayer.add(pointsText());

		for (int i = 0; i < gameEngine.gameRules.getSizeOfPlayers(); i++) {
			// pointsAndPlayer.add(playerScoreCard(i));

			// gameEngine.scoreCardPanels.add(playerScoreCard(i));
			pointsAndPlayer.add(gameEngine.gameRules.getPlayer(i).getPlayerScorePanel());

			//
			// gameEngine.scoreCardPanels.add(playerScoreCard(i));
			// pointsAndPlayer.add(playerScoreCard(i));
		}

		gameEngine.uperFrame.add(pointsAndPlayer);
		gameEngine.uperFrame.pack();
		gameEngine.uperFrame.setLocationRelativeTo(null);
		// uperFrame.setLocation();
		gameEngine.uperFrame.setVisible(true);

		gameEngine.lowerFrame.add(dicePanel);
		gameEngine.lowerFrame.pack();
		gameEngine.lowerFrame.setLocationRelativeTo(gameEngine.uperFrame);
		gameEngine.lowerFrame.setLocation(gameEngine.uperFrame.getX(),
		gameEngine.uperFrame.getHeight() + gameEngine.uperFrame.getY());
		gameEngine.lowerFrame.setVisible(true);

		gameEngine.cheatFrame.pack();
		gameEngine.cheatFrame.setLocationRelativeTo(gameEngine.uperFrame);
		gameEngine.cheatFrame.setLocation(gameEngine.uperFrame.getX() + gameEngine.uperFrame.getHeight(),
		gameEngine.uperFrame.getY());
		gameEngine.cheatFrame.setVisible(true);

	}

	public JPanel pointsText() {

		JLabel empty = new JLabel();
		JLabel empty2 = new JLabel();

		JPanel pointsText = new JPanel(new GridLayout(21, 1));

		pointsText.add(empty);
		pointsText.add(empty2);

		gameEngine.scoreCardText.get(1).setText("Ettor");
		gameEngine.scoreCardText.get(2).setText("Tvåor");
		gameEngine.scoreCardText.get(3).setText("Treor");
		gameEngine.scoreCardText.get(4).setText("Fyror");
		gameEngine.scoreCardText.get(5).setText("Femmor");
		gameEngine.scoreCardText.get(6).setText("Sexor");
		gameEngine.scoreCardText.get(7).setText("Delsumma");
		gameEngine.scoreCardText.get(8).setText("Bonus");

		gameEngine.scoreCardText.get(9).setText("");

		gameEngine.scoreCardText.get(10).setText("Ett par");
		gameEngine.scoreCardText.get(11).setText("Två Par");
		gameEngine.scoreCardText.get(12).setText("Tretal");
		gameEngine.scoreCardText.get(13).setText("Fyrtal");
		gameEngine.scoreCardText.get(14).setText("Kåk");
		gameEngine.scoreCardText.get(15).setText("Liten stege");
		gameEngine.scoreCardText.get(16).setText("Stor stege");
		gameEngine.scoreCardText.get(17).setText("Chans");
		gameEngine.scoreCardText.get(18).setText("Yatzy");
		gameEngine.scoreCardText.get(19).setText("Total");

		for (int i = 1; i < 20; i++) {
			pointsText.add(gameEngine.scoreCardText.get(i));

		}

		gameEngine.placeOnesMouseListener(gameEngine.scoreCardText.get(1));
		gameEngine.placeTwosMouseListener(gameEngine.scoreCardText.get(2));
		gameEngine.placeThreesMouseListener(gameEngine.scoreCardText.get(3));
		gameEngine.placeFoursMouseListener(gameEngine.scoreCardText.get(4));
		gameEngine.placeFivesMouseListener(gameEngine.scoreCardText.get(5));
		gameEngine.placeSixesMouseListener(gameEngine.scoreCardText.get(6));
		gameEngine.placePairsMouseListener(gameEngine.scoreCardText.get(10));
		gameEngine.placeTwoPairsMouseListener(gameEngine.scoreCardText.get(11));
		gameEngine.placeThreeOfAKindMouseListener(gameEngine.scoreCardText.get(12));
		gameEngine.placeFourOfAKindMouseListener(gameEngine.scoreCardText.get(13));
		gameEngine.placeFullHousedMouseListener(gameEngine.scoreCardText.get(14));
		gameEngine.placeSmallStraightdMouseListener(gameEngine.scoreCardText.get(15));
		gameEngine.placeLargeStraightdMouseListener(gameEngine.scoreCardText.get(16));
		gameEngine.placeChanceMouseListener(gameEngine.scoreCardText.get(17));
		gameEngine.placeYahtzeeListener(gameEngine.scoreCardText.get(18));

		return pointsText;

	}

	public JPanel cheats() {

		JButton cheatButton = new JButton("FUSKA");

		JPanel cheatsPanel = new JPanel(new GridLayout(1, 6));

		cheatsPanel.add(gameEngine.dice1Cheat);
		cheatsPanel.add(gameEngine.dice2Cheat);
		cheatsPanel.add(gameEngine.dice3Cheat);
		cheatsPanel.add(gameEngine.dice4Cheat);
		cheatsPanel.add(gameEngine.dice5Cheat);

		cheatsPanel.add(cheatButton);
		gameEngine.placeCheatListener(cheatButton);

		return cheatsPanel;

	}

}
