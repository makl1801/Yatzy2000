import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Player implements Comparable{

	private String name;
	// private String points = "3";
	private ArrayList<Integer> score = new ArrayList<Integer>();
	private ArrayList<JLabel> scoreBoard = new ArrayList<JLabel>();
	private int numberOfThrows = 3;
	// private boolean active = false;
	private JPanel scorePanel = new JPanel(new GridLayout(21, 1));
	private Border border = LineBorder.createGrayLineBorder();
	private int totalScore;

	public Player(String name) {
		this.name = name;
//		this.totalScore = totalScore;
	}

	public int getSizeOfScore() {

		return score.size();

	}

	public Integer getScore(int index) {

		return score.get(index);

	}

	public void addScoreToList(int round, int points) {

		score.set(round, points);

	}

	public void fillScoreArrayWithZeros(int rounds, int points) {

		score.add(rounds, points);

	}

	public int getNumberOfThrows() {

		return numberOfThrows;
	}

	public String getName() {

		return name;

	}

	public void addLabelToScoreBoard() {
		Color color = new Color(0,255,0);
		JLabel nameLabel = new JLabel(name);
		scorePanel.add(nameLabel);
		
		for (int i = 0; i < 20; i++) {

			JLabel scoreLabel = new JLabel();
			scoreLabel.setBackground(new Color(181,181,181));
			scoreLabel.setBorder(border);
			
			scoreLabel.setOpaque(true);
			scoreBoard.add(i, scoreLabel);

			scorePanel.add(scoreLabel);
			
			
		}

		scoreBoard.get(0).setBorder(null);
		scoreBoard.get(0).setBackground(null);
		

	}

	public JPanel getPlayerScorePanel() {

		return scorePanel;

	}

	public JLabel getPointsJlabelFromPlayer(int n) {

		return scoreBoard.get(n);

	}

	public ArrayList<Integer> getScoreList() {

		return score;

	}

	public JLabel getJLabel(int n) {

		return scoreBoard.get(n);

	}
	
	public JLabel highlightPlayer(){
		
		JLabel a = null;
		
		for(int i = 1; i < 20; i++){
			
			a = scoreBoard.get(i);
				
//			a.setBackground(new Color(228,251,255));
			a.setBackground(Color.WHITE);


		}
		
		return a;
		
	}
	
	public JLabel lowLightPlayer(){
		
		JLabel a = null;
		
		for(int i = 1; i < 20; i++){
			
			a = scoreBoard.get(i);
				
			a.setBackground(new Color(181,181,181));
			

			
			
		}
		
		return a;
		
		
		
	}
	
	public void setPlayerTotalScore(){
		
		for(int i = 0; i < score.size(); i++){
			
			totalScore += score.get(i);
			
		}
		
		
		
	}
	
	public Integer getTotalScore(){
		
		
		return totalScore;
	}
	
	@Override
	  public int compareTo(Object p) {
		     return (((Player) p).getTotalScore()).compareTo(this.getTotalScore());
		  }
	

}