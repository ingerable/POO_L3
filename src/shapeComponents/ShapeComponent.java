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
	{
		this.points = new ArrayList<Point>();
	}

	//print the current command
	public void presentYourself()
	{}
		
	//get the minimum value for x 
	public float getMinX()
	{
		float min=0.0f;
		for(Point p : this.points)
		{
			if(p.getX()<min)
			{
				min = p.getX();
			}
		}
		return min;
	}
	
	//get the minimum value for y
	public float getMinY()
	{
		float min=0.0f;
		for(Point p : this.points)
		{
			if(p.getY()<min)
			{
				min = p.getY();
			}
		}
		return min;
	}
	
	//get the maximum value for x 
	public float getMaxX()
	{
		float max=0.0f;
		for(Point p : this.points)
		{
			if(p.getX()>max)
			{
				max = p.getX();
			}
		}
		return max;
	}
	
	//get the maximum value for y
	public float getMaxY()
	{
		float max=0.0f;
		for(Point p : this.points)
		{
			if(p.getY()>max)
			{
				max = p.getY();
			}
		}
		return max;
	}
	
	//transform the coordinate of a shapeComponent from relative to absolute
	public void  toAbsolute(Point cursor)
	{}
	
	//return the value of the shape component used to update the cursor
	public Point getPositionForCursor(){return null;}
	
	//identify the type of the child
	public char whoiam()
	{
		return  ' ';
	}
	
	//method for bezier curve
	public Point point_t(double t)
	{
		return new Point(0,0);
	}
	/*
	 * accessors
	 */
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
