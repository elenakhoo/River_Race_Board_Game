/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
import java.util.ArrayList;
import java.util.Random;

public class River {
	// River has a composition relationship (has-a-relationship) with Current, Trap, Bomb, RiverObject, and Contestant.
	// The river will be displayed horizontally, similar to the visual in the question paper.

	// Attribute(s)
	private ArrayList<RiverObject> riverStream = new ArrayList<RiverObject>();
	private ArrayList<RiverObject> traps = new ArrayList<RiverObject>();
	private ArrayList<RiverObject> currents = new ArrayList<RiverObject>();
	private ArrayList<RiverObject> bombs = new ArrayList<RiverObject>();
	private ArrayList<String> contestantUsernames;
	private Current current;
	private Trap trap;
	private RiverObject ro;
	private Bomb bomb;
	private String riverIcon;
	private Random randomLocation = new Random();
	
	// Setter(s) & Getter(s)
	public void setCurrents()
	{
		while (currents.size() < 15)
		{
			current = new Current();
			int spot = randomLocation.nextInt(99);
			
			if (spot %5 == 1)
				if (checkIndex(spot, currents) == false)
				{
					current.setObjectLocation(spot);
					currents.add(current);
				}
		}
		for (RiverObject ro: currents)
		{
			riverStream.set(ro.getObjectLocation(), ro);
		}
	}
	
	public void setTraps()
	{
		while (traps.size() < 15)
		{
			trap = new Trap();
			int spot = randomLocation.nextInt(99);
			
			if (spot %5 == 0)
				if (checkIndex(spot, traps) == false)
				{
					trap.setObjectLocation(spot);
					traps.add(trap);
				}
		}
		for (RiverObject ro: traps)
		{
			riverStream.set(ro.getObjectLocation(), ro);
		}
	}
	
	public void setBombs()
	{
		while (bombs.size() < 5)
		{
			bomb = new Bomb();
			int spot = randomLocation.nextInt(99);
			
			if (spot %5 == 2)
				if (checkIndex(spot, bombs) == false)
				{
					bomb.setObjectLocation(spot);
					bombs.add(bomb);
				}
		}
		for (RiverObject ro: bombs)
		{
			riverStream.set(ro.getObjectLocation(), ro);
		}
	}
	
	public ArrayList<RiverObject> getRiverStream()
	{
		return riverStream;
	}
	
	// Other Method(s)
	public String presentRiverStream(ArrayList<Contestant> contestants)
	{
		riverIcon = "~(Go!)~";
		
		for (RiverObject ro: riverStream)
		{
			boolean currentContestantPosition;
			currentContestantPosition = false;
			contestantUsernames = new ArrayList<String>();
			int currentRiverSpot;
			currentRiverSpot = ro.getObjectLocation();
			
			for(Contestant c: contestants)
			{
				if (c.getContestantBoatLocation() == currentRiverSpot)
				{
					contestantUsernames.add(c.getContestantUsername());
					currentContestantPosition = true;
				}
			}
			if (currentContestantPosition)
			{
				riverIcon += String.format("[%s %s's Boat]", ro.getObjectIcon(), String.join(", ", contestantUsernames));
			}
			else
			{
				riverIcon += ro.getObjectIcon();
			}
		}
		return riverIcon + "~(End!)~";
	}
	
	public boolean checkIndex(int i, ArrayList<RiverObject> check)
	{
		for (RiverObject ro: check)
		{
			if (i == ro.getObjectLocation())
			{
				return true;
			}
		}
		return false;
	}
	
	public void generateRiverStream()
	{
		for (int index = 0; index < 100; index++)
		{
			ro = new RiverObject();
			ro.setObjectLocation(index);
			riverStream.add(ro);
		}
	}
}
