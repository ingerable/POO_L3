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
		if(!isR) // check if the command is relative or not and change the char type field
		{
			this.setCharType('L');
		}
	}
	
	@Override
	public Point getPosition()
	{
		return this.getPoints().get(this.getPoints().size()-1);
	}
}
