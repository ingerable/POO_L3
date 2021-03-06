package pathCommands;

import shapeComponents.Point;

public class Lineto extends Command
{
	
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
