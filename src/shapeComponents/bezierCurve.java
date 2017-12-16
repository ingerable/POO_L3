package shapeComponents;

import pathCommands.Command;

public class bezierCurve extends ShapeComponent
{
	private Point control_1;
	private Point control_2;
	private Point endPoint;
	private Point begginingPoint;
	
	public bezierCurve(Command c, Point cursor)
	{
		super(cursor,c);
		
		//temporary attribute to compute 
		this.begginingPoint=cursor;
		this.control_1 = new Point(c.getPoints().get(0).getX(),c.getPoints().get(0).getY());
		this.control_2 = new Point(c.getPoints().get(1).getX(),c.getPoints().get(1).getY());
		this.endPoint = new Point(c.getPoints().get(2).getX(),c.getPoints().get(2).getY());
		
		updateList(); // we still have to use the list to store the points
	}
	
	
	
	public Point point_t(double t)
	{
		double x = Math.pow(3,t) * this.begginingPoint.getX()
				+ 3*Math.pow(2, t)*(1-t)*this.control_1.getX()
				+ 3*Math.pow(2,(1-t))*this.control_2.getX()
				+ Math.pow(3, (1-t)) * this.endPoint.getX();

		double y = Math.pow(3,t) * this.begginingPoint.getY()
				+ 3*Math.pow(2, t)*(1-t)*this.control_1.getY()
				+ 3*Math.pow(2,(1-t))*this.control_2.getY()
				+ Math.pow(3, (1-t)) * this.endPoint.getY();
		
		return new Point((float)x,(float)y);
	}
	
	
	public void updateList()
	{
		this.getPoints().add(this.begginingPoint);
		this.getPoints().add(this.control_1);
		this.getPoints().add(this.control_2);
		this.getPoints().add(this.endPoint);
	}
	
	
	//print the current command
		@Override
		public void presentYourself()
		{
			System.out.println("Bezier curve from " + this.begginingPoint.presentYourself()+" to "+this.endPoint.presentYourself());
			System.out.println("with control point from " + this.control_1.presentYourself()+" to "+this.control_2.presentYourself());
		}
		
		
	/*
	 * accesors	
	 */
	
	public Point getControl_1() 
	{
		return control_1;
	}
	public void setControl_1(Point control_1) 
	{
		this.control_1 = control_1;
	}
	public Point getControl_2() 
	{
		return control_2;
	}
	public void setControl_2(Point control_2) 
	{
		this.control_2 = control_2;
	}
	public Point getEndPoint() 
	{
		return endPoint;
	}
	public void setEndPoint(Point endPoint) 
	{
		this.endPoint = endPoint;
	}
	public Point getBegginingPoint() 
	{
		return begginingPoint;
	}
	public void setBegginingPoint(Point begginingPoint) 
	{
		this.begginingPoint = begginingPoint;
	}
}
