import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;


public class GameRules {
	private ArrayList<Dice> dices = new ArrayList<Dice>();
	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Integer> diceValues = new ArrayList<Integer>();
	HashMap<Integer, Integer> diceHash = new HashMap<Integer, Integer>();

	public void setUpHashMap() {

		diceHash.put(1, 0);
		diceHash.put(2, 0);
		diceHash.put(3, 0);
		diceHash.put(4, 0);
		diceHash.put(5, 0);
		diceHash.put(6, 0);

		System.out.println(diceHash);

	}

	public void checkForWinner() {

		for (int i = 0; i < players.size(); i++) {
			players.get(i).setPlayerTotalScore();
			System.out.println(players.get(i).getName() + " Fick " + players.get(i).getTotalScore() + " poäng!");
		}
		Collections.sort(players);
		// Collections.reverse(players);

		for (int i = 0; i < players.size(); i++) {
			System.out.println(players.get(i).getName() + " Fick " + players.get(i).getTotalScore() + " poäng!");
		}

		new GameEnd().gameEnd(players);
		
		
	}

	public void setUpPlayers(ArrayList<String> playerNames) {

		for (int i = 0; i < playerNames.size(); i++) {

			players.add(new Player(playerNames.get(i)));

		}
		System.out.println("i setup players. storlek på playerNames: " + playerNames.size());
		System.out.println("i setup players. storlek på players: " + players.size());
		fillPlayerScoresWithZeros();

	}

	public void fillPlayerScoresWithZeros() {
		for (int n = 0; n < players.size(); n++) {

			for (int i = 0; i < 20; i++) {

				players.get(n).fillScoreArrayWithZeros(i, 0);

			}
		}

	}

	public void fillScoreBoardInPlayer() {

		for (int n = 0; n < players.size(); n++) {

			players.get(n).addLabelToScoreBoard();

		}

	}

	public void addDicesToGame() {
		dices.add(0, new Dice());
		dices.add(1, new Dice());
		dices.add(2, new Dice());
		dices.add(3, new Dice());
		dices.add(4, new Dice());
		System.out.println("antal tärningar " + dices.size());

	}

	public void fillDiceValuesWithZeros() {

		for (int i = 0; i < 5; i++) {

			diceValues.add(0);

		}

	}


	public void addDiceValuesToDiceHashMap() {

		int one = 0;
		int two = 0;
		int three = 0;
		int four = 0;
		int five = 0;
		int six = 0;

		for (int i = 0; i < diceValues.size(); i++) {
			if (diceValues.get(i) == 1) {
				one++;
			}
			if (diceValues.get(i) == 2) {
				two++;
			}
			if (diceValues.get(i) == 3) {
				three++;
			}
			if (diceValues.get(i) == 4) {
				four++;
			}
			if (diceValues.get(i) == 5) {
				five++;
			}
			if (diceValues.get(i) == 6) {
				six++;
			}

		}
		diceHash.put(1, one);
		diceHash.put(2, two);
		diceHash.put(3, three);
		diceHash.put(4, four);
		diceHash.put(5, five);
		diceHash.put(6, six);

		System.out.println("diceHash är" + diceHash);

	}

	public void setDiceValues(int index, int value) {

		diceValues.set(index, value);

	}

	public ArrayList<Integer> getDiceValuesArray() {

		return diceValues;

	}

	public Dice getDice(int n) {
		return dices.get(n);

	}

	public int getSizeOfDices() {
		return dices.size();

	}

	public int getSizeOfPlayers() {
		return players.size();
	}

	public Player getPlayer(int n) {

		return players.get(n);
	}

	public int checkNumberPoints(int n) {

		int numberOfNumbers = 0;

		for (int i = 0; i < diceValues.size(); i++) {

			if (n == diceValues.get(i)) {

				numberOfNumbers++;
			}

		}

		return numberOfNumbers * n;

	}

	public int checkForOnePair() {

		int pair = 0;

		for (int i = 0; i < diceValues.size(); i++) {

			for (int j = i + 1; j < diceValues.size(); j++) {

				if (diceValues.get(i) == diceValues.get(j) && diceValues.get(i) > 0 && diceValues.get(j) > 0) {

					if (diceValues.get(i) > pair) {

						pair = diceValues.get(i);

					}
					diceValues.set(i, 0);
					diceValues.set(j, 0);

				}

			}

		}

		return pair * 2;

	}

