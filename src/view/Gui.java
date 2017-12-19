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
		
		//p.getPaths().get(18).printCommands();
		
		System.out.println("///////////////////////// shape 9 ///////////////////////////////");
		this.c.getShapes().get(9).printShapeComponents();
		
		
		//System.out.println("//////////////////////// hitbox shape9 ////////////////////");
		//this.c.getShapes().get(9).getMyHitbox().printShapeComponents();
		
		
		
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
		c.rescale(value);
		c.calculateAreaOfAllShapes();
		this.jp.repaint();
		
	}
	
}
