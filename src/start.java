import java.awt.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import pathCommands.Path;
import shapeComponents.Board;
import shapeComponents.FinalShape;

import view.Gui;


public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Parser p = Parser.getParser();
		p.setFile(args[0]);
		try {
			int nbrPath = p.numberOfPattern("<path");
			
			System.out.println("nbrPath :"+ nbrPath);
			
			FileReader in = new FileReader(p.getFile());
			
			for(int i=0; i<nbrPath;i++)
			{
				p.extractPathCommands(in);
			}
			in.close();
			
			//list of shapes
			ArrayList<FinalShape> shapes = new ArrayList<FinalShape>();
			
			Path p1 = p.getPaths().get(0);
			
			/*p1.printCommands();
			FinalShape s = new FinalShape(p1);
			System.out.println("//////////////////  shape components /////////////////");
			s.printShapeComponents();
			System.out.println("//////////////////  shape components positive /////////////////");
			
			s.toPositive();
			
			s.printShapeComponents();
			System.out.println("//////////////////  resized component /////////////////");
			s.reSize(1000, 1000);
			s.printShapeComponents();*/
			
			// transform all path into shapes
			
			for(Path path : p.getPaths())
			{
				shapes.add(new FinalShape(path));
			}
			Board c = new Board(shapes);
			System.out.println("//////////////////  shape components /////////////////");
			c.getShapes().get(0).printShapeComponents();
			System.out.println("//////////////////  shape components positive/////////////////");
			
			c.toPositive();
			c.getShapes().get(0).printShapeComponents();
			System.out.println("//////////////////  shape components rescaled/////////////////");
			c.rescale(0.2f,0.2f);
			c.getShapes().get(0).printShapeComponents();
			new Gui(c);
			
			
	
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
