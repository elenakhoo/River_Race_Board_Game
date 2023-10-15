/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	// Game has a composition relationship with Scoreboard, River, Dice, & Contestant.
	// We have not made boat connect to game directly, because in the game we display the players names instead of their boat.
	// Game class includes use of polymorphism.
	// Game is essentially the controller class.
	
	Scanner input = new Scanner(System.in);
	
	// Attribute(s)
	private Scoreboard scoreboard = new Scoreboard();
	private ArrayList<Contestant> contestants = new ArrayList<Contestant>();
	private River riverStream = new River();
	private Dice dice = new Dice();
	
	// Other Method(s)
	public void displayInstructions()
	{
		System.out.println("\nINSTRUCTIONS:");
        System.out.println("1) Firstly, a display of the river will be shown to players each round");
        System.out.println("2) The river will have about 100 cells, in which currents and traps will be placed randomly.");
        System.out.println("3) The contestant(s) will have to throw their dice in order to move along the river.");
        System.out.println("4) Contestant(s) have a choice of playing solo, or duo.");
        System.out.println("5) In order to win, you must reach the end of the river");
        System.out.println("6) Stepping on a current will push you forwards in the river.");
        System.out.println("7) However, stepping on a trap will push you backwards in the river.");
        System.out.println("8) In hard mode, watch out for bombs! They push you back really far.");
        System.out.println("9) If there's an error with the scoreboard at first run, please play the game completely for the scoreboard to appear the following rounds.");
        System.out.println("10) Good luck and have fun! Make sure to avoid traps! :)\n");
	}
	
	public void drawBanner()
	{
		System.out.println("\n            /|~~~");
		System.out.println("          ///|");
		System.out.println("        /////|");
		System.out.println("      ///////|");
		System.out.println("    /////////|");
		System.out.println("  \\==========|===/");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public void checkContestantDetails()
	{
		int contestantNumber = 0;
		
		while (true)
		{
			try
			{
				
				System.out.print("Please enter the number of contestants (solo / duo) that are going to participate in this match (Min = 1, Max = 2): ");
				contestantNumber = input.nextInt();
					
				if (contestantNumber > 0 && contestantNumber < 3)
				{
					break;
				}
				else
				{
					throw new Exception();
				}
			}
			catch(Exception e)
			{
				System.err.println("~- You are only allowed to enter a number between 1 to 2! -~");
				input.nextLine();
			}
		}
		for (int index = 0; index < contestantNumber; index++)
		{
			Contestant contestantInGame = new Contestant();
			System.out.println();
			System.out.printf("Enter the username for contestant number %d: ", index + 1);
			contestantInGame.setContestantUsername(input.next());
			
			contestants.add(contestantInGame);
		}
	}
	
	public void openScoreboard()
	{
		scoreboard.generateScoreboard();
		scoreboard.captureScoreboard();
		scoreboard.displayScoreboard();
	}
	
	public void startGame()
	{
		int match = 1;
		String difficulty = "";
		
		drawBanner();
		System.out.println();
		System.out.println("Welcome to Group 14's Boat Race! Have fun!");
		System.out.println();
		displayInstructions();
		openScoreboard();
		riverStream.generateRiverStream();
		ArrayList<RiverObject> river = riverStream.getRiverStream();
		System.out.println();
		checkContestantDetails();
		System.out.println();
		System.out.print("Please enter your level of difficulty (easy / hard): ");
		difficulty = input.next();
		
		while ((difficulty.equals("easy") == false) && (difficulty.equals("hard") == false))
		{
			System.err.println("~- Please enter 'easy' or 'hard' only! -~");
			System.out.print("Please enter your level of difficulty (easy / hard): ");
			difficulty = input.next();
		}
		
		if (difficulty.equals("easy"))
		{
			System.out.println("---------------------------------------------------------");
			System.out.println();
			System.out.println("Loading.....");
			System.out.println("Digging up a pathway....");
			System.out.println("Pouring water to create river....");
			System.out.println("Placing currents.....");
			System.out.println("Placing traps.....");
			System.out.println("All done!");
			System.out.println();
			System.out.println("The game has started!");
			System.out.println();
			System.out.println("River Created:"); 
			System.out.println();
			riverStream.setCurrents();
			riverStream.setTraps();
			System.out.println(riverStream.presentRiverStream(new ArrayList<Contestant>()));
			System.out.println();
			
			while (!endGame())
			{
				System.out.printf("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Match %d-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n", match);
				System.out.println();
				
				for (Contestant cont: contestants)
				{
					int number;
					System.out.printf("%s, enter any letter or number (except enter) to throw the dice: ", cont.getContestantUsername());
					input.next();
					System.out.println();
					number = cont.throwDice(dice);
					System.out.println(cont.getContestantUsername() + " " + "threw the dice and got a" + " " + number + ".");
					cont.setContestantBoatLocation(cont.getContestantBoatLocation() + number);
					
					int position;
					position = cont.getContestantBoatLocation();
					
					RiverObject riverPosition = river.get(position);
					if (riverPosition instanceof Trap)
					{
						System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
						riverPosition.generateObjectStrength();
						cont.setContestantBoatLocation(cont.getContestantBoatLocation() - riverPosition.getObjectStrength());
						System.out.printf("Oh no! %s, you landed on a trap and moved back by %d :(!\n", cont.getContestantUsername(), riverPosition.getObjectStrength());
					}
					else if (riverPosition instanceof Current)
					{
						System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
						riverPosition.generateObjectStrength();
						cont.setContestantBoatLocation(cont.getContestantBoatLocation() + riverPosition.getObjectStrength());
						System.out.printf("Yay! %s, you landed on a current and moved front by %d :)!\n", cont.getContestantUsername(), riverPosition.getObjectStrength());
					}
					cont.setContestantScore(cont.getContestantScore() + 1);
					System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
					System.out.println();
				}
				System.out.println();
				System.out.println(riverStream.presentRiverStream(contestants));
				System.out.println();
				System.out.println();
				match++;
			}
			
			feedback();
		}
		
		if (difficulty.equals("hard"))
		{
			System.out.println("---------------------------------------------------------");
			System.out.println();
			System.out.println("Loading.....");
			System.out.println("Digging up a pathway....");
			System.out.println("Pouring water to create river....");
			System.out.println("Placing currents.....");
			System.out.println("Placing traps.....");
			System.out.println("Placing bombs.....");
			System.out.println("All done!");
			System.out.println();
			System.out.println("The game has started!");
			System.out.println();
			System.out.println("River Created:"); 
			System.out.println();
			riverStream.setCurrents();
			riverStream.setTraps();
			riverStream.setBombs(); // Additional Attribute
			System.out.println(riverStream.presentRiverStream(new ArrayList<Contestant>()));
			System.out.println();
			
			while (!endGame())
			{
				System.out.printf("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*Match %d-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n", match);
				System.out.println();
				
				for (Contestant cont: contestants)
				{
					int number;
					System.out.printf("%s, enter any letter or number (except enter) to throw the dice: ", cont.getContestantUsername());
					input.next();
					System.out.println();
					number = cont.throwDice(dice);
					System.out.println(cont.getContestantUsername() + " " + "threw the dice and got a" + " " + number + ".");
					cont.setContestantBoatLocation(cont.getContestantBoatLocation() + number);
	
					int position;
					position = cont.getContestantBoatLocation();
					
					RiverObject riverPosition = river.get(position);
					if (riverPosition instanceof Trap)
					{
						System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
						riverPosition.generateObjectStrength();
						cont.setContestantBoatLocation(cont.getContestantBoatLocation() - riverPosition.getObjectStrength());
						System.out.printf("Oh no! %s, you landed on a trap and moved back by %d :(!\n", cont.getContestantUsername(), riverPosition.getObjectStrength());
					}
					else if (riverPosition instanceof Current)
					{
						System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
						riverPosition.generateObjectStrength();
						cont.setContestantBoatLocation(cont.getContestantBoatLocation() + riverPosition.getObjectStrength());
						System.out.printf("Yay! %s, you landed on a current and moved front by %d :)!\n", cont.getContestantUsername(), riverPosition.getObjectStrength());
					}
					else if (riverPosition instanceof Bomb)
					{
						System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
						riverPosition.generateHardObjectStrength();
						cont.setContestantBoatLocation(cont.getContestantBoatLocation() - riverPosition.getObjectStrength());
						System.out.printf("Oh no! %s, you landed on a bomb and moved back by %d :(!\n", cont.getContestantUsername(), riverPosition.getObjectStrength());
					}
					cont.setContestantScore(cont.getContestantScore() + 1);
					System.out.println(cont.getContestantUsername() + " " + "moved to" + " " + cont.getContestantBoatLocation() + ".");
					System.out.println();
				}
				System.out.println();
				System.out.println(riverStream.presentRiverStream(contestants));
				System.out.println();
				System.out.println();
				match++;
			}
			
			feedback();
		}
	}
	
	public boolean endGame()
	{
		for (Contestant contestantInGame: contestants)
		{
			if (contestantInGame.getContestantBoatLocation() == 99)
			{
				System.out.println("You reached the finsh line!");
				System.out.println();
				System.out.println("The game has ended! Yay!");
				System.out.printf("\n%s has won the game with exactly %d turns! Congratulations, you played very well!\n", contestantInGame.getContestantUsername(), contestantInGame.getContestantScore());
				System.out.println();
				
				scoreboard.addScoresToList(contestantInGame);
				scoreboard.loadScoreboard();
				scoreboard.displayScoreboard();
				System.out.println();
				
				return true;
			}
		}
		return false;
	}
	
	public void feedback()
	{
		String response = " ";
		String feedback = " ";
		
		System.out.print("Please enter 'yes' if you would like to give us feedback on the game, and 'no' if no: ");
		response = input.next();
		
		while ((response.equals("yes") == false) && (response.equals("no") == false))
		{
			System.err.println("~- Please enter 'yes' or 'no' only! -~");
			System.out.print("Please enter 'yes' if you would like to give us feedback on the game, and 'no' if no: ");
			response = input.next();
		}
		
		if (response.equals("yes"))
		{
			System.out.println();
			System.out.print("Enter feedback here (500 characters): ");
			feedback = input.next();
			
			if (feedback.length() > 100)
			{
				System.err.println("~- Please keep feedback under 500 characters! -~");
				System.out.print("Enter feedback here (500 characters): ");
				feedback = input.next();
			}
			System.out.println();
			System.out.println("Great feedback! Thank you for playing Group 14's Boat Race Game! Please come again soon!");
		}
		else
		{
			System.out.println();
			System.out.print("Thank you for playing Group 14's Boat Race Game! Please come again soon!");
		}
	}
}