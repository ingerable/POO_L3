package shapeComponents;

import pathCommands.Command;

public class bezierCurve extends ShapeComponent
{

	
	public bezierCurve(Command c, Point cursor, int point)
	{
		super(cursor,c);
		
		//add the begginig, control point 1, control point 2 and the final point
		this.addPoint(new Point(cursor.getX(),cursor.getY()));
		this.addPoint(new Point(c.getPoints().get(point).getX(),c.getPoints().get(point).getY()));
		this.addPoint(new Point(c.getPoints().get(point+1).getX(),c.getPoints().get(point+1).getY()));
		this.addPoint(new Point(c.getPoints().get(point+2).getX(),c.getPoints().get(point+2).getY()));
		
	}
	

	public Point point_t(double t)
	{
		double tmp = 1-t;
		double x = Math.pow(t,3) * this.getBegginingPoint().getX()
				+ 3*Math.pow(t,2)*tmp*this.getControl_1().getX()
				+ 3*t*Math.pow(tmp,2)*this.getControl_2().getX()
				+ Math.pow(tmp,3) * this.getEndPoint().getX();

		double y = Math.pow(t,3) * this.getBegginingPoint().getY()
				+ 3*Math.pow(t,2)*tmp*this.getControl_1().getY()
				+ 3*t*Math.pow(tmp,2)*this.getControl_2().getY()
				+ Math.pow(tmp,3) * this.getEndPoint().getY();
		
		return new Point((float)x,(float)y);
	}
	
		
	
	//print the current command
	@Override
	public void presentYourself()
	{
		System.out.println("Bezier curve from " + this.getBegginingPoint().presentYourself()+" to "+this.getEndPoint().presentYourself());
		System.out.println("with control point from " + this.getControl_1().presentYourself()+" to "+this.getControl_2().presentYourself());
	}
	
	//transform relative values to absolute values for a bezier curve
	public void  toAbsolute(Point cursor)
	{
		this.getPoints().get(1).addPoint(cursor);
		this.getPoints().get(2).addPoint(cursor);
		this.getPoints().get(3).addPoint(cursor);
	}
	
	//return the value of the shape component used to update the cursor
	@Override
	public Point getPositionForCursor()
	{
		return this.getPoints().get(3); 
	}
	
	//identify the type of the child
	@Override
	public char whoiam()
	{
		return  'b';
	}
	

	//get the minimum value for x 
	public float getMinX()
	{
		double t =0.0;
		float minX = this.getMaxX();
		Point temp;
		while(t<1)
		{
			temp = this.point_t(t);
			if(temp.getX()<minX)
			{
				minX=temp.getX();
			}
			t+=0.01;
		}
		
		return minX;
	}
	
	//get the minimum value for y
	public float getMinY()
	{
		double t =0.0;
		float minY = this.getMaxY();
		Point temp;
		while(t<1)
		{
			temp = this.point_t(t);
			if(temp.getY()<minY)
			{
				minY=temp.getY();
			}
			t+=0.01;
		}
		
		return minY;
	}
	
	//get the maximum value for x 
	public float getMaxX()
	{
		double t =0.0;
		float maxX = 0.0f;
		Point temp;
		while(t<1)
		{
			temp = this.point_t(t);
			if(temp.getX()>maxX)
			{
				maxX=temp.getX();
			}
			t+=0.01;
		}
		
		return maxX;
	}
	
	//get the maximum value for y
	public float getMaxY()
	{
		double t =0.0;
		float maxY = 0.0f;
		Point temp;
		while(t<1)
		{
			temp = this.point_t(t);
			if(temp.getY()>maxY)
			{
				maxY=temp.getY();
			}
			t+=0.01;
		}
		
		return maxY;
}

		
	/*
	 * accesors	
	 */
	
	public Point getControl_1() 
	{
		return this.getPoints().get(1);
	}

	public Point getControl_2() 
	{
		return this.getPoints().get(2);
	}

	public Point getEndPoint() 
	{
		return this.getPoints().get(3);
	}

	public Point getBegginingPoint() 
	{
		return this.getPoints().get(0);
	}

}
