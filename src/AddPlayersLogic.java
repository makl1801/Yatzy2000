import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class AddPlayersLogic {
	private ArrayList<String> playerNames = new ArrayList<String>();
	
	//Lyssnare som tar in namnen vi angav och skapar spelarobjekt av dem
	public void spelaActionListener(JButton spela, int amountOfPlayers, ArrayList<JTextField> textFields, JFrame mainFrame){
		
		
		spela.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				
				for(int i = 0; i <amountOfPlayers; i++){
					String addedName = textFields.get(i).getText();
					
					addPlayer(addedName);
					
				}
					
				new GameGui().prepare(playerNames);
				mainFrame.dispose();
			}
			
			
		});
		
		
		
		
		
		
	}
	
public void addPlayer(String addedName){
		
//		String name = addedName;
		playerNames.add(addedName);
		
	}
	
	
	
}
