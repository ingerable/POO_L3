package pathCommands;

import java.io.FileReader;
import java.io.IOException;

public class Closepath extends Command
{
		
	public Closepath(boolean isR) 
	{
		super('z',isR);
	}
	
	@Override // here we do not extract any point (z does not take arguments)
	public FileReader extractPoints(FileReader in, char firstChar) throws IOException
	{	
		//seems like z is closing the path
		if( firstChar =='"')
		{
			this.setLast(true);
		}
		
		return in;	
	}	
}
