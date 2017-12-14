package pathCommands;

import java.io.FileInputStream;
import java.util.ArrayList;

import shapeComponents.Point;

public class Bezier extends Command 
{
	private ArrayList<Point> points;
	
	private char charType;
	
	private boolean isPolyBezier; //indicate if this bezier command is part of a poliBezier command
		
	public Bezier(boolean b,boolean isR)
	{
		super('c',isR);
		this.isPolyBezier = b;
	}
	
	//return the name of the implicit command after a bezier command
	@Override
	public char getImplicitCommand()
	{
		return 'p'; // stand for polybezier
	}
		
	
}
