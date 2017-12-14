package pathCommands;

import java.util.ArrayList;

public class Path 
{
	private ArrayList<Command> pathCommands;
	
	private boolean isFilled;
	
	public Path(boolean f)
	{
		this.isFilled = f;
		this.pathCommands = new ArrayList<Command>();
	}

	public Path() 
	{}

	public boolean isFilled() {
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
}
