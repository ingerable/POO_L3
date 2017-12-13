package pathCommands;

import java.io.FileInputStream;
import java.util.ArrayList;

import shapeComponents.Point;

public class Command 
{	
	private ArrayList<Point> points;
	
	//extract the string command from the fs file
	public String extract(FileInputStream fs){ return "";};
	
	public void whoIam(){};
	
	public Command(){};
	
	//return the type of the command depending on the argument c
	public static Command getType(char c)
	{
		Command cmd=null;
		switch(c)
		{
			case'm':
				cmd=new Moveto();
				break;
			case'z':
				cmd=new Closepath();
				break;
			case'l':
				cmd=new Lineto();
				break;
			case'c':
				cmd=new Bezier();
				break;	
		}
		return cmd;
	}
	
	
	public ArrayList<Point> getPoints() 
	{
		return points;
	}
	
	
	public void setPoints(ArrayList<Point> points) 
	{
		this.points = points;
	}
	
	public void addPoint(Point p)
	{
		this.points.add(p);
	}
}
