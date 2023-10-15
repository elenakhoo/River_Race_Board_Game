/*
 * @author Group 14 Assignment (OOPF Boat Racing Game)
 */
public class Bomb extends RiverObject{ // Is-A-Relationship between Bomb and RiverObject (Inheritance).
	
	// Bomb will call the 2nd constructor in RiverObject and pass in the symbol specified.
	// Bomb is a subclass of RiverObject.
	// Additional Attribute
	
	// Constructor(s)
	public Bomb()
	{
		super("(B)");
	}
}
