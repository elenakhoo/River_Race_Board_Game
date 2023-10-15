/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
public class Boat {
	
	// Attribute(s)
	private int boatLocation;
	
	// Constructor(s)
	public Boat()
	{
		boatLocation = 0;
	}
	
	// Setter(s) & Getter(s)
	public void setLocationOfBoat(int bLocation)
	{
		if (bLocation > 99)
		{
			boatLocation = 99; //if boat location past 99, it will be set to 99.
		}
		else if (bLocation < 0)
		{
			boatLocation = 0; //if boat location below 0, it will be set to 0.
		}
		else
		{
			boatLocation = bLocation; //if boat location between 0 to 99, it will be set.
		}
	}
	
	public int getLocationOfBoat()
	{
		return boatLocation;
	}
	
	// toString
	public String toString()
	{
		return String.format("The boat is located in the river at spot number %d", getLocationOfBoat());
	}
}