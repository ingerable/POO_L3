package pathCommands;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import shapeComponents.Point;

public class Bezier extends Command 
{
		
	public Bezier(boolean isR)
	{
		super('c',isR);
		if(!isR)
		{
			this.setCharType('C');
		}
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
	
	@Override
	public Point getPosition()
	{
		return this.getPoints().get(this.getPoints().size()-1);
	}	
	
}
