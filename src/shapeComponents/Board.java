package shapeComponents;
import java.util.ArrayList;



public class Board 
{
	private ArrayList<FinalShape> shapes;
	
	
	public Board(ArrayList<FinalShape> s)
	{
		this.shapes=s;
	}
	
	
	public ArrayList<FinalShape> getShapes() 
	{
		return shapes;
	}

	public void setShapes(ArrayList<FinalShape> shapes) 
	{
		this.shapes = shapes;
	}
	
	public void toPositive()
	{
		Point p1 = new Point(0,0);
		for(FinalShape sc : this.shapes)
		{
			if(p1.getX()>sc.getMin().getX())
			{
				p1 = new Point(sc.getMin().getX(),p1.getY());
			}
			
			if(p1.getY()>sc.getMin().getY())
			{
				p1 = new Point(p1.getX(),sc.getMin().getY());
			}
		}
		
		for(FinalShape sc : this.shapes) // for all shapes in the board
		{
			for(ShapeComponent c : sc.getComponents()) // for each component of a shape
			{
				for(Point p : c.getPoints())
				{
					p.addPoint(new Point(Math.abs(p1.getX()),Math.abs(p1.getY())));
				}
			}
		}
	}
	
	public void rescale(float rescaleX,float rescaleY)
	{
		
		for(FinalShape sc : this.shapes) // for all shapes in the board
		{
			for(ShapeComponent c : sc.getComponents()) // for each component of a shape
			{
				for(Point p : c.getPoints())
				{
					Point temp = new Point(p.getX()*rescaleX,p.getY()*rescaleY);
					p.setX(temp.getX());
					p.setY(temp.getY());
				}
			}
		}
	}
}
