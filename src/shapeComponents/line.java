package shapeComponents;

import pathCommands.Command;

public class line extends ShapeComponent
{
	
	
	public line(Point cursor, Command c) 
	{
		super(cursor, c);
		
		//start of the line
		this.addPoint(cursor);
		
		//end of the line
		this.addPoint(c.getPoints().get(0));
		
	}
	
	public line(Point cursor, Point lastSubPath) 
	{
		super();
		this.addPoint(cursor);
		this.addPoint(lastSubPath);
	}
	
	public line() 
	{super();}
	
	
	
	//print the current command
	@Override
	public void presentYourself()
	{
		System.out.println("Line from " + this.getPoints().get(0).presentYourself()+" to "+this.getPoints().get(1).presentYourself());
	}


}
