package pathCommands;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
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
	
	
	@Override
	//get the point for the current command with the given file stream, the last argument indicate if the there is space or not before we read 
		public FileReader extractPoints(FileReader in,char firstNumber) throws IOException
		{
			FileReader inR = super.extractPoints(in, firstNumber);
			inR = super.extractPoints(inR, (char)inR.read());
			inR = super.extractPoints(inR, (char)inR.read());
			return in;
			
		}
		
	
}
