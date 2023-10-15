/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
import java.util.Random;

public class RiverObject {
	
	// Attribute(s)
	private int objectLocation;
	private int objectStrength;
	private String objectIcon;
	
	// Constructor(s)
	public RiverObject()
	{
		setObjectIcon("_"); // Setting the normal river icon
		objectStrength = 0;
	}
	
	public RiverObject(String icon)
	{
		setObjectIcon(icon); // Setting the icon as what is received from the current, trap and bomb.
	}
	
	// Setter(s) & Getter(s)
	public void setObjectLocation(int location)
	{
		objectLocation = location;
	}
	
	public int getObjectLocation()
	{
		return objectLocation;
	}
	
	public void setObjectStrength(int strength)
	{
		objectStrength = strength;
	}
	
	public int getObjectStrength()
	{
		return objectStrength;
	}
	
	public void setObjectIcon(String icon)
	{
		objectIcon = icon;
	}
	
	public String getObjectIcon()
	{
		return objectIcon;
	}
	
	// Other Method(s)
	public void generateObjectStrength()
	{
		int[] slist = {2, 3, 4, 5, 6, 7};
		Random chooserandom = new Random();
		setObjectStrength(slist[chooserandom.nextInt(slist.length)]); // Chooses random strength (power) for the object within the slist.
	}
	
	public void generateHardObjectStrength()
	{
		int[] blist = {10, 15, 20};
		Random chooserandoms = new Random();
		setObjectStrength(blist[chooserandoms.nextInt(blist.length)]); // Chooses random strength (power) for the bomb object within the blist.
	}
}