package pathCommands;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import shapeComponents.Point;

public class Command 
{	
	private ArrayList<Point> points;
	
	//get the implicit command of the current command
	public char getImplicitCommand()
	{return ' ';};
	
	//char that represent the type of the command
	private char charType;
	
	//has relative or absolute position
	private boolean isRelative;
	
	//is the last command of the path
	private boolean isLast;
	
	public Command(char c, boolean b)
	{
		this.points = new ArrayList<Point>();
		this.charType=c;
		this.isRelative=b;
		this.isLast=false;
	};
	
	//used to initialize the loop in the parser
	public Command()
	{
		this.isLast=false;
	};
	
	//return the type of the command depending on the argument c 
	public static Command getType(char c)
	{
		Command cmd=null;
		switch(c)
		{
			case'm':
				cmd=new Moveto(false);
				break;
			case'z':
				cmd=new Closepath(true);
				break;
			case'l':
				cmd=new Lineto(true);
				break;
			case'c':
				cmd=new Bezier(false,true);
				break;
			case'p':
				cmd=new Bezier(true,true);
				break;
			case'M':
				cmd=new Moveto(false);
				break;
			case'Z':
				cmd=new Closepath(false);
				break;
			case'L':
				cmd=new Lineto(false);
				break;
			case'C':
				cmd=new Bezier(false,false);
				break;			
		}
		return cmd;
	}
	
	//get the point for the current command with the given file stream, the last argument indicate if the there is space or not before we read 
	public FileReader extractPoints(FileReader in,char firstNumber) throws IOException
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
		while(c!='"' && c!=' ')
		{
			y+=c;
			c=(char)in.read();
		}
		
		//System.out.println(x+" "+y);
		
		//we reached the last command of the path (case where we already read the end of path char (") and we must advertise the parser)
		if(c=='"')
		{
			this.isLast=true;
			
		}
		
		this.addPoint(new Point(Float.parseFloat(x),Float.parseFloat(y)));
		
		return in;
		
	}
	
	
	//print the current command
	public void presentYourself()
	{
		System.out.println("Command: "+this.charType + ", relative: "+this.isRelative+ " isLast :"+this.isLast);
		
		for(int i=0;i<this.points.size();i++)
		{
			System.out.print(this.points.get(i).getX()+",");
			System.out.print(+this.points.get(i).getY()+"\n");
		}
	}
	
	
	
	/*
	 * Accessors
	 */
		
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

	public boolean isRelative() {
		return isRelative;
	}

	public void setRelative(boolean isRelative) {
		this.isRelative = isRelative;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}
}
