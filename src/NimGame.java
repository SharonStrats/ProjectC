/* Author: 	Sharon Stratsianis
 * Subject: COMP90041
 * Tutorial:Monday 9am
 * Date:	04/05/2017
 * Project:	B
 */
import java.util.Scanner;
/* Class: 				NimGame
 * Description: 		This class plays a game of Nim, given game details of Stones, max stone removal, and two players.
 * Instance Variables: 	player1, player2: 	NimPlayers
 * 						curStoneCount:		Integer, Number of stones to start with
 * 						maxRemoval:			Integer, max number to be removed at a time.
 * Public Methods:		PlayGame()
 * Private Methods:		PrintStones(), to print the stones left.
 * 						Swap(), to swap players on each turn.
*/
public class NimGame 
{
	private NimPlayer player1;
	private NimPlayer player2;
	private Integer numStones;
	private Integer maxRemoval;
	private static NimPlayer curPlayer;
	
	// Constructor: needs all instance variables to be defined by user.
	NimGame(NimPlayer player1, NimPlayer player2, Integer curStoneCount, Integer maxRemoval) 
	{
		this.player1 = player1;
		this.player2 = player2;
		this.numStones = curStoneCount;
		this.maxRemoval = maxRemoval;
		
	}
	
	//Playgame: controls the details of the game. 
	// Returns the winner
	public static NimPlayer PlayGame(NimGame game) 
	{
		System.out.println("");
		System.out.println("Initial stone count: " + game.numStones);	
		System.out.println("Maximum stone removal: " + game.maxRemoval);	
		System.out.println("Player 1: " + game.player1.GetName() + " " + game.player1.GetFamilyName());				    
		System.out.println("Player 2: " + game.player2.GetName() + " " + game.player2.GetFamilyName());	
		System.out.println("");
		
			curPlayer = game.player1;
			int player = 0;
			int numToRemove = 0;
			
			while (game.numStones > 0)  
			{
			
				PrintStones(game.numStones);
				if (curPlayer.getClass().toString().equals("class NimAIPlayer")) {
					numToRemove = NimAIPlayer.RemoveStone(curPlayer.GetName(),game.maxRemoval,game.numStones);
				}
				else {
					numToRemove = NimPlayer.RemoveStone(curPlayer.GetName(),game.maxRemoval,game.numStones);
				}
				
				if (numToRemove != 0) 
				{
					game.numStones = (game.numStones - numToRemove);
					System.out.println();
					// going to next player
					curPlayer = Swap(curPlayer,game.player1, game.player2);
						
					//we have a winner
					if (game.numStones <= 0) 
					{
						System.out.println("Game Over");
						System.out.println(curPlayer.GetName() + " " + curPlayer.GetFamilyName() + " wins!");
						System.out.println();	
						if (curPlayer.getClass().toString().equals("class NimHumanPlayer")) {
							String extra = Nimsys.sc.nextLine();
					}}
				}
			} 
			return curPlayer;	
		}
		
	// PrintStones:  prints how many stones are left.	
	private static void PrintStones(Integer numStones) 
	{
		System.out.print(numStones + " stones left:");
		for (int i = 1; i <= numStones; i++) 
		{
			System.out.print(" *");
			if (i == numStones) 
			{
				System.out.println();
			}
		}
	}
	//Swap: Swaps players to return current player.
	private static NimPlayer Swap(NimPlayer current, NimPlayer player1, NimPlayer player2) 
	{
		if (player1.GetName().equals(current.GetName())) 
		{
			return player2;
		}
		else 
		{
			return player1;
		}
	}

}
