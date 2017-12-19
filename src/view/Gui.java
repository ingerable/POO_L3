package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import pathCommands.Path;
import shapeComponents.Board;
import shapeComponents.FinalShape;
import shapeComponents.Point;
import utility.Optimizer;
import utility.Parser;

public class Gui extends JFrame implements ChangeListener
{
	//panel 
	OptimizationWindow jp;
	
	//jslider
	JSlider scale;
	
	String file;
	
	Board c;
	
	public Gui()
	{
		super("POO");
		
		/*
		 * file chooser
		 */
		
		JFileChooser choix = new JFileChooser();
		int retour=choix.showOpenDialog(this);
		if(retour==JFileChooser.APPROVE_OPTION)
		{
		   choix.getSelectedFile().getName();
		   file = choix.getSelectedFile().
		          getAbsolutePath();
		};
		
		/*
		 * initialization parser
		 */
		Parser p = Parser.getParser();
		p.setFile(file);
		
		int nbrPath;
		
		try 
		{
			//get the width and the height of the svg 
			p.getWidthHeigh();
			
			//get the numer of path to extract
			nbrPath = p.numberOfPattern("<path");
			
			FileReader in = new FileReader(p.getFile());
			
			//extract all the path
			for(int i=0; i<nbrPath;i++)
			{
				p.extractPathCommands(in);
			}
			in.close();
			
			//list of shapes
			ArrayList<FinalShape> shapes = new ArrayList<FinalShape>();
			
			// transform all path into shapes	
			for(Path path : p.getPaths())
			{
				shapes.add(new FinalShape(path));
			}
			
			
			//p.getPaths().get(0).printCommands();
			c = new Board(shapes,p.getHeight(),p.getWidth());
			

			
			// put all coordinates in positive values
			c.toPositive();
			
			//add the window that represent the original size of the svg
			c.addWindow(p.getWidth(), p.getHeight());
			
			
			//compute hitbox
			c.calculateAreaOfAllShapes();
			
			///////test///////
			//Optimizer op = new Optimizer(c);
			//op.sortHitbox();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.jp = new OptimizationWindow(c);
		JPanel jpButtons = new JPanel();
		
		/*
		 * buttons, slider
		 */
		
		scale = new JSlider(JSlider.HORIZONTAL,0,100,100);
		scale.addChangeListener(this);
		
		JButton button = new JButton("Optimize");
		JButton buttonHitbox = new JButton("hitbox");
		
		buttonHitbox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				repaintWindow();
			}
		});
		
		jpButtons.add(buttonHitbox);
		jpButtons.add(button);
		jpButtons.add(scale);
		
		
		this.add(jpButtons, BorderLayout.PAGE_END);
		this.add(jp);
		this.setSize(2000, 1000);
		this.setVisible(true);
	}
	
	public void repaintWindow() 
	{
		if(this.jp.getHitbox()==true)
		{
			this.jp.setHitbox(false);
		}
		else
		{
			this.jp.setHitbox(true);
		}
		
	    this.jp.repaint();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		float value = (float)scale.getValue()/100;
		c.rescale(value, value);
		c.calculateAreaOfAllShapes();
		this.jp.repaint();
		
	}
	
}
