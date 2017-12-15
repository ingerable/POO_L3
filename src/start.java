import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


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
			
			System.out.println("relative command");
			p.getPaths().get(0).printCommands();
			System.out.println("absolute command");
			p.getPaths().get(0).toAbsolute();
			p.getPaths().get(0).printCommands();
			
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
