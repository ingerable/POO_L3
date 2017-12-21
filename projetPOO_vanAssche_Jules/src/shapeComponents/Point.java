package shapeComponents;

public class Point {

	private float x;
	private float y;
	
	public Point(float x, float y) 
	{
		this.x=x;
		this.y=y;
	}
	
	public void addPoint(Point p)
	{
		this.x += p.getX();
		this.y += p.getY();
	}
	
	public static Point substractPoint(Point p1, Point p2)
	{
		return new Point(p1.getX()-p2.getX(),p1.getY()-p2.getY());
	}
	
	public float getY() 
	{
		return y;
	}
	public void setY(float y) 
	{
		this.y = y;
	}
	public float getX() 
	{
		return x;
	}
	public void setX(float x) 
	{
		this.x = x;
	}
	
	public String presentYourself()
	{
		return new String("("+this.x+","+this.y+")");
	}
	
}
