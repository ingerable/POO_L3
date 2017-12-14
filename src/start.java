import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			
			FileInputStream in = new FileInputStream(p.getFile());
			for(int i=0; i<nbrPath;i++)
			{
				p.extractPathCommands(in);
			}
			
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
