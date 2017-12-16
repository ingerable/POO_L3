package shapeComponents;

import java.util.ArrayList;

import pathCommands.Command;
import pathCommands.Path;

public class ShapeComponent 
{
	private ArrayList<Point> points;
	
	//build a shape component based on the command and the cursor(current position in the space)
	public ShapeComponent(Point cursor, Command c)
	{
		this.points = new ArrayList<Point>();
	}
	
	public ShapeComponent()
	{}

	//print the current command
	public void presentYourself()
	{}
		
	public void addPoint(Point p)
	{
		this.points.add(p);
	}
	
	public ArrayList<Point> getPoints() 
	{
		return points;
	}

	public void setPoints(ArrayList<Point> points) 
	{
		this.points = points;
	}
}
