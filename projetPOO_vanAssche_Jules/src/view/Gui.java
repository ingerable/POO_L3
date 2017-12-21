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
	
	//the file
	String file;
	
	//the board that contains all the shape
	Board c;
	
	//the optimizer object
	Optimizer o;
	
	JButton sortType = new JButton("large hitbox first");
	
	public Gui()
	{
		super("POO");
		
		this.loadBoard();
		
		JPanel jpButtons = new JPanel();
		
		this.jp = new OptimizationWindow(c);
		
		/*
		 * buttons, slider
		 */
		
		scale = new JSlider(JSlider.HORIZONTAL,0,100,100);
		scale.addChangeListener(this);
		
		JButton button = new JButton("Optimize");
		JButton buttonHitbox = new JButton("hitbox");
		JButton load = new JButton("load file");
		
		buttonHitbox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				repaintWindow();
			}
		});
		
		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				
				optimize();
			}
		});
		
		sortType.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				changeSortType();
			}
		});
		
		
		jpButtons.add(buttonHitbox);
		jpButtons.add(button);
		jpButtons.add(sortType);
		jpButtons.add(scale);		
		
		this.add(jpButtons, BorderLayout.PAGE_END);
		this.add(jp);
		this.setSize(2000, 1000);
		this.setVisible(true);
	}
	
	
	
	/*
	 * functions
	 */
	
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
	    this.repaint();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) 
	{
		float value = (float)scale.getValue()/100;
		c.rescale(value);
		this.jp.repaint();
		
	}
	
	public void optimize()
	{
		this.o.setBoard(this.c); //don't forget to update the board attribute of the optimizer before processing the optimization (because of the scaling)
		o.optimize();
		repaintWindow();
	}
	
	public void changeSortType()
	{
		if(this.o.getLargestFirst())
		{
			this.sortType.setText("tight hitbox first");
			this.o.setLargestFirst(false);
		}
		else
		{
			this.sortType.setText("large hitbox first");
			this.o.setLargestFirst(true);
		}
		
	}
	
	public void loadBoard()
	{
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
		

		
		//get the board from the parsed svg
		try 
		{
			this.c = p.getBoardFromSvg();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
		
		
		// put all coordinates in positive values
		c.toPositive();
		
		//add the window that represent the original size of the svg
		c.addWindow(p.getWidth(), p.getHeight());
		
		
		//compute hitbox
		c.calculateAreaOfAllShapes();
		
		/*
		 * initialization optimizer
		 */
		this.o = new Optimizer(c);
		
		
	}
	
	
}
