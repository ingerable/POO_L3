package pathCommands;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import shapeComponents.Point;

public class Moveto extends Command
{
	
	public Moveto(boolean isR) 
	{
		super('m', isR);
	}
	
	//return the name of the implicit command after a moveto command
	@Override
	public char getImplicitCommand()
	{
		return 'l';
	}
	
}
