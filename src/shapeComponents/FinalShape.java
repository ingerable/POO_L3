package shapeComponents;

import java.util.ArrayList;

import pathCommands.Command;
import pathCommands.Path;

public class FinalShape 
{
	private ArrayList<ShapeComponent> components;
	
	private Path path;
	
	public FinalShape(Path p)
	{
		this.path = p;
		this.components = new ArrayList<ShapeComponent>();
		this.pathToShape();
		
	}

	// transform path into shape
	public void pathToShape()
	{
		//current position in the space
		Point cursor = new Point(0,0);
		
		//position of the last subpath
		Point lastSubPath = new Point(0,0);
		
		//for each command in the path
		for(Command cmd : this.path.getCommand())
		{
			ShapeComponent sc = null;
			
			//if cmd has relative values, we have to update the cursor based on those values and the last value of cursor
			if(cmd.isRelative())
			{
				cursor.addPoint(cmd.getPosition()); // update the cursor
				
				//start of new sub-Path
				if(cmd.getCharType()=='m')
				{
					lastSubPath = cursor;
				}
			}
			else // cmd has absolute values we have to update the cursor based on the absolute value
			{
				cursor = cmd.getPosition(); // update the cursor
				//start of new sub-Path
				if(cmd.getCharType()=='M' || cmd.getCharType()=='m')
				{
					lastSubPath = cursor;
				}
				else if(cmd.getCharType()=='z') // ! ! z position return 0,0 , we must not assign that value to the cursor
				{
					// do nothing
				}
			}
			
			//create the shape component object that will have absolute values 
			switch(cmd.getCharType())
			{
				case'z':
				case'Z':
					sc=new line(cursor,lastSubPath);
					this.addComponent(sc);
					break;
				case'l':
				case'L':
					sc=new line(cursor,cmd);
					this.addComponent(sc);
					break;
				case'c':
				case'C':
					sc=new bezierCurve(cmd,cursor);
					this.addComponent(sc);
					break;			
			}
			
		}
	}
	
	
	
	//show the values of all the component of the current shape
	public void printShapeComponents()
	{
		for(ShapeComponent sc : this.components)
		{
			sc.presentYourself();
		}
	}
	
	
	/*
	 * accessors
	 */
	
	public Path getPath() 
	{
		return path;
	}

	public void setPath(Path path) 
	{
		this.path = path;
	}

	public ArrayList<ShapeComponent> getComponents() 
	{
		return components;
	}

	public void setComponents(ArrayList<ShapeComponent> components) 
	{
		this.components = components;
	}
	
	public void addComponent(ShapeComponent sc) 
	{
		this.components.add(sc);
	}
}
