package pathCommands;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import shapeComponents.Point;

public class Closepath extends Command
{
		
	public Closepath(boolean isR) 
	{
		super('z',isR);
	}
	
	@Override
	public FileReader extractPoints(FileReader in, char firstChar) throws IOException
	{	
		if( firstChar =='"')
		{
			System.out.println("z closes the path");
			this.setLast(true);
		}
		
		return in;	
	}
}