	public int checkForTwoPairs() {

		int pair1 = 0;
		int pair2 = 0;

		for (int i = 0; i < diceValues.size(); i++) {

			for (int j = i + 1; j < diceValues.size(); j++) {

				if (diceValues.get(i) == diceValues.get(j) && diceValues.get(i) > 0 && diceValues.get(j) > 0
						&& pair1 == 0) {

					pair1 = diceValues.get(i);

					diceValues.set(i, 0);
					diceValues.set(j, 0);

				}

				if (diceValues.get(i) == diceValues.get(j) && diceValues.get(i) > 0 && diceValues.get(j) > 0
						&& pair1 > 0) {

					pair2 = diceValues.get(i);

					diceValues.set(i, 0);
					diceValues.set(j, 0);

				}

			}

		}

		if (pair1 > 0 && pair2 > 0) {

			return (pair1 * 2) + (pair2 * 2);
		} else {

			return 0;
		}

	}

	public int checkForThreeOfAKind() {

		for (Integer i : diceHash.keySet()) {
			if (diceHash.get(i) > 2) {

				return i * 3;

			}
		}

		return 0;

	}

	public int checkForFourOfAKind() {

		for (Integer i : diceHash.keySet()) {
			if (diceHash.get(i) > 3) {

				return i * 4;

			}
		}

		return 0;

	}

	public int checkForFullHouse() {
		int tretal = 0;
		int pair = 0;

		for (Integer i : diceHash.keySet()) {
			if (diceHash.get(i) == 3) {

				tretal = i * 3;

			}
		}
		for (Integer i : diceHash.keySet()) {
			if (diceHash.get(i) == 2) {

				pair = i * 2;

			}
		}
		if (tretal > 0 && pair > 0) {
			return tretal + pair;

		} else {
			return 0;

		}

	}

	public int checkForSmallStraight() {

		int ones = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;

		for (int i = 0; i < diceValues.size(); i++) {

			if (diceValues.get(i) == 1) {

				ones++;

			}
			if (diceValues.get(i) == 2) {

				twos++;

			}
			if (diceValues.get(i) == 3) {

				threes++;

			}
			if (diceValues.get(i) == 4) {

				fours++;

			}
			if (diceValues.get(i) == 5) {

				fives++;

			}
		}

		if (ones == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1) {

			return 15;
		} else {

			return 0;

		}

	}

	public int checkForLargeStraight() {

		int twos = 0;
		int threes = 0;
		int fours = 0;
		int fives = 0;
		int sixes = 0;

		for (int i = 0; i < diceValues.size(); i++) {

			if (diceValues.get(i) == 6) {

				sixes++;

			}
			if (diceValues.get(i) == 2) {

				twos++;

			}
			if (diceValues.get(i) == 3) {

				threes++;

			}
			if (diceValues.get(i) == 4) {

				fours++;

			}
			if (diceValues.get(i) == 5) {

				fives++;

			}
		}

		if (sixes == 1 && twos == 1 && threes == 1 && fours == 1 && fives == 1) {

			return 20;
		} else {

			return 0;

		}

	}

	public int checkForChance() {

		int chance = 0;

		for (int i = 0; i < diceValues.size(); i++) {

			chance += diceValues.get(i);

		}

		return chance;

	}

	public void cheatDiceValues(int i, int j) {

		diceValues.set(i, j);
		addDiceValuesToDiceHashMap();

	}

	public int checkForYahtzee() {

		for (Integer i : diceHash.keySet()) {
			if (diceHash.get(i) == 5) {

				return 50;

			}
		}

		return 0;

	}

	public int checkTopScore(int player) {

		int delsumma = getPlayer(player).getScore(1) + getPlayer(player).getScore(2) + getPlayer(player).getScore(3)
				+ getPlayer(player).getScore(4) + getPlayer(player).getScore(5) + getPlayer(player).getScore(6);

		System.out.println("");

		return delsumma;

	}

	public int checkForGrandTotal(int player) {
		int grandTotal = 0;

		for (int i = 1; i < 20; i++) {

			grandTotal += getPlayer(player).getScore(i);

		}
		return grandTotal;

	}

	public int checkForBonus(int player) {

		System.out.println("Kollar efter bonus");

		int bonus = 50;

		if (checkTopScore(player) > 62) {
			getPlayer(player).addScoreToList(8, 50);
			return bonus;

		}

		return 0;

	}

	public int checkForQuantityOfNumber(int n) {
		int quantityOfNumber = 0;

		for (int i = 0; i < dices.size(); i++) {
			if (dices.get(i).getValue() == n) {

				quantityOfNumber++;

			}

		}

		System.out.println("Antalet " + n + " är " + quantityOfNumber);

		return quantityOfNumber;
	}

}
