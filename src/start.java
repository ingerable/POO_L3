import java.io.IOException;


public class start {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Parser p = Parser.getParser();
		p.setFile(args[0]);	
		try 
		{
			p.extractPath();

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	

}
