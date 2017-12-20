package shapeComponents;

import java.util.ArrayList;

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
