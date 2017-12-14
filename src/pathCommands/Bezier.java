package pathCommands;

import java.io.FileInputStream;
import java.util.ArrayList;

import shapeComponents.Point;

public class Bezier extends Command 
{
	private ArrayList<Point> points;
	
	private char charType;
	
	public Bezier() 
	{
		super('c');
	}
	
	//return the name of the implicit command after a bezier command
	@Override
	public char getImplicitCommand()
	{
		return 'p'; // stand for polybezier
	}
		
	
}
