package pathCommands;


import java.util.ArrayList;

import shapeComponents.Point;

public class Moveto extends Command
{
	
	public Moveto() 
	{
		super('m');
	}
	
	//return the name of the implicit command after a moveto command
	@Override
	public char getImplicitCommand()
	{
		return 'l';
	}
	
	
}
