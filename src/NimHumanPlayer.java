
public class NimHumanPlayer extends NimPlayer {
	
	public NimHumanPlayer(String userName, String name, String familyName){
	super(userName, name, familyName);
	}
	public NimHumanPlayer(String userName, String name, String familyName, Integer gamesPlayd, Integer gamesWon){
	super(userName, name, familyName, gamesPlayd, gamesWon);
	}
	
	public static NimPlayer[] AddPlayer(NimPlayer[] players, Integer count, String playerDetails) {
		String[] details = new String[3];
		details = playerDetails.split(",");

		NimPlayer newPlayer = new NimHumanPlayer(details[0].toString(), details[2].toString(), details[1].toString());
			players[count] = newPlayer;
		return players;
	}
	
	public static NimPlayer[] AddPlayerWithStats(NimPlayer[] players, Integer count, String playerDetails) 
	{
		String[] details = new String[7];
		details = playerDetails.split(" ");
		Integer playd = Integer.parseInt(details[5]);
		Integer won = Integer.parseInt(details[6]);
        
		NimPlayer newPlayer = new NimHumanPlayer(details[2].toString(), details[4].toString(), details[3].toString(),playd, won);
		players[count-1] = newPlayer;
		return players;
}}
