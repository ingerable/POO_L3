package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pathCommands.Path;
import shapeComponents.Board;
import shapeComponents.FinalShape;

public class Gui extends JFrame
{
	public Gui(Board b)
	{
		super("POO");
		JPanel jp = new OptimizationWindow(b);
		this.add(jp);
		JButton button = new JButton("Optimize");
		
		this.add(button, BorderLayout.PAGE_END);
		this.setSize(3000, 3000);
		this.setVisible(true);
	}
	
}
