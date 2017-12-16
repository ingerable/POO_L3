package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import pathCommands.Path;

public class Gui extends JFrame
{
	public Gui(Path p)
	{
		super("POO");
		JPanel jpOpti = new OptimizationWindow(p);
		this.add(jpOpti);
		this.setSize(10000, 10000);
		this.setVisible(true);
	}
	
}
