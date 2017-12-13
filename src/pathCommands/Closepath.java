package pathCommands;

import java.io.FileInputStream;
import java.util.ArrayList;

import shapeComponents.Point;

public class Closepath extends Command
{
	private ArrayList<Point> points;
	
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
	
	@Override
	public ArrayList<Point> getPoints() 
	{
		return this.points;
	}
	
	@Override
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

}
