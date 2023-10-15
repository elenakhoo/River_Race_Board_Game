/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Scoreboard {
	// Scoreboard has a composition (has-a-relationship) relationship with Contestant.
	// A scoreboard has contestants.
	
	// Attribute(s)
	private Formatter scoreboardOutput;
	private ArrayList<Contestant> contestants = new ArrayList<Contestant>();
	private Scanner scoreboardInput;
	
	// Other Method(s)
	public void orderScores()
	{
		Comparator<Contestant> ascendingOrder = (c1, c2) -> (int) (c1.getContestantScore() - c2.getContestantScore());
		Collections.sort(contestants, ascendingOrder);
	}
	
	public void printScoreboardData()
	{
		System.out.println("-----------------------------------------");
		System.out.println("            |TOP 5 SCOREBOARD|             ");
		System.out.println("(Placing)--------(Username)--------(Score)");
		
		int list;
		
		if (contestants.size() < 5)
		{
			list = contestants.size();
		}
		else
		{
			list = 5;
		}
		for (int index = 0; index < list; index++)
		{
			System.out.printf("%5d. %17s %15d\n", index + 1, contestants.get(index).getContestantUsername(), contestants.get(index).getContestantScore());
		}
		System.out.println("-----------------------------------------");
	}
	
	public void generateFile(String n)
	{
		try
		{
			File createFile = new File(n);
		}
		catch (Exception e)
		{
			System.out.println("Error has occured from file!" + e);
		}
	}
	
	public Scanner openInputScannerFile(String n)
	{
		Scanner i = null;
		
		try
		{
			i = new Scanner(new File(n));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not created!" + e);
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		return i;
	}
	
	public void readTopScoresFile()
	{
		try
		{
			while (scoreboardInput.hasNext())
			{
				Contestant readContestant = new Contestant();
				readContestant.setContestantUsername(scoreboardInput.next());
				readContestant.setContestantScore(scoreboardInput.nextInt());
				contestants.add(readContestant);
			}
		}
		catch (NoSuchElementException e)
		{
			System.err.println("Error with the file!" + e);
			scoreboardInput.close();
			System.exit(1);
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
	}
	
	public void closeInputScannerFile(Scanner i)
	{
		if (i != null)
		{
			i.close();
		}
	}
	
	public Formatter openOutputFormatterFile(String n)
	{
		Formatter u = null;
		
		try
		{
			u = new Formatter(new File(n));
		}
		catch (FileNotFoundException e)
		{
			System.err.println("File not created!" + e);
		}
		catch (Exception e)
		{
			System.err.println(e);
		}
		return u;
	}
	
	public void insertTopScoresFile()
	{
		for (int x = 0; x < contestants.size(); x++)
		{
			scoreboardOutput.format("%s %d \n", contestants.get(x).getContestantUsername(), contestants.get(x).getContestantScore());
		}
	}
	
	public void closeOutputFormatterFile(Formatter o)
	{
		if (o != null)
		{
			o.close();
		}
	}
	
	public void addScoresToList(Contestant c)
	{
		Contestant w = c;
		try
		{
			contestants.add(w);
		}
		catch (NoSuchElementException e)
		{
			System.err.println("Error inputting data to file!" + e);
			return;
		}
		catch (Exception e)
		{
			System.err.println("Error inputting data to file!" + e);
			return;
		}
	}
	
	public void generateScoreboard()
	{
		generateFile("TopScores.txt");
	}
	
	public void captureScoreboard()
	{
		scoreboardInput = openInputScannerFile("TopScores.txt");
		readTopScoresFile();
		closeInputScannerFile(scoreboardInput);
	}
	
	public void loadScoreboard()
	{
		scoreboardOutput = openOutputFormatterFile("TopScores.txt");
		insertTopScoresFile();
		closeOutputFormatterFile(scoreboardOutput);
		
	}
	
	public void displayScoreboard()
	{
		orderScores();
		printScoreboardData();
	}
}
