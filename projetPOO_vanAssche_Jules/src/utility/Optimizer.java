package utility;

import java.util.ArrayList;
import java.util.Stack;

import shapeComponents.Board;
import shapeComponents.FinalShape;
import shapeComponents.Hitbox;
import shapeComponents.Point;

public class Optimizer 
{
	private ArrayList<FinalShape> allShapes;
	
	private Stack<Point> possiblePositionsAscending;
	
	private Stack<Point> possiblePositionsDescending;
	
	private Point position; 
	
	private float yMax;
		
	private Board board;
	
	private boolean largestFirst;
	
	@SuppressWarnings("unchecked")
	public Optimizer(Board b)
	{
		this.board = b;
		this.allShapes = (ArrayList) b.getShapes().clone();
		this.possiblePositionsAscending = new Stack<Point>();
		this.possiblePositionsDescending = new Stack<Point>();
		this.position = new Point(0.0f,0.0f);
		this.yMax = this.board.getHeight();
		this.largestFirst = true;
	}
	
	public void optimize()
	{
		//result of all the new positionned shapes
		ArrayList<FinalShape> optimizedShapes = new ArrayList<FinalShape>();
		
		int i =0;
		
		boolean descendingPhase = true;
		
		boolean initializationPhase = true;
		
		
		//sort hitbox by width
		if(this.largestFirst)
		{
			this.sortHitboxLargestFirst();
		}
		else
		{
			this.sortHitboxSmallerFirst();
		}
				
		while(!this.allShapes.isEmpty())
		{
			
			if(i==this.allShapes.size()) // end of phase or none hitbox fit the area
			{	
				if(initializationPhase) // end of initialization phase
				{
					descendingPhase=false;
					initializationPhase=false;
					this.position = this.possiblePositionsAscending.pop();
					i=0;
				}
				else if(descendingPhase) // end of descending  phase 
				{
					if(this.possiblePositionsDescending.size()>0)
					{
						position = this.possiblePositionsDescending.pop();
						i=0;
					}
					else
					{
						descendingPhase=false;
						this.position = this.possiblePositionsAscending.pop();
						i=0;
					}
					
				}
				else // end of ascending 
				{
					if(this.possiblePositionsAscending.size()>0)
					{
						position = this.possiblePositionsAscending.pop();
						i=0;
					}
					else
					{
						this.yMax = this.board.getHeight();
						this.position = this.possiblePositionsDescending.pop();
						descendingPhase=true;
						i=0;
					}
					
					
				}
			}
				
			
			
				if(hitboxFitIn(this.allShapes.get(i).getMyHitbox(),this.position,this.board.getWidth(),this.yMax))
				{
							
					//compute the translation vector to move the shape and its hitbox
					Point translation = Point.substractPoint(this.position,this.allShapes.get(i).getMyHitbox().getUpperLeftCorner());
					
					//translate hitbox and shape
					this.allShapes.get(i).translateTo(translation);
					this.allShapes.get(i).getMyHitbox().translateTo(translation);
					
					//add the shape in the list
					optimizedShapes.add(this.allShapes.get(i));
					
					//update the position
					if(initializationPhase)
					{
						this.position = new Point(this.position.getX(),this.allShapes.get(i).getMyHitbox().getHeight()+this.position.getY());
						this.possiblePositionsAscending.push(this.allShapes.get(i).getMyHitbox().getUpperRightCorner());
						this.allShapes.remove(i);
						i=0;
						
					}
					else if(descendingPhase && !initializationPhase)
					{
						this.possiblePositionsAscending.push(this.allShapes.get(i).getMyHitbox().getUpperRightCorner());
						this.allShapes.remove(i);
						i=0;
						if(this.possiblePositionsDescending.size()>0)
						{
							this.position = this.possiblePositionsDescending.pop();	
						}
						else
						{
							i = this.allShapes.size();
						}
						
					}
					else
					{
						this.yMax = this.allShapes.get(i).getMyHitbox().getUpperLeftCorner().getY();
						
						this.possiblePositionsDescending.push(this.allShapes.get(i).getMyHitbox().getUpperRightCorner());
						
						this.allShapes.remove(i);
						i=0;
						
						
						if(this.possiblePositionsAscending.size()>0) // check if there is still some point left
						{
							this.position = this.possiblePositionsAscending.pop();	
						}
						else // no more position left
						{
							i = this.allShapes.size();
						}
						
					}
					
					
					
				}
				else
				{
					i++;
				}	
		}
		this.board.setShapes(optimizedShapes);	
	}
	
	public boolean hitboxFitIn(Hitbox h, Point position , float xMax,float yMax)
	{
		
		if(h.getHeight()<yMax-position.getY() )//&& h.getWidth()<xMax-position.getX()) //if hitbox does not exceed the height of the area
		{
			return true;
		}
		return false;
		
	}
	
	
	//sort the hitboxesByPerimter(descending)
	public void sortHitboxLargestFirst()
	{
		int i, j;
			
		for(i=1; i < this.allShapes.size(); i++)
		{
			FinalShape sc = this.allShapes.get(i);
			
			j=i;
			
			while(j>0 && this.allShapes.get(j-1).getMyHitbox().getWidth()<sc.getMyHitbox().getWidth())
			{
				this.allShapes.set(j, this.allShapes.get(j-1));
				j=j-1;
			}
		this.allShapes.set(j, sc);
		}
		
		for(int m=0; m<this.allShapes.size();m++)
		{
			System.out.println(this.allShapes.get(m).getMyHitbox().getWidth());
		}
	}
	
	public void sortHitboxSmallerFirst()
	{
		int i, j;
			
		for(i=1; i < this.allShapes.size(); i++)
		{
			FinalShape sc = this.allShapes.get(i);
			
			j=i;
			
			while(j>0 && this.allShapes.get(j-1).getMyHitbox().getWidth()>sc.getMyHitbox().getWidth())
			{
				this.allShapes.set(j, this.allShapes.get(j-1));
				j=j-1;
			}
		this.allShapes.set(j, sc);
		}
		
		for(int m=0; m<this.allShapes.size();m++)
		{
			System.out.println(this.allShapes.get(m).getMyHitbox().getWidth());
		}
	}
	
	
	
	/*
	 * accesors
	 */
	public void setLargestFirst(boolean b)
	{
		this.largestFirst = b;
	}
	
	public boolean getLargestFirst()
	{
		return this.largestFirst;
	}
	
	public void setBoard(Board b)
	{
		this.board=b;
		this.yMax = this.board.getHeight();
	}
}
