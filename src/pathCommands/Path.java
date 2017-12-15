package pathCommands;

import java.util.ArrayList;

import shapeComponents.Point;

public class Path 
{
	private ArrayList<Command> pathCommands;
	
	private boolean isFilled;
	
	private String name;
	
	public Path(boolean f)
	{
		this.isFilled = f;
		this.pathCommands = new ArrayList<Command>();
	}

	public Path() 
	{}
	
	public void toAbsolute()
	{
		Point cursor = this.pathCommands.get(0).getPoints().get(0); // extract the M commands point (absolute position of the cursor)
		
		//for each command of the path
		for(int i=1; i<this.pathCommands.size();i++)
		{
			//convert points
			cursor = this.pathCommands.get(i).toAbsolute(cursor);
		}
	}
	/*
	 * accessors
	 */
	public boolean isFilled() 
	{
		return isFilled;
	}

	public void setFilled(boolean isFilled) 
	{
		this.isFilled = isFilled;
	}
	
	public void addCommand(Command c)
	{
		this.pathCommands.add(c);
	}
	
	public void printCommands()
	{
		for(Command p : this.pathCommands)
		{
			p.presentYourself();
		}
	}
	
	public String name() 
	{
		return name;
	}

	public void setName(String n) 
	{
		this.name = n;
	}
}
