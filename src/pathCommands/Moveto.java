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
		
		if(!isR) // check if the command is relative or not and change the char type field
		{
			this.setCharType('M');
		}
		
	}
}
