package shapeComponents;

public class Hitbox extends FinalShape 
{
	private float perimeter;
	
	private float width;
	
	private float height;
	
	public Hitbox()
	{
		super();
		this.computePerimeter();
	}

	
	public void computePerimeter()
	{
		/*Point max = this.getMax();
		Point min = this.getMin();
		float width = max.getX()-min.getX();
		float height = max.getX()-min.getX();
		this.width = width;
		this.height = height;
		this.perimeter = (2*width)+(2*height);*/
	}
	
	
	
	/*
	 * accessors
	 */
	
	
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
