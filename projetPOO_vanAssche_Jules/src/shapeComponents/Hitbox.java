package shapeComponents;

public class Hitbox extends FinalShape 
{
	private float perimeter;
	
	private float width;
	
	private float height;
	
	public Hitbox(float p, float w, float h)
	{
		super();
		this.perimeter=p;
		this.width=w;
		this.height=h;
	}
	
	public void rescale(float scale)
	{
		for(ShapeComponent sc : this.getComponents())
		{
			for(Point p : sc.getPoints())
			{
				Point temp = new Point(p.getX()*scale,p.getY()*scale);
				p.setX(temp.getX());
				p.setY(temp.getY());
			}
		}
		this.perimeter *= scale;
		this.width *= scale;
		this.height *= scale;
	}
	/*
	 * accessors
	 */
	public Point getUpperLeftCorner()
	{
		return this.getComponents().get(0).getPoints().get(0);
	}
	
	public Point getUpperRightCorner()
	{
		return this.getComponents().get(0).getPoints().get(1);
	}
	
	public float getPerimeter() 
	{
		return perimeter;
	}

	public void setPerimeter(float perimeter) 
	{
		this.perimeter = perimeter;
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


}
