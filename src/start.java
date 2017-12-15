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
				System.out.println("path number: "+i);
				p.extractPathCommands(in);
			}
			p.getPaths().size();
			
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
