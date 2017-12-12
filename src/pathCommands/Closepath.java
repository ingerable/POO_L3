package pathCommands;

import java.io.FileInputStream;

public class Closepath extends Command
{
	@Override
	public String extract(FileInputStream fs) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void whoIam()
	{
		System.out.println("I am Closepath command");
	}

}
