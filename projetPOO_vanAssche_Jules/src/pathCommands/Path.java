package pathCommands;

import java.util.ArrayList;

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
		if(c.getCharType()=='m' && this.pathCommands.size()==0)//case where m is absolute even if the char is m because this is the first command
		{
			c.setRelative(false);
		}
		this.pathCommands.add(c);
	}
	
	public ArrayList<Command> getCommand()
	{
		return this.pathCommands;
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
