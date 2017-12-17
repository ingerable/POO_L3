package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Path2D;

import javax.swing.JPanel;

import pathCommands.Bezier;
import pathCommands.Closepath;
import pathCommands.Command;
import pathCommands.Lineto;
import pathCommands.Moveto;
import pathCommands.Path;
import shapeComponents.FinalShape;
import shapeComponents.Point;
import shapeComponents.ShapeComponent;

public class OptimizationWindow extends JPanel
{

	private FinalShape shape;
	
	
	
	public OptimizationWindow(FinalShape s)
	{
		super();
		this.shape=s;
	}
		

	
	//draw a bezier curve from cursor to a using c1 as control point and c2 as control point for the end of the curve
	public void curveTo(ShapeComponent s)
	{
		

	}
	
	public void drawLine(ShapeComponent s, Graphics g)
	{
		int x1 = (int)s.getPoints().get(0).getX();
		int x2 = (int)s.getPoints().get(1).getX();
		int y1 = (int)s.getPoints().get(0).getY();
		int y2 = (int)s.getPoints().get(1).getX();
		
		g.setColor(new Color(105,105,105));
		g.drawLine(x1, y1, x2, y2);
		
	}
	  
	  @Override
	  protected void paintComponent(Graphics g) 
	  {
	      super.paintComponent(g);
	      for(ShapeComponent s : this.shape.getComponents())
	      {
	    	  drawLine(s,g);
	      }
	      
	      
	  }
	  
	
}
