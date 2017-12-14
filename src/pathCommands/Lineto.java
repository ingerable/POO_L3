package pathCommands;

import java.io.FileInputStream;
import java.util.ArrayList;

import shapeComponents.Point;

public class Lineto extends Command
{
	private ArrayList<Point> points;
	
	private char charType;
	
	public Lineto(boolean isR) 
	{
		super('l',isR);
	}
	
	//return the name of the implicit command after a lineto command
	@Override
	public char getImplicitCommand()
	{
		return 'l';
	}

}
