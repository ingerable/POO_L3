package shapeComponents;

import pathCommands.Command;

public class line extends ShapeComponent
{
	
	
	public line(Point cursor, Command c) 
	{
		super(cursor, c);
		
		//start of the line
		this.addPoint(new Point(cursor.getX(),cursor.getY()));
		
		//end of the line
		this.addPoint(new Point(c.getPoints().get(0).getX(),c.getPoints().get(0).getY()));
		
	}
	
	public line(Point cursor, Point lastSubPath) 
	{
		super();
		this.addPoint(new Point(cursor.getX(),cursor.getY())); // we have to instantiate new point because we do not want to copy the reference of cursor and lastSubPath
		this.addPoint(new Point(lastSubPath.getX(),lastSubPath.getY()));
	}
	
	public line() 
	{super();}
	
	
	//transform relative values to absolute values for a line
	public void  toAbsolute(Point cursor)
	{
		this.getPoints().get(1).addPoint(cursor);
	}
	
	
	//print the current command
	@Override
	public void presentYourself()
	{
		System.out.println("Line from " + this.getPoints().get(0).presentYourself()+" to "+this.getPoints().get(1).presentYourself());
	}
	
	//return the value of the shape component used to update the cursor
	@Override
	public Point getPositionForCursor()
	{
		return this.getPoints().get(1); 
	}
	


}
