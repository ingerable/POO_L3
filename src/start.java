import java.awt.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import pathCommands.Path;
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
			
			
			Path p1 = p.getPaths().get(9);
			//FinalShape s = new FinalShape(p1);
			p1.printCommands();
			//System.out.println("//////////////////  shape components /////////////////");
			//s.printShapeComponents();
	
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
