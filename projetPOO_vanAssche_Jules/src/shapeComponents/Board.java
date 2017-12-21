package shapeComponents;
import java.util.ArrayList;



public class Board 
{
	private ArrayList<FinalShape> shapes;
	
	private float height;
	
	private float width;
	
	private FinalShape roll;
	
	public Board(ArrayList<FinalShape> s,float h, float w)
	{
		this.shapes=s;
		this.height = h;
		this.width=w;
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
	
	//rescale all the shapes of the board
	public void rescale(float rescale)
	{
		
		
		for(FinalShape sc : this.shapes) // for all shapes in the board
		{
			for(ShapeComponent c : sc.getComponents()) // for each component of a shape
			{
				for(Point p : c.getPoints())
				{
					Point temp = new Point(p.getX()*rescale,p.getY()*rescale);
					p.setX(temp.getX());
					p.setY(temp.getY());
				}
			}
			sc.getMyHitbox().rescale(rescale);
		}
		
		// do it for the window aswell 
		for(ShapeComponent c : this.roll.getComponents()) // for each component of a shape
		{
			for(Point p : c.getPoints())
			{
				Point temp = new Point(p.getX()*rescale,p.getY()*rescale);
				p.setX(temp.getX());
				p.setY(temp.getY());
			}
		}
		
		this.height *=rescale;
		this.width *=rescale;
		
	}
	
	//calculate the hitbox for all the shapes in the board
	public void calculateAreaOfAllShapes()
	{
		for(FinalShape sc : this.shapes)
		{
			sc.calculateArea();
		}
	}
	
	//widow that respresent the initial size of the svg
	public void addWindow(float width, float height)
	{
		FinalShape window = new FinalShape();
		window.addComponent(new line(new Point(0.0f,0.0f),new Point(width,0.0f)));
		window.addComponent(new line(new Point(width,0.0f),new Point(width,height)));
		window.addComponent(new line(new Point(width,height),new Point(0.0f,height)));
		window.addComponent(new line(new Point(0.0f,height),new Point(0.0f,0.0f)));
		this.roll = window;
	}


	public float getWidth() {
		return width;
	}


	public void setWidth(float width) {
		this.width = width;
	}


	public float getHeight() {
		return height;
	}


	public void setHeight(float height) {
		this.height = height;
	}


	public FinalShape getRoll() {
		return roll;
	}


	public void setRoll(FinalShape roll) {
		this.roll = roll;
	}
}
