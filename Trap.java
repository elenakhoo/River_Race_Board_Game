/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
public class Trap extends RiverObject{ // Is-a-relationship between Trap and RiverObject (Inheritance).
	
	// Trap will call the 2nd constructor in RiverObject and pass in the symbol specified.
	// Trap is a subclass of RiverObject.
	
	// Constructor(s)
	public Trap()
	{
		super("(T)");
	}
	
}