package pathCommands;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import shapeComponents.Point;

public class Command 
{	
	private ArrayList<Point> points;
	
	//extract the string command from the fs file
	public FileInputStream extract(FileInputStream fs){ return null;};
	
	public char getImplicitCommand()
	{return ' ';};
	
	private char charType;
	
	public Command(char c)
	{
		this.points = new ArrayList<Point>();
		this.charType=c;
	};
	
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
			default:			
		}
		return cmd;
	}
	
	//get the point for the current command with the given file stream, the last argument indicate if the there is space or not before we read 
	public FileInputStream getPoint(FileInputStream in,char firstNumber) throws IOException
	{
		String x="";
		String y="";
		
		char c = firstNumber;
		
		//get the x coordinate
		while(c!=',')
		{
			x+=c;
			c=(char)in.read();
		}
		
		c=(char)in.read(); // skip the , delimiter between 2 point
		
		//get the y coordinate
		while(c!=' ')
		{
			y+=c;
			c=(char)in.read();
		}
			
		System.out.println(Float.parseFloat(x)+" "+Float.parseFloat(y));
		
		this.addPoint(new Point(Float.parseFloat(x),Float.parseFloat(y)));
		
		return in;
		
	}
	
	public void presentYourself()
	{
		System.out.println("Command: "+this.charType);
		
		for(int i=0;i<this.points.size();i++)
		{
			System.out.print(this.points.get(i).getX()+",");
			System.out.print(+this.points.get(i).getY()+"\n");
		}
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

	public char getCharType() 
	{
		return this.charType;
	}

	public void setCharType(char charType) 
	{
		this.charType = charType;
	}
}
