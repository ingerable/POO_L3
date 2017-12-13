package pathCommands;

import java.io.FileInputStream;
import java.util.ArrayList;

import shapeComponents.Point;

public class Moveto extends Command
{
	private ArrayList<Point> points;
	
	public Moveto(){};
	
	@Override
	public String extract(FileInputStream fs) 
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void whoIam()
	{
		System.out.println("I am moveto command");
	}
	
	@Override
	public ArrayList<Point> getPoints() {
		return points;
	}
	
	@Override
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	
}
