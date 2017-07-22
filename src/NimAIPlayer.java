/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.3, 2.4 and 2.5 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
	
	Coded by: Jin Huang
	Modified by: Jianzhong Qi, 29/04/2015
*/

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the task in Section 2.3	

	public NimAIPlayer(String userName, String name, String familyName) {
		super(userName, name, familyName);	
	}
	public NimAIPlayer(String userName, String name, String familyName, Integer gamesPlayd, Integer gamesWon){
	super(userName, name, familyName, gamesPlayd, gamesWon);
	}
	public static NimPlayer[] AddPlayer(NimPlayer[] players, Integer count, String playerDetails) {
		String[] details = new String[3];
		details = playerDetails.split(",");

		NimPlayer newPlayer = new NimAIPlayer(details[0].toString(), details[2].toString(), details[1].toString());
			players[count] = newPlayer;
		return players;
	}
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
	public static NimPlayer[] AddPlayerWithStats(NimPlayer[] players, Integer count, String playerDetails) 
	{
		String[] details = new String[7];
		details = playerDetails.split(" ");
		Integer playd = Integer.parseInt(details[5]);
		Integer won = Integer.parseInt(details[6]);
        
		NimPlayer newPlayer = new NimAIPlayer(details[2].toString(), details[4].toString(), details[3].toString(),playd, won);
		players[count-1] = newPlayer;
		return players;
}
	//RemoveStone: Remove the stones for the NimGame, checking against the max number
	//@override
		public static int RemoveStone(String player,  int max, int total) 
		{   double k;
			System.out.println(player + "'s turn - remove how many?");
			Integer numToRemove;
			int newTotal;
	
			for (int i = 1; i<=1;i++) {
				newTotal = total - i;
				k = (newTotal-1)/(max+1);
				if (((newTotal-1)/(max+1) % 1) == 0) {
					return i;
				}
			}
			return max;
		}
}