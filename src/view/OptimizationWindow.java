package view;

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
import shapeComponents.Point;

public class OptimizationWindow extends JPanel
{

	private Path myPath;
	
	
	
	public OptimizationWindow(Path p)
	{
		this.myPath = p;
		
		Point cursor = new Point(0,0);
		
	}
		

	
	//draw a bezier curve from cursor to a using c1 as control point and c2 as control point for the end of the curve
	public void curveTo(Point cursor, Command cmd)
	{
		

	}
	

	public Path getMyPath() 
	{
		return myPath;
	}

	public void setMyPath(Path myPath) 
	{
		this.myPath = myPath;
	}
}
