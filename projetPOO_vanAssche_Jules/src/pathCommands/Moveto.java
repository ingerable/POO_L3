package pathCommands;


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
	
	@Override
	public Point getPosition()
	{
		return this.getPoints().get(0);
	}
}
