/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
public class Contestant {
	
	// Attribute(s)
	private String username;
	private int contestantScore;
	private Boat contestantBoatLocation;
	
	// Constructor(s)
	public Contestant()
	{
		username = "N/A";
		contestantScore = 0;
		contestantBoatLocation = new Boat(); // Shows a relationship is created (Composition) has-a-relationship
	}
	
	public Contestant(String user, int cScore)
	{
		username = user;
		contestantScore = cScore;
	}
	
	// Setter(s) & Getter(s)
	public void setContestantUsername(String user)
	{
		username = user;
	}
	
	public String getContestantUsername()
	{
		return username;
	}
	
	public void setContestantScore(int cScore)
	{
		contestantScore = cScore;
	}
	
	public int getContestantScore()
	{
		return contestantScore;
	}
	
	public void setContestantBoatLocation(int cbLocation)
	{
		contestantBoatLocation.setLocationOfBoat(cbLocation);
	}
	
	public int getContestantBoatLocation()
	{
		return contestantBoatLocation.getLocationOfBoat();
	}
	
	// Other Method(s)
	public int throwDice(Dice dice) // Dice is associated with the contestant.,showing a has-a-relationship.
	{
		return dice.generateDiceNo();
	}
	
	// toString
	public String toString()
	{
		return String.format("%-15s, %-10d", getContestantUsername(), getContestantScore());
	}
}