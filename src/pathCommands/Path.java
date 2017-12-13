package pathCommands;

import java.util.ArrayList;

public class Path 
{
	ArrayList<Command> pathCommands;
	
	private boolean isFilled;
	
	public Path(boolean f)
	{
		this.isFilled = f;
	}

	public boolean isFilled() {
		return isFilled;
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}
}
