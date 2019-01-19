import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GameEngine {
	ArrayList<JPanel> scoreCardPanels = new ArrayList<JPanel>();
	ArrayList<JLabel> scoreCardText = new ArrayList<JLabel>();
	public JFrame uperFrame = new JFrame();
	public JFrame lowerFrame = new JFrame();
	public JFrame cheatFrame = new JFrame();
	// private ArrayList<Dice> dices = new ArrayList<Dice>();
	GameRules gameRules = new GameRules();

	boolean havePlacedPointsThisTurn = false;
	boolean lockLabelsBeforeThrow = true;
	boolean lockDiceButtons = true;
	boolean lockDicesAtStart = true;

	int activePlayer = 0;

	int numberOfClicks = 3;
	int firstTimeClicked = 0;

	int turnsLeft = 15;

	JLabel dice1 = new JLabel();
	JLabel dice2 = new JLabel();
	JLabel dice3 = new JLabel();
	JLabel dice4 = new JLabel();
	JLabel dice5 = new JLabel();

	JButton throwDiceButton = new JButton();

	JButton endTurnButton = new JButton("Avsluta tur");

	JTextField dice1Cheat = new JTextField();
	JTextField dice2Cheat = new JTextField();
	JTextField dice3Cheat = new JTextField();
	JTextField dice4Cheat = new JTextField();
	JTextField dice5Cheat = new JTextField();

	public void prepareGame(ArrayList<String> playerNames) {
		throwDiceButton.setPreferredSize(new Dimension(110, 100));
		addJlabelsToScoreCardTextArraylist();
		// nameJlabels();
		gameRules.addDicesToGame();
		gameRules.setUpPlayers(playerNames);
		// gameRules.setUpTestPlayers();
		gameRules.fillScoreBoardInPlayer();
		addImages();
		// gameRules.newTurn();
		// throwDice.setEnabled(false);
		// setButton("Start");
		setAllJlabelsToFalse();
		endTurnButton.setEnabled(false);
		gameRules.fillDiceValuesWithZeros();
		gameRules.setUpHashMap();
		gameRules.getPlayer(activePlayer).highlightPlayer();

	}

	public void addJlabelsToScoreCardTextArraylist() {
		Border border = LineBorder.createBlackLineBorder();

		for (int i = 0; i < 20; i++) {

			JLabel scoreCardTextJlabel = new JLabel();
			scoreCardTextJlabel.setOpaque(true);
			scoreCardTextJlabel.setBackground(Color.WHITE);
			scoreCardTextJlabel.setBorder(border);
			scoreCardText.add(scoreCardTextJlabel);
		}

	}

	public void setButton(String buttonText) {

		throwDiceButton.setText(buttonText);

	}

	// public void nameJlabels() {
	//// dice1.setName("dice1");
	//// dice2.setName("dice2");
	//// dice3.setName("dice3");
	//// dice4.setName("dice4");
	//// dice5.setName("dice5");
	// }

	public void addImages() {
		// Hämta in bilderna och gör dem till ImageIcons
		ImageIcon face1 = new ImageIcon("images/1.png");
		ImageIcon face2 = new ImageIcon("images/2.png");
		ImageIcon face3 = new ImageIcon("images/3.png");
		ImageIcon face4 = new ImageIcon("images/4.png");
		ImageIcon face5 = new ImageIcon("images/5.png");
		ImageIcon face6 = new ImageIcon("images/6.png");

		for (int i = 0; i < gameRules.getSizeOfDices(); i++) {

			// System.out.println("Ny tärningar får bilder");
			gameRules.getDice(i).addImages(0, face1);
			gameRules.getDice(i).addImages(1, face2);
			gameRules.getDice(i).addImages(2, face3);
			gameRules.getDice(i).addImages(3, face4);
			gameRules.getDice(i).addImages(4, face5);
			gameRules.getDice(i).addImages(5, face6);

		}
	}

	public Dice getDice(int n) {

		return gameRules.getDice(n);

	}

	public void mouseListenerForDiceLock(JLabel diceLabel) {

		diceLabel.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (lockDicesAtStart == false) {

					if (lockDiceButtons == false) {

						// if(diceLabel.getName().equals("dice1") &&
						// dices.get(0).getLockedStatus() == false){
						if (diceLabel.getName().equals("dice1") && gameRules.getDice(0).getLockedStatus() == false) {
							dice1.setEnabled(false);
							gameRules.getDice(0).setLockedStatus();

						} else if (diceLabel.getName().equals("dice1")
								&& gameRules.getDice(0).getLockedStatus() == true) {
							dice1.setEnabled(true);
							gameRules.getDice(0).setLockedStatus();
							// dices.get(0).setLockedStatus();

						}

						if (diceLabel.getName().equals("dice2") && gameRules.getDice(1).getLockedStatus() == false) {
							dice2.setEnabled(false);

							gameRules.getDice(1).setLockedStatus();

						} else if (diceLabel.getName().equals("dice2")
								&& gameRules.getDice(1).getLockedStatus() == true) {
							dice2.setEnabled(true);
							gameRules.getDice(1).setLockedStatus();

						}

						if (diceLabel.getName().equals("dice3") && gameRules.getDice(2).getLockedStatus() == false) {
							dice3.setEnabled(false);
							gameRules.getDice(2).setLockedStatus();

						} else if (diceLabel.getName().equals("dice3")
								&& gameRules.getDice(2).getLockedStatus() == true) {
							dice3.setEnabled(true);
							gameRules.getDice(2).setLockedStatus();

						}

						if (diceLabel.getName().equals("dice4") && gameRules.getDice(3).getLockedStatus() == false) {
							dice4.setEnabled(false);
							gameRules.getDice(3).setLockedStatus();

						} else if (diceLabel.getName().equals("dice4")
								&& gameRules.getDice(3).getLockedStatus() == true) {
							dice4.setEnabled(true);
							gameRules.getDice(3).setLockedStatus();

						}

						if (diceLabel.getName().equals("dice5") && gameRules.getDice(4).getLockedStatus() == false) {
							dice5.setEnabled(false);
							gameRules.getDice(4).setLockedStatus();

						} else if (diceLabel.getName().equals("dice5")
								&& gameRules.getDice(4).getLockedStatus() == true) {
							dice5.setEnabled(true);
							gameRules.getDice(4).setLockedStatus();
						}
					}
				}
			}

		});

	}

	public void endTurnAfterPlacingPoints() {
		if (havePlacedPointsThisTurn == true) {
			if (activePlayer == gameRules.getSizeOfPlayers() - 1) {
				turnsLeft--;
				System.out.println("Antal omgånger kvar " + turnsLeft);
				if (turnsLeft == 0) {

					System.out.println("SLUT");
					gameRules.checkForWinner();
					closeFrames();

				}

				gameRules.getPlayer(activePlayer).lowLightPlayer();
				numberOfClicks = 3;
				activePlayer = 0;

				setButton("<html>" + gameRules.getPlayer(activePlayer).getName() + " har " + "<br /> " + numberOfClicks
						+ " slag kvar " + "</html>");
				throwDiceButton.setEnabled(true);
				numberOfClicks = 3;
				lockDicesAtStart = true;
				dice1.setEnabled(true);
				gameRules.getDice(0).unLockDice();
				dice2.setEnabled(true);
				gameRules.getDice(1).unLockDice();
				dice3.setEnabled(true);
				gameRules.getDice(2).unLockDice();
				dice4.setEnabled(true);
				gameRules.getDice(3).unLockDice();
				dice5.setEnabled(true);
				gameRules.getDice(4).unLockDice();

				setAllJlabelsToFalse();
				havePlacedPointsThisTurn = false;

				gameRules.getPlayer(activePlayer).highlightPlayer();

			} else if (activePlayer < gameRules.getSizeOfPlayers() - 1) {

				throwDiceButton.setEnabled(true);

				numberOfClicks = 3;
				activePlayer++;

				setButton("<html>" + gameRules.getPlayer(activePlayer).getName() + " har " + "<br /> " + numberOfClicks
						+ " slag kvar " + "</html>");

				lockDicesAtStart = true;

				dice1.setEnabled(true);
				gameRules.getDice(0).unLockDice();
				dice2.setEnabled(true);
				gameRules.getDice(1).unLockDice();
				dice3.setEnabled(true);
				gameRules.getDice(2).unLockDice();
				dice4.setEnabled(true);
				gameRules.getDice(3).unLockDice();
				dice5.setEnabled(true);
				gameRules.getDice(4).unLockDice();

				setAllJlabelsToFalse();
				havePlacedPointsThisTurn = false;

				gameRules.getPlayer(activePlayer).highlightPlayer();
				gameRules.getPlayer(activePlayer - 1).lowLightPlayer();

			}
		}

	}

	public void throwDiceActionListener(JButton throwDice) {

		setButton("<html>" + gameRules.getPlayer(activePlayer).getName() + " har " + "<br /> " + numberOfClicks
				+ " slag kvar " + "</html>");

		throwDice.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				lockLabelsBeforeThrow = false;

				checkUsedJlabels();

				numberOfClicks--;
				System.out.println("Antalet klick " + numberOfClicks);
				System.out.println("activePlayer är " + activePlayer);

				// gameRules.getActivePlayer().useAThrow();

				Timer timer = new Timer(10, new ActionListener() {

					private int counter;

					public void actionPerformed(ActionEvent e) {
						setButton("<html>" + gameRules.getPlayer(activePlayer).getName() + " har " + "<br /> "
								+ numberOfClicks + " slag kvar " + "</html>");

						lockDicesAtStart = false;
						if (counter < 20) {
							lockDiceButtons = true;
							throwDice.setEnabled(false);
							counter++;
							if (getDice(0).getLockedStatus() == false) {

								int diceRoll1 = getDice(0).rollDice() - 1;
								dice1.setIcon(getDice(0).getDiceFace(diceRoll1));
								gameRules.setDiceValues(0, diceRoll1 + 1);

							}
							if (getDice(1).getLockedStatus() == false) {
								int diceRoll2 = getDice(0).rollDice() - 1;
								dice2.setIcon(getDice(1).getDiceFace(diceRoll2));
								gameRules.setDiceValues(1, diceRoll2 + 1);

							}
							if (getDice(2).getLockedStatus() == false) {
								int diceRoll3 = getDice(0).rollDice() - 1;
								dice3.setIcon(getDice(2).getDiceFace(diceRoll3));
								gameRules.setDiceValues(2, diceRoll3 + 1);

							}
							if (getDice(3).getLockedStatus() == false) {
								int diceRoll4 = getDice(0).rollDice() - 1;
								dice4.setIcon(getDice(3).getDiceFace(diceRoll4));
								gameRules.setDiceValues(3, diceRoll4 + 1);

							}
							if (getDice(4).getLockedStatus() == false) {
								int diceRoll5 = getDice(0).rollDice() - 1;
								dice5.setIcon(getDice(4).getDiceFace(diceRoll5));
								gameRules.setDiceValues(4, diceRoll5 + 1);

							}

						} else {

							System.out.println(gameRules.getDiceValuesArray());
							throwDice.setEnabled(true);
							lockDiceButtons = false;
							gameRules.addDiceValuesToDiceHashMap();
							((Timer) e.getSource()).stop();
							// gameRules.addDiceValuesToDiceHashMap();

							if (numberOfClicks < 1) {

								throwDice.setEnabled(false);

							}
						}

					}

				});

				timer.start();

			}
		});

	}

	public void placeOnesMouseListener(JLabel ones) {

		ones.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(1).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(1).getText().isEmpty()) {
					placeNumbers(1);

					scoreCardText.get(1).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeTwosMouseListener(JLabel twos) {

		twos.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(2).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(2).getText().isEmpty()) {
					placeNumbers(2);
					scoreCardText.get(2).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}
			}

		});
	}

	public void placeThreesMouseListener(JLabel threes) {

		threes.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(3).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(3).getText().isEmpty()) {
					placeNumbers(3);
					scoreCardText.get(3).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}
			}

		});
	}

	public void placeFoursMouseListener(JLabel fours) {

		fours.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(4).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(4).getText().isEmpty()) {
					placeNumbers(4);
					scoreCardText.get(4).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}
			}

		});
	}

	public void placeFivesMouseListener(JLabel fives) {

		fives.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(5).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(5).getText().isEmpty()) {
					placeNumbers(5);
					scoreCardText.get(5).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}
			}

		});
	}

	public void placeSixesMouseListener(JLabel sixes) {

		sixes.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(6).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(6).getText().isEmpty()) {
					placeNumbers(6);
					scoreCardText.get(6).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}
			}

		});
	}

	public void placePairsMouseListener(JLabel pairs) {

		pairs.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(10).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(10).getText().isEmpty()) {
					placeOnePair();

					scoreCardText.get(10).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeTwoPairsMouseListener(JLabel twoPairs) {

		twoPairs.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(11).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(11).getText().isEmpty()) {
					placeTwoPairs();

					scoreCardText.get(11).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeThreeOfAKindMouseListener(JLabel threeOfAKind) {

		threeOfAKind.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(12).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(12).getText().isEmpty()) {
					placeThreeOfAKind();

					scoreCardText.get(12).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeFourOfAKindMouseListener(JLabel fourOfAKind) {

		fourOfAKind.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(13).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(13).getText().isEmpty()) {
					placeFourOfAKind();

					scoreCardText.get(13).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeFullHousedMouseListener(JLabel fullHouse) {

		fullHouse.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(14).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(14).getText().isEmpty()) {
					placeFullHouse();

					scoreCardText.get(14).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeSmallStraightdMouseListener(JLabel smallStraight) {

		smallStraight.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(15).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(15).getText().isEmpty()) {
					placeSmallStraight();

					scoreCardText.get(15).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeLargeStraightdMouseListener(JLabel largeStraight) {

		largeStraight.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(16).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(16).getText().isEmpty()) {
					placeLargeStraight();

					scoreCardText.get(16).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeChanceMouseListener(JLabel chance) {

		chance.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(17).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(17).getText().isEmpty()) {
					placeChance();

					scoreCardText.get(17).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeYahtzeeListener(JLabel yahtzee) {

		yahtzee.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				if (lockLabelsBeforeThrow == true
						|| !gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(18).getText().isEmpty()) {

					e.consume();

				} else if (havePlacedPointsThisTurn == false
						|| gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(18).getText().isEmpty()) {
					placeYahtzee();

					scoreCardText.get(18).setBackground(Color.DARK_GRAY);

				} else {

					e.consume();

				}

			}

		});

	}

	public void placeNumbers(int whatNumberToCheck) {

		gameRules.getPlayer(activePlayer).addScoreToList(whatNumberToCheck,
				gameRules.checkNumberPoints(whatNumberToCheck));

		gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(whatNumberToCheck)
				.setText(Integer.toString(gameRules.getPlayer(activePlayer).getScore(whatNumberToCheck)));

		gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(7)
				.setText(Integer.toString(gameRules.checkTopScore(activePlayer)));

		gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(8)
				.setText(Integer.toString(gameRules.checkForBonus(activePlayer)));

		gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(19)
				.setText(Integer.toString(gameRules.checkForGrandTotal(activePlayer)));

		havePlacedPointsThisTurn = true;
		lockLabelsBeforeThrow = true;

		setAllJlabelsToFalse();

		recetDicesAndButtons();

		endTurnAfterPlacingPoints();

	}

	public void placeOnePair() {

		gameRules.getPlayer(activePlayer).addScoreToList(10, gameRules.checkForOnePair());
		finishUpTurn(10);

	}

	public void placeTwoPairs() {

		gameRules.getPlayer(activePlayer).addScoreToList(11, gameRules.checkForTwoPairs());
		finishUpTurn(11);

	}

	public void placeThreeOfAKind() {

		gameRules.getPlayer(activePlayer).addScoreToList(12, gameRules.checkForThreeOfAKind());
		finishUpTurn(12);

	}

	public void placeFourOfAKind() {

		gameRules.getPlayer(activePlayer).addScoreToList(13, gameRules.checkForFourOfAKind());
		finishUpTurn(13);

	}

	public void placeFullHouse() {

		gameRules.getPlayer(activePlayer).addScoreToList(14, gameRules.checkForFullHouse());
		finishUpTurn(14);

	}

	public void placeSmallStraight() {

		gameRules.getPlayer(activePlayer).addScoreToList(15, gameRules.checkForSmallStraight());
		finishUpTurn(15);

	}

	public void placeLargeStraight() {

		gameRules.getPlayer(activePlayer).addScoreToList(16, gameRules.checkForLargeStraight());
		finishUpTurn(16);

	}

	public void placeChance() {

		gameRules.getPlayer(activePlayer).addScoreToList(17, gameRules.checkForChance());
		finishUpTurn(17);

	}

	public void placeYahtzee() {

		gameRules.getPlayer(activePlayer).addScoreToList(18, gameRules.checkForYahtzee());

		finishUpTurn(18);

	}

	public void finishUpTurn(int i) {

		gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(i)
				.setText(Integer.toString(gameRules.getPlayer(activePlayer).getScore(i)));

		gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(19)
				.setText(Integer.toString(gameRules.checkForGrandTotal(activePlayer)));

		havePlacedPointsThisTurn = true;
		lockLabelsBeforeThrow = true;

		setAllJlabelsToFalse();

		recetDicesAndButtons();
		endTurnAfterPlacingPoints();

	}

	public void recetDicesAndButtons() {

		throwDiceButton.setEnabled(false);
		endTurnButton.setEnabled(true);

		dice1.setEnabled(true);
		gameRules.getDice(0).unLockDice();
		dice2.setEnabled(true);
		gameRules.getDice(1).unLockDice();
		dice3.setEnabled(true);
		gameRules.getDice(2).unLockDice();
		dice4.setEnabled(true);
		gameRules.getDice(3).unLockDice();
		dice5.setEnabled(true);
		gameRules.getDice(4).unLockDice();

	}

	public void setAllJlabelsToFalse() {

		for (int i = 0; i < scoreCardText.size(); i++) {

			scoreCardText.get(i).setEnabled(false);
			scoreCardText.get(i).setBackground(null);
		}

	}

	public void checkUsedJlabels() {

		for (int i = 0; i < scoreCardText.size(); i++) {

			if (!gameRules.getPlayer(activePlayer).getPointsJlabelFromPlayer(i).getText().isEmpty()) {

				scoreCardText.get(i).setEnabled(false);
				scoreCardText.get(i).setBackground(Color.DARK_GRAY);

			}

			else {

				scoreCardText.get(i).setEnabled(true);
				scoreCardText.get(i).setBackground(Color.WHITE);

			}

			scoreCardText.get(7).setBackground(null);
			scoreCardText.get(8).setBackground(null);
			scoreCardText.get(9).setBackground(null);
			scoreCardText.get(19).setBackground(null);

			scoreCardText.get(7).setEnabled(false);
			scoreCardText.get(8).setEnabled(false);
			scoreCardText.get(9).setEnabled(false);
			scoreCardText.get(19).setEnabled(false);
		}

	}

	public void lightUpActivePlayerScoreJlabels() {

		for (int i = 0; i < 20; i++) {
			gameRules.getPlayer(activePlayer).getJLabel(i).setBackground(Color.WHITE);
		}

	}

	// Fuska

	public void placeCheatListener(JButton cheatButton) {

		cheatButton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {

				gameRules.cheatDiceValues(0, Integer.parseInt(dice1Cheat.getText()));
				gameRules.cheatDiceValues(1, Integer.parseInt(dice2Cheat.getText()));
				gameRules.cheatDiceValues(2, Integer.parseInt(dice3Cheat.getText()));
				gameRules.cheatDiceValues(3, Integer.parseInt(dice4Cheat.getText()));
				gameRules.cheatDiceValues(4, Integer.parseInt(dice5Cheat.getText()));

				System.out.println(gameRules.getDiceValuesArray());

			}

		});

	}

	public void closeFrames() {

		uperFrame.dispose();
		lowerFrame.dispose();
		cheatFrame.dispose();

	}

}
