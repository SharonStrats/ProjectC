/* Author: 	Sharon Stratsianis
 * Subject: COMP90041
 * Tutorial:Monday 9am
 * Date:	04/05/2017
 * Project:	B
 */

import java.util.Arrays;
import java.util.Scanner;
/* Class: 				NimPlayer
 * Description: 		Creates a player for a NimGame.  Stores their stats, along with their personal details.
 * 						Contains all methods to deal with a player, adding, removing, display, etc.  see below for all
 * 						public methods.
 * Instance Variables:	userName: 	String, User Name
 * 						name:		String, First name
 * 						familyName:	String, Last name
 * 						gamesPlayd:	Integer, Total games played
 * 						gamesWon:	Integer, Total games won
 * 						rank:		Integer, Stores percentage as an integer.  Will round to store value.
 * Public Methods:		RemoveStone(String player,  int max): Int
 * 						AddPlayer(NimPlayer[] players, Integer count, String playerDetails): NimPlayer[]
 * 						EditPlayer(NimPlayer[] players, Integer count, String playerDetails):NimPlayer[]
 * 						ResetStats(NimPlayer[] players, Integer count, String player, Boolean resetAll): NimPlayer[]
 * 						DisplayPlayer(NimPlayer[] players, Integer count, String user): void
 * 						UpdateGameStats(NimPlayer[] players, Integer count, NimPlayer player): NimPlayer[]
 * 						UpdateWonStats(NimPlayer[] players, Integer count, NimPlayer winner): NimPlayer[]
 * 						RankSort(NimPlayer[] players, Integer count): NimPlayer[]
 * 						DisplaySort(NimPlayer[] players, Integer count): NimPlayer[]
 * Private Methods:		Insert(NimPlayer[] players, Integer count, NimPlayer insPlayer, Integer index):NimPlayer[]
 * 						
 * 
 * 						
 */
public abstract class NimPlayer 
{
	private String userName;
	private String name;
	private String familyName;
	private Integer gamesPlayd; 
	private Integer gamesWon;
	private Double rank;
	
	NimPlayer() {
		userName = " ";
		name = " ";
		familyName = " ";
		gamesPlayd = 0; 
		gamesWon = 0;
		rank = 0.0;
	}
	NimPlayer(String userName, String name, String familyName) 
	{
		this.userName = userName;
		this.name = name;
		this.familyName = familyName;
		this.gamesPlayd = 0;
		this.gamesWon = 0;
		this.rank = 0.0;
	}
	NimPlayer(String userName, String name, String familyName, Integer gamesPlayd, Integer gamesWon) {
		this.userName = userName;
		this.name = name;
		this.familyName = familyName;
		this.gamesPlayd = gamesPlayd;
		this.gamesWon = gamesWon;
		this.rank = (double) Math.round(((double) gamesWon/(double) gamesPlayd * 100));;
	}
	//Getters
	public String GetUserName() {
		return this.userName;
	}
	public String GetName() {
		return this.name;
	}
	public String GetFamilyName() {
		return this.familyName;
	}
	public Integer GetGamesPlayd() {
		return this.gamesPlayd;
	}
	public Integer GetGamesWon() {
		return this.gamesWon;
	}
	public Double GetRank() {
		return this.rank;
	}
	//RemoveStone: Remove the stones for the NimGame, checking against the max number
	public static int RemoveStone(String player,  int max, int total) 
	{
		System.out.println(player + "'s turn - remove how many?");
		Integer numRemove = Nimsys.sc.nextInt();
		try {
			if (numRemove == 0) 
				{
			throw new Exception("Invalid move. You must remove between 1 and 1 stones.");
		}
			else if  (numRemove > max)  
		{
			throw new Exception("Invalid move. You must remove between 1 and " + max + " stones.");
			}
			else {
				return numRemove;
			}
		 } catch (Exception e) {
			 System.out.println("");
			 String message = e.getMessage();
			 System.out.println("");
			 return 0; 
		}
		
	}
	//**********************************************************************************
	//AddPlayer:  Add a player at the end
	// This method will be implemented in HumanPlayer and AI Player
	//public static NimPlayer[] AddPlayer(NimPlayer[] players, Integer count, String playerDetails) 
	//{
	//	String[] details = new String[3];
	//	details = playerDetails.split(",");

	//	NimPlayer newPlayer = new NimPlayer(details[0].toString(), details[2].toString(), details[1].toString());
	//	players[count] = newPlayer;
	//	return players;
	//}
	//AddPlayer:  Add a player at the end
//	public static NimPlayer[] AddPlayerWithStats(NimPlayer[] players, Integer count, String playerDetails) 
	//{
	//	String[] details = new String[5];
	//	details = playerDetails.split(",");
	//	Integer playd = Integer.parseInt(details[3]);
	//	Integer won = Integer.parseInt(details[4]);
        
	//	NimPlayer newPlayer = new NimPlayer(details[0].toString(), details[2].toString(), details[1].toString(),playd, won);
	//	players[count-1] = newPlayer;
	//	return players;
	//}
	
