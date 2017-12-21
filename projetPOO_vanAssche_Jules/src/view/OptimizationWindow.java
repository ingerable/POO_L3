package view;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import shapeComponents.Board;
import shapeComponents.FinalShape;
import shapeComponents.Point;
import shapeComponents.ShapeComponent;

public class OptimizationWindow extends JPanel
{

	private Board board;
	
	private Boolean hitbox;
	
	public OptimizationWindow(Board b)
	{
		super();
		this.board=b;
		this.hitbox=false;
	}
		
	
	
	public void drawLine(ShapeComponent s, Graphics g)
	{
		int x1 = (int)s.getPoints().get(0).getX();
		int x2 = (int)s.getPoints().get(1).getX();
		int y1 = (int)s.getPoints().get(0).getY();
		int y2 = (int)s.getPoints().get(1).getY();
		
		g.setColor(new Color(105,105,105));
		g.drawLine(x1, y1, x2, y2);
		
	}
	  
	  @Override
	  protected void paintComponent(Graphics g) 
	  {
		  this.removeAll(); //clear first
		  
	      super.paintComponent(g);
	      
	      g.clearRect(0, 0, this.getWidth(), this.getHeight());
	      for(FinalShape f : this.board.getShapes())
	      {    
	    	  if(this.hitbox==true)
	    	  {
	    			  //hitbox
	    	    	  for(ShapeComponent s : f.getMyHitbox().getComponents())
	    	    	  {
	    	    		  drawLine(s,g);
	    	    	  }
	    	  }
	    	  
	    	  //draw all the shapes
	    	  for(ShapeComponent sc : f.getComponents())
		      {
	    		  if(sc.whoiam()=='l')
	    		  {
	    			  drawLine(sc,g);
	    		  }
	    		  else if(sc.whoiam()=='b')
	    		  {
	    			  double t = 0.0;
	    			  while(t<1)
	    			  {
	    				  Point temp = sc.point_t(t);
	    				  g.drawLine((int)temp.getX(),(int)temp.getY(),(int)temp.getX(),(int)temp.getY());
	    				  t+=0.001;
	    			  }
	    		  }
		      }
	      }
	      
	      //draw the window that represent the roll
	      for(ShapeComponent scc : board.getRoll().getComponents())
	      {
	    	  drawLine(scc,g);
	      }
	    
	  }	
	  
	  public void setHitbox(Boolean b)
	  {
		  this.hitbox=b;
	  }
	  
	  public void setBoard(Board b)
	  {
		  this.board=b;
	  }
	  
	  public boolean getHitbox()
	  {
		  return this.hitbox;
	  }
	  
}
