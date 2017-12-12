import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 */

/**
 * @author jules
 *
 */
public class Parser 
{

	/**
	 * @param 
	 */
	private static Parser instanceParser = new Parser();
	
	private String file;
	
	private File xml;
	
	private Parser()
	{}
	
	public static Parser getParser()
	{
		if(instanceParser==null)
		{
			instanceParser = new Parser();
		}
		return instanceParser;
	}
	
	//get the number of occurences for a given pattern
	public Integer numberOfPattern(String pattern) throws IOException
	{
		FileInputStream in = new FileInputStream(xml);	
		int result=0;
		while(in.available()>0)
		{
			if(isPath(in,0,pattern))
			{
				result+=1;
			}
		}
		in.close();
		return result;
	}
	
	public boolean isPath(FileInputStream b, int index, String pattern) throws IOException
	{
		if(b.read()== (int)pattern.charAt(index))
		{
			// last char of path, now we need to stop recursive call
			if(index==pattern.length()-1)
			{
				return true;
			}
			else
			{
				return isPath(b,index+1,pattern);
			}
		}
		else
		{
			return false;
		}
	}

	public String getFile() 
	{
		return file;
	}

	public void setFile(String file) 
	{	
		this.file = file;
		this.xml = new File(this.file);
		
		
	}
	
}
