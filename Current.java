/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
public class Current extends RiverObject{ // Is-A-Relationship between Current and RiverObject (Inheritance).
	
	// Current will call the 2nd constructor in RiverObject and pass in the symbol specified.
	// Current is a subclass of RiverObject.
	
	// Constructor(s)
	public Current()
	{
		super("(C)");
	}
}