	//************************************************************
	//RemovePlayer: Remove a player
	public static NimPlayer[] RemovePlayer(NimPlayer[] players,Integer count, String playerDetails) 
	{
		Boolean found = false;
		
        for (int i = 0; i < count; i++ ) 
        {
        	if (players[i] != null) {
        		if ((players[i].userName.equals(playerDetails)) || found) 
        		{
        		//redo the array and delete
        			found = true;
        			if (players[i+1] != null) 
        			{
        				players[i].userName = players[i+1].userName;
        				players[i].name = players[i+1].name;
        				players[i].familyName = players[i+1].familyName;
        				players[i].gamesPlayd = players[i+1].gamesPlayd;
        				players[i].gamesWon = players[i+1].gamesWon;
        				players[i].rank = players[i+1].rank;
        			} else 
        			{
        				players[i].userName = "";
        				players[i].name = "";
        				players[i].familyName = "";
        				players[i].gamesPlayd = 0;
        				players[i].gamesWon = 0;
        				players[i].rank = 0.0;
        			}
        		}    
        	}
        }
		return players;
	}
	//EditPlayer: Edit players name and familyName
	public static NimPlayer[] EditPlayer(NimPlayer[] players, Integer count, String playerDetails) 
	{
		String[] details = new String[3];
		details = playerDetails.split(",");
        for (int i = 0; i < count; i++ ) 
        {
        	if (players[i] != null) 
        	{
        		if (players[i].userName.equals(details[0])) 
        		{
        			players[i].name = details[2];
        			players[i].familyName = details[1];   		
        		}
        	}
        }
        return players;
		
	}
	//ResetStats: Reset stats for all or one player
	public static NimPlayer[] ResetStats(NimPlayer[] players, Integer count, String player, Boolean resetAll) 
	{
	    	
		if (resetAll) {
			for (int i = 0; i < count; i++ ) 
			{
				if (players[i] != null) 
				{
					players[i].gamesPlayd = 0;
					players[i].gamesWon = 0;
					players[i].rank = 0.0;
				}
			}
		}
		else 
		{
			for (int i = 0; i < count; i++ ) 
			{
				if (players[i].userName.equals(player)) 
				{
					players[i].gamesPlayd = 0;
					players[i].gamesWon = 0;							
					players[i].rank = 0.0;				
				}
			}   	    	
        }
        return players;
		
	}
	//DisplayPlayer: Display a player
	public static void DisplayPlayer(NimPlayer[] players, Integer count, String user) 
	{
        for (int i = 0; i < count; i++ ) 
        {
        	if (players[i] != null) 
        	{
        		if ((user.isEmpty()) || (players[i].userName.equals(user))) 
        		{
        			System.out.println(players[i].userName + "," + players[i].name + "," + players[i].familyName  + ","  + players[i].gamesPlayd + " games," + players[i].gamesWon + " wins");	
        		}
        	}
        }
		
	}
	//UpdateGameStats: 	Update the game stats for the players who are playing 
	//					and recalc the player's ranking.
	public static NimPlayer[] UpdateGameStats(NimPlayer[] players, Integer count, NimPlayer player) 
	{
		Boolean found = false;
		int i = 0;
	
        while (!found) {
        	if (players[i].userName.equals(player.userName)) 
        	{
        		players[i].gamesPlayd = player.gamesPlayd + 1 ;
        		players[i].rank =  (double) Math.round(((double) player.gamesWon/(double) player.gamesPlayd * 100));
        		found = true;
        	}    
        	i++;
        }
        return players;
		
	}
	//UpdateWonStats: Update the stats for the Winner and recalc rankings
	public static NimPlayer[] UpdateWonStats(NimPlayer[] players, Integer count, NimPlayer winner) 
	{
		Boolean found = false;
		int i = 0;
        while (!found) 
        {
        	if (players[i].userName.equals(winner.userName)) 
        	{
        		found = true;
        		players[i].gamesWon = winner.gamesWon + 1;
        		players[i].rank = (double) Math.round(((double) winner.gamesWon/(double) winner.gamesPlayd * 100));
        	}
        	i++;
        }
        return players;
	}
	//RankSort sorts players by ranking, in either ascending or descending order
	public static NimPlayer[] RankSort(NimPlayer[] players, Integer count) 
	{
		for (int i = 0; i < count-1; i++) {
			for (int j = 1; j < count - i; j++ ) {
				if (players[j-1].rank.compareTo(players[j].rank) < 0) 
				{
					SwapPlayer(players,j, j-1);
				}
				if (players[j-1].rank.compareTo(players[j].rank) == 0) 
				{
					if (players[j].userName.compareTo(players[j-1].userName) < 0)
					SwapPlayer(players,j, j-1);
				}
			}
		}
		return players;
		
	}
	//DisplaySort: Sort them in alphabetical order
	public static NimPlayer[] DisplaySort(NimPlayer[] players, Integer count) 
	{
		for (int i = 0; i < count-1; i++) 
		{
			for (int j = 1; j < count - i; j++ ) 
			{
				if (players[j].userName.compareTo(players[j-1].userName) < 0) 
				{
					SwapPlayer(players,j, j-1);
				}
			}
		}
		return players;
		
	}
	//Insert: Insert player into player array, used to do in alphabetical order
	private static NimPlayer[] Insert(NimPlayer[] players, Integer count, NimPlayer insPlayer, Integer index) 
	{
		for (int i = count; i > index; i-- ) 
		{
			players[i] = players[i-1];		
		}
		players[index] = insPlayer;
		return players;
	}
	//SwapPlayer: SwapPlayer used in sorting
	private static NimPlayer[] SwapPlayer(NimPlayer[] players, int indexTo, int indexFrom) 
	{
			NimPlayer Temp = players[indexFrom];
			players[indexFrom] = players[indexTo];
			players[indexTo] = Temp;
		return players;
	}
}
