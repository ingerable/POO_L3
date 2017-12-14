package pathCommands;

import java.io.FileInputStream;
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
	public FileInputStream extractPoints(FileInputStream in, char firstNumber) throws IOException
	{	
		return in;
		
	}
	
	//return the name of the implicit command after a closepath command
	@Override
	public char getImplicitCommand()
	{
		return 'l';
	}
		

}
