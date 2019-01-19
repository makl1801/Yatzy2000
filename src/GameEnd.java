import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.*;


public class GameEnd {
	
	public void gameEnd(ArrayList<Player> players){
		JFrame mainFrame = new JFrame();
		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel mainPanel = new JPanel(new GridLayout(players.size()+2, 1));
		
		JButton playAgain = new JButton("Spela igen");
		playAgain.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				new Menu().menuGui();
				mainFrame.dispose();
				
			}
			
			
		});
		
		
		JLabel someText = new JLabel("Grattis " + players.get(0).getName() + " du vann! Med hela " + players.get(0).getTotalScore() + " Poäng!");
		
		
		mainPanel.add(someText);
		
		for(int i = 1; i < players.size(); i++){
			
		JLabel playerAndPoints = new JLabel(players.get(i).getName() + "  " + players.get(i).getTotalScore() + " Poäng");
			
			mainPanel.add(playerAndPoints);
		}
		
		mainPanel.add(playAgain);
		mainFrame.add(mainPanel);
		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
	}
	
	
	

}
