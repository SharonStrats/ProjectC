/* Author: 	Sharon Stratsianis
 * Subject: COMP90041
 * Tutorial:Monday 9am
 * Date:	04/05/2017
 * Project:	B
 */
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;


/* Class: 				Nimsys
 * Description: 		This class is the main class to create a player list and play NimGames
 * Instance Variables: 	sc:Scanner scanner to receive commands from user
 * Public Methods:		main(String[] args): void
 * Private Methods:		DisplayPlayer(NimPlayer[] players, Integer count, String command): void
 * 						Rankings(NimPlayer[] players, Integer count, String sort): void
 * 						StartGame(NimPlayer[] players, Integer count, String command): NimPlayer[]
 * 						ProcessCommand(String input): String[]
 * 						GetPlayer(NimPlayer[] players, Integer count,String playerName): NimPlayer
 * 						CheckPlayerExists(NimPlayer[] players, Integer count, String userName): Boolean
*/


public class Nimsys 
{

	public static Scanner sc;
	public static Scanner streamObject;
	private static NimGame game;
	private static NimPlayer[] players = new NimPlayer[100]; 
	private static int numOfPlayers = 0; 
	private static Boolean resetAll = false;
	Boolean correctArg;

	private static Boolean exists;

	Nimsys(Scanner streamObject, String fileName) 
	{
		try {
			Nimsys.streamObject = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Nimsys(Scanner sc)
	{
		Nimsys.sc = new Scanner(System.in);
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		//File f = null;
		String userInput;
		String[] userCommand;
		System.out.println("Welcome to Nim");
		System.out.println();
		sc = new Scanner(System.in);
		
        // Get the players
      //  game = new NimGame();
        Boolean userExit = false;
        
        // loading from file

        File file = new File("players.dat");
        
        if (file.exists()) {
        try 
        {
        	// read from the file
            Scanner fileScanner = new Scanner(file);
        	while (fileScanner.hasNextLine()) {
        		String playerDetails = fileScanner.nextLine();	 
        		String[] details = new String[5];
        		details = playerDetails.split(" ");
        		numOfPlayers++;
        		if (details[1].equals("NimHumanPlayer")) {
        			NimHumanPlayer.AddPlayerWithStats(players, numOfPlayers, playerDetails);
        		}
        		else {
        			NimAIPlayer.AddPlayerWithStats(players, numOfPlayers, playerDetails);
        		}
        			
        	}
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        }
// Main instructions of running game.        
        
        while(!userExit) 
        {
        	try {
        	System.out.print("$");
        	userInput = sc.nextLine();

        	userCommand = ProcessCommand(userInput);
    
        	if (!userCommand[0].toString().isEmpty()) 
        	{
        		
        		switch(userCommand[0].toLowerCase()) 
        		{
			
        		case "addplayer" : 
        			Boolean correctArg = CheckArguments(userCommand);
        			try {
        				
        			if (correctArg) {
        				String[] commandDetails = userCommand[1].split(",");
        				exists = CheckPlayerExists(players,numOfPlayers,commandDetails[0]);		
        				if (!exists) {	
        					players = NimHumanPlayer.AddPlayer(players, numOfPlayers , userCommand[1].toString());
        					numOfPlayers++;
        				}
        				else 
        				{
        					System.out.println("The player already exists.");	
        				}
        				System.out.println("");
        			}
        			else {
        				throw new Exception(" ");
        			}}
        			catch (Exception e) {
        				System.out.println("");
        				break;
        			}
        			break;
        		case "addaiplayer" :
        			correctArg = CheckArguments(userCommand);
        			try {
        				if (correctArg) {
        					String[] commandDetails1 = userCommand[1].split(",");
        					exists = CheckPlayerExists(players,numOfPlayers,commandDetails1[0]);
        					if (!exists) {
        					players = NimAIPlayer.AddPlayer(players, numOfPlayers , userCommand[1].toString());
        					numOfPlayers++;
        					}
            				else 
            				{
            					System.out.println("The player already exists.");	
            				}
        					System.out.println("");
        					}
        				else {
        					throw new Exception(" ");
        					}
        				} catch (Exception e) {
        					System.out.println("");
            				break;
        				}
        					
        					break;
        		case "removeplayer" :
        			if (userCommand.length > 1) 
        			{
        				exists = CheckPlayerExists(players, numOfPlayers, userCommand[1].toString());
				
        				if (exists) 
        				{
        					players = NimPlayer.RemovePlayer(players, numOfPlayers, userCommand[1].toString());
        					numOfPlayers--;
        				}
        				else 
        				{
        					System.out.println("The player does not exist.");
        				}
        			}
        			else 
        			{
        				System.out.println("Are you sure you want to remove all players? (y/n)");
        				String sure = sc.nextLine(); 
        				if (sure.toLowerCase().contentEquals("y")) 
        				{
        					numOfPlayers = 0;
        				}
        			}
        			System.out.println("");
        			break;
        		case "editplayer" :
        			String[] commandDetails2 = userCommand[1].split(",");
        			exists = CheckPlayerExists(players,numOfPlayers,commandDetails2[0]);		
        			if (exists) 
        			{
        				players = NimPlayer.EditPlayer(players,numOfPlayers,userCommand[1].toString());
        			}
        			else 
        			{
        				System.out.println("The player does not exist.");	
        			}
        			System.out.println("");
        			break;
        		case "resetstats" :
        			if (userCommand.length > 1) 
        			{
        				exists = CheckPlayerExists(players,numOfPlayers, userCommand[1].toString());
            			if (exists) 
            			{
            				players = NimPlayer.ResetStats(players,numOfPlayers, userCommand[1].toString(), resetAll);
            			}
            			else 
            			{
            				System.out.println("The player does not exist.");	
            			}
        			}
        			else 
        			{
        				System.out.println("Are you sure you want to reset all player statistics? (y/n)");
        				String sure = sc.nextLine(); 
        				if (sure.toLowerCase().contentEquals("y")) 
        				{
        					resetAll = true;
        					players = NimPlayer.ResetStats(players,numOfPlayers," ", resetAll);
        					resetAll = false;
        				}
        			}
        			System.out.println("");
        			break;
        		case "displayplayer" :      		
        			if (userCommand.length > 1) 
        			{
        				DisplayPlayer(players, numOfPlayers, userCommand[1].toString());
        			}
        			else 
        			{
        				if (numOfPlayers > 0) 
        				{
        					DisplayPlayer(players, numOfPlayers, "");
        				}
        			}	
        			System.out.println("");
        			break;
        		case "rankings" :
        			if (userCommand.length > 1) 
        			{
        				Rankings(players, numOfPlayers, userCommand[1].toString());
        			}
        			else 
        			{
        				Rankings(players, numOfPlayers, "desc");
        			}
        			break;
        		case "startgame" :
        			players = StartGame(players,numOfPlayers, userCommand[1].toString());
        			break;
        		case "exit" : 
        			userExit = true;
        			//Write output to a file.
        			WriteOutput(players,numOfPlayers);
        			System.out.println("");
        			System.exit(0);
        			break;
        		default :
        			System.out.println("");
        			throw new Exception("'" + userCommand[0].toString() + "' is not a valid command.");
        		} 
        		}} catch (Exception e) {
        			String message = e.getMessage();
        			System.out.println("");
        		}
        		finally {

        		}
        	}
	
        }
	
// WriteOutput:  Output player stats to a file
	private static void WriteOutput(NimPlayer[] players, Integer count) {
		try 
		{
		PrintWriter outputStream = null;
		outputStream = new PrintWriter(new FileOutputStream("players.dat"));
		  for (int i = 0; i < count; i++ ) 
	        {
	        	if (players[i] != null) 
	        	{
	        	
	            	outputStream.println(players[i].getClass().toString() + " " + players[i].GetUserName()  + " " + players[i].GetFamilyName()+ " " + players[i].GetName()  + " "  + players[i].GetGamesPlayd() + " " + players[i].GetGamesWon());	
	        		
	        	}
	        }
		  outputStream.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not Found");
		}
	}
	//DisplayPlayer: Display one or all players
	private static void DisplayPlayer(NimPlayer[] players, Integer count, String command) 
	{
		Boolean exists = false;
		if (!command.isEmpty()) 
		{
			exists = CheckPlayerExists(players, count, command);
		} 
		else 
		{
			players = NimPlayer.DisplaySort(players, count);
		}
		if ((exists) || (command.isEmpty())) 
		{
			NimPlayer.DisplayPlayer(players, count, command);
		}
		else 
		{
			System.out.println("The player does not exist.");	
		}
		
	}
	//Rankings: Sort default is desc, if asc print in reverse
	//			Max to print is 10
	private static void Rankings(NimPlayer[] players, Integer count, String sort) 
	{
		String percent = "";
		//this sort based on parameter passed asc or desc
	
		//print and sort only 10
		if (count > 10) 
		{
			count = 10;
		}
		// need to sort
		players = NimPlayer.RankSort(players, count);
		if (sort.isEmpty() || sort.equals("desc")) 
		{
			for (int i = 0; i < count; i++)
			{	
				Integer rankTemp = (int) Math.round(players[i].GetRank());
				percent = rankTemp.toString() + "%";
				System.out.printf("%-5.5s| %02d games | %s %s%n", percent, players[i].GetGamesPlayd(), players[i].GetName(), players[i].GetFamilyName());
			}
		}
		else 
		{
			for (int i = count-1; i < count; i--)
			{
				Integer rankTemp = (int) Math.round(players[i].GetRank());
				percent = rankTemp.toString() + "%";
				System.out.printf("%-5.5s| %02d games | %s %s%n" , percent, players[i].GetGamesPlayd(), players[i].GetName(), players[i].GetFamilyName());
			}
		}
			System.out.println("");
	}
	//Startgame: 	prepares the players and starts the NimGame
	//				Updates stats for playing and winning
	private static NimPlayer[] StartGame(NimPlayer[] players, Integer count, String command) 
	{	
		String[] curPlayers = command.split(",");
		int stones = Integer.parseInt(curPlayers[0].toString());
		int upper = Integer.parseInt(curPlayers[1].toString());
		if ((CheckPlayerExists(players, numOfPlayers, curPlayers[2])) && (CheckPlayerExists(players, numOfPlayers, curPlayers[3]))) 
		{
			NimPlayer player1 = GetPlayer(players, numOfPlayers, curPlayers[2]);
			NimPlayer player2 = GetPlayer(players, numOfPlayers, curPlayers[3]);
			players = NimPlayer.UpdateGameStats(players, numOfPlayers, player1);
			players = NimPlayer.UpdateGameStats(players, numOfPlayers, player2);
			NimGame game = new NimGame(player1, player2, stones, upper);
			NimPlayer winner = NimGame.PlayGame(game);
			players = NimPlayer.UpdateWonStats(players, numOfPlayers, winner);
		}
		else 
		{
			System.out.println("One of the players does not exist.");
			System.out.println("");
		}
		return players;
	}
	//ProcessCommand: Split the command and return the command
	private static String[] ProcessCommand(String input) 
	{
		//First spot will have command
		//Second spot will have arguments
		String[] commandDetails = input.split(" ");
		
		Boolean valid = CheckCommand(commandDetails);
		if (valid) 
		{
			return commandDetails;
		}
		else {
			return new String[0];
		}
	}
	//CheckCommand:  Check to make sure the user has entered a correct command
	private static Boolean CheckCommand(String[] command)
	{
		//if string compare - is equal return true else return false
		try {
		if ((command[0].toLowerCase().equals("addplayer")) || command[0].toLowerCase().equals("addaiplayer")  || command[0].toLowerCase().equals("editplayer") || command[0].toLowerCase().equals("displayplayer") || command[0].toLowerCase().equals("removeplayer") || command[0].toLowerCase().equals("resetstats")  || command[0].toLowerCase().equals("startgame") || command[0].toLowerCase().equals("rankings") || command[0].toLowerCase().equals("exit")){
			return true;
		} 
			throw new Exception("'" + command[0].toString() + "' is not a valid command.");
		}
		catch (Exception e) {
			String message = e.getMessage();
			System.out.println(message);
			return false;
		}
		}
	private static Boolean CheckArguments(String[] command) {

		String[] details = command[1].split(",");
		try {
		if (command[0].toLowerCase().equals("addplayer") || command[0].toLowerCase().equals("addaiplayer") || command[0].toLowerCase().equals("editplayer")) {
			if (details.length < 3) {
				throw new Exception("Incorrect number of arguments supplied to command.");
		}
			else {
				return true;
			}
		}
		// need to check the others.....
		else if (command[0].toLowerCase().equals("addplayer") || command[0].toLowerCase().equals("addaiplayer") || command[0].toLowerCase().equals("editplayer")) {
			if (command.length < 4) {
				throw new Exception("Incorrect number of arguments supplied to command.");
		}}}
		catch (Exception e) {
			String message = e.getMessage();
			System.out.println(message);
			return false;
		}
	return false;	
	}
	//GetPlayer: Get the details of the player that is playing the game.
	private static NimPlayer GetPlayer(NimPlayer[] players, Integer count,String playerName) 
	{
		Boolean found = false;
		int i = 0;
		while (!found) 
		{
			if (players[i].GetUserName().equals(playerName)) 
			{
				found = true;
			}
			i++;
		}	
		return players[i-1];
	}
	
	private static Boolean CheckPlayerExists(NimPlayer[] players, Integer count, String userName) 
	{
		Boolean found = false;

		if (count == 0) 
		{
			return found;
		} 
		else 
		{
			for (int i = 0; i < count; i++) 
			{
				if (players[i] != null) 
				{
					if (players[i].GetUserName().equals(userName)) 
					{
						found = true;
						break;
					} 
				} else 
				{
					return found;
				}
			} 
		}
		return found;
		
	}
}
