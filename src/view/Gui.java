package view;

import javax.swing.JFrame;

public class Gui extends JFrame
{
	public Gui()
	{
		super("POO");
		this.setSize(1000, 2000);
		this.setVisible(true);
	}
	
	public static void main(String[] args)
	{
		JFrame jf = new Gui();
	}	
}
