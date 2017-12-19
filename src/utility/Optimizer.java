package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import shapeComponents.Board;
import shapeComponents.FinalShape;
import shapeComponents.Hitbox;
import shapeComponents.Point;
import shapeComponents.line;

public class Optimizer 
{
	private ArrayList<FinalShape> allShapes;
	
	private Stack<Float> gridX;
	
	private Stack<Float> gridY;
	
	private Board board;
	
	public Optimizer(Board b)
	{
		this.allShapes = (ArrayList) b.getShapes().clone();
		this.gridX = new Stack<Float>();
		this.gridY = new Stack<Float>();
		this.gridX.add(0.0f);
		this.gridY.add(0.0f);
		this.board = board;
	}
	
	public void Optimize()
	{
		Hitbox tmp;
		while(this.allShapes.size()>0)
		{
			/*tmp = this.board.getShapes().get(this.getBiggestHitbox()).getMyHitbox();
			
			//if the hitbox does not exceed the width of the actual grid
			if(tmp.getHeight()>)
			{
				
			}*/
			
		}
	}
	
	//get the biggest hitbox of the current board
	public int getBiggestHitboxThatFitInArea()
	{
		float perimeter = 0.0f;
		
		int index = 0;
		
		for(int i=0;i<this.allShapes.size();i++)
		{
			if(this.allShapes.get(i).getMyHitbox().getPerimeter()>perimeter)
			{
				index = i;
			}
		}
		return index;
	}
	
	//sort the hitboxesByPerimter(descending)
	public void sortHitbox()
	{
		int i = 0;
		while(i!=this.allShapes.size()-1)
		{
			if(this.allShapes.get(i).getMyHitbox().getPerimeter()>this.allShapes.get(i+1).getMyHitbox().getPerimeter())
			{
				Collections.swap(this.allShapes, i, i+1);
			}
			i++;
		}
		
	}
	
	
}
