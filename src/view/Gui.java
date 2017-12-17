package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pathCommands.Path;
import shapeComponents.FinalShape;

public class Gui extends JFrame
{
	public Gui(FinalShape s)
	{
		super("POO");
		JPanel jp = new OptimizationWindow(s);
		this.add(jp);
		this.setSize(3000, 3000);
		this.setVisible(true);
	}
	
}
