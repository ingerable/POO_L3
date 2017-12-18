package shapeComponents;

import java.util.ArrayList;

import pathCommands.Command;
import pathCommands.Path;

public class FinalShape 
{
	//all the components of the final shape
	private ArrayList<ShapeComponent> components;
	
	//path associated to the final shape
	private Path path;
	
	//the "hitbox" of the final shape
	private FinalShape myHitbox;
	
	public FinalShape(Path p)
	{
		this.path = p;
		this.components = new ArrayList<ShapeComponent>();
		this.pathToShape();
		
	}
	
	public FinalShape()
	{
		this.components = new ArrayList<ShapeComponent>();	
	}

	// transform path into shape
	public void pathToShape()
	{
		//current position in the space
		Point cursor = new Point(0,0);
		
		//position of the last subpath
		Point lastSubPath = new Point(0,0);
		
		//for each command in the path
		for(Command cmd : this.path.getCommand())
		{
			ShapeComponent sc = null;
						
			if(cmd.getPoints().size()>1) // polyline or polybzier
			{
				switch(cmd.getCharType())
				{
					case'l':
						sc=new line(cursor,cmd); // first line
						sc.toAbsolute(cursor);
						this.addComponent(sc);
						cursor = sc.getPositionForCursor(); //update the cursor
						for(int i=1; i<cmd.getPoints().size();i++) // use the end of the last line to create the beggining of the new one
						{
							sc = new line(sc.getPoints().get(1),cmd.getPoints().get(i));
							sc.toAbsolute(cursor);
							cursor = sc.getPositionForCursor(); //update the cursor
							this.addComponent(sc);
						}
						break;
					case'L':
						sc=new line(cursor,cmd); // first line
						this.addComponent(sc);
						for(int i=1; i<cmd.getPoints().size();i++) // use the end of the last line to create the beggining of the new one
						{
							sc = new line(sc.getPoints().get(1),cmd.getPoints().get(i));
							this.addComponent(sc);
							cursor = sc.getPositionForCursor();
						}
						
						break;
					case'c':
						// iterate all the 3 points of the polybezier curve, use the end position of the last bezier curve to start the new one
						sc = new bezierCurve(cmd,cursor,0);
						sc.toAbsolute(cursor);
						this.addComponent(sc);
						cursor = sc.getPositionForCursor(); //update the cursor
						for(int i=3; i<(cmd.getPoints().size());i=i+3)
						{
							sc = new bezierCurve(cmd,sc.getPoints().get(3),i);
							sc.toAbsolute(cursor);
							this.addComponent(sc);
							cursor = sc.getPositionForCursor(); //update the cursor
						}
						break;	
					case'C':
						// iterate all the 3 points of the polybezier curve, use the end position of the last bezier curve to start the new one
						sc = new bezierCurve(cmd,cursor,0);
						this.addComponent(sc);
						for(int i=3; i<(cmd.getPoints().size());i=i+3)
						{
							sc = new bezierCurve(cmd,sc.getPoints().get(3),i);
							this.addComponent(sc);
							cursor = sc.getPositionForCursor();
						}
						
						break;			
				}
			}
			else // closepath, single bezier curve or single lineto
			{
				//create the shape component object that will have absolute values 
				switch(cmd.getCharType())
				{
					case'z':
					case'Z':
						sc=new line(cursor,lastSubPath);
						this.addComponent(sc);
						break;
					case'l':
						sc=new line(cursor,cmd);
						sc.toAbsolute(cursor);
						this.addComponent(sc);
						cursor = sc.getPositionForCursor(); //update the cursor
						break;
					case'L':
						sc=new line(cursor,cmd);
						this.addComponent(sc);
						cursor = sc.getPositionForCursor();
						break;
					case'c':
						sc=new bezierCurve(cmd,cursor,0);
						sc.toAbsolute(cursor);
						this.addComponent(sc);
						cursor = sc.getPositionForCursor(); //update the cursor
						break;	
					case'C':
						sc=new bezierCurve(cmd,cursor,0);
						this.addComponent(sc);
						cursor = sc.getPositionForCursor();
						break;			
				}
			}
			
			if(cmd.getCharType()=='M' || cmd.getCharType()=='m') // update the cursor but also the subpath
			{
				if(cmd.getCharType()=='M' || !cmd.isRelative())
				{
					lastSubPath = new Point(cmd.getPosition().getX(),cmd.getPosition().getY());
					cursor = new Point(cmd.getPosition().getX(),cmd.getPosition().getY()); // update the cursor
				}
				else if(cmd.getCharType()=='m' && cmd.isRelative())
				{
					lastSubPath = new Point(cmd.getPosition().getX()+cursor.getX(),cmd.getPosition().getY()+cursor.getY());
					cursor = new Point(lastSubPath.getX(),lastSubPath.getY());
				}
				else
				{
					lastSubPath = new Point(cmd.getPosition().getX(),cmd.getPosition().getY());
					cursor = new Point(cmd.getPosition().getX(),cmd.getPosition().getY()); // update the cursor
				}
			}
			else if(cmd.getCharType()=='z')
			{
				
			}

			
			
		}
	}
	
	
	
	//show the values of all the component of the current shape
	public void printShapeComponents()
	{
		for(ShapeComponent sc : this.components)
		{
			sc.presentYourself();
		}
	}
	
	
	//look for the minimum value and add it to all shape component ( to make all the coordinates stricly positive)
	public Point getMin()
	{
		float minX=this.getMax().getX();
		float minY=this.getMax().getY();
		
		//look for the minimum value for x and y
		for(ShapeComponent s : this.components)
		{
			if(s.getMinX()<minX)
			{
				minX=s.getMinX();
			}
			
			if(s.getMinY()<minY)
			{
				minY=s.getMinY();
			}
		}
				
		return new Point(minX,minY);
	}
	
	public void calculateArea()
	{
		Point min = this.getMin();
		Point max = this.getMax();
		FinalShape hitbox = new FinalShape();
		hitbox.addComponent(new line(new Point(min.getX(),min.getY()),new Point(max.getX(),min.getY())));
		hitbox.addComponent(new line(new Point(max.getX(),min.getY()),new Point(max.getX(),max.getY())));
		hitbox.addComponent(new line(new Point(max.getX(),max.getY()),new Point(min.getX(),max.getY())));
		hitbox.addComponent(new line(new Point(min.getX(),max.getY()),new Point(min.getX(),min.getY())));
		this.setMyHitbox(hitbox);
	}
	
	//look for the maximum value 
	public Point getMax()
	{
		float maxX=0.0f;
		float maxY=0.0f;
			
		//look for the minimum value for x and y
		for(ShapeComponent s : this.components)
		{
			if(s.getMaxX()>maxX)
			{
				maxX=s.getMaxX();
			}
				
			if(s.getMinY()>maxY)
			{
				maxY=s.getMaxY();
			}
		}
					
		return new Point(maxX,maxY);
	}
				
	
	/*
	 * accessors
	 */
	
	public Path getPath() 
	{
		return path;
	}

	public void setPath(Path path) 
	{
		this.path = path;
	}

	public ArrayList<ShapeComponent> getComponents() 
	{
		return components;
	}

	public void setComponents(ArrayList<ShapeComponent> components) 
	{
		this.components = components;
	}
	
	public void addComponent(ShapeComponent sc) 
	{
		this.components.add(sc);
	}

	public FinalShape getMyHitbox() {
		return myHitbox;
	}

	public void setMyHitbox(FinalShape myHitbox) {
		this.myHitbox = myHitbox;
	}
}
