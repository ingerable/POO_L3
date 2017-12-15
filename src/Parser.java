import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import pathCommands.Command;
import pathCommands.Path;
import shapeComponents.Point;

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
	
	private static final char[] VALUES = new char[] {'c','m','l','z','C','M','L','Z'};
	
	private ArrayList<Path> paths = new ArrayList<Path>();
	
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
		FileReader in = new FileReader(xml);	
		int result=0;
		while(in.ready())
		{
			if(isFound(in,0,pattern))
			{
				result+=1;
			}
		}
		in.close();
		return result;
	}
	
	//return true if the given pattern was found 
	public boolean isFound(FileReader b, int index, String pattern) throws IOException
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
				return isFound(b,index+1,pattern);
			}
		}
		else
		{
			return false;
		}
	}
		
	
	//extract path that start at the beginning of FileReader in
	public void extractPathCommands(FileReader in) throws IOException
	{	
		//initialize command and path
		Path p = new Path(false);
		Command cmd = new Command();
		
		//look for the first occurence of a path
		while(isFound(in,0,"<path")==false);
		while(isFound(in,0,"d=\"m")==false);
		char c = 'm';
		
		//while we do not reach the end of the path
		while(c!='"' && cmd.isLast()==false)
		{
			if( (Character.isDigit(c) || c=='-') && cmd!=null) //this is an implicit command, so we have to deduce it from the last command
			{
				cmd = extractImplicitCommand(c,cmd.getImplicitCommand(),in);
				p.addCommand(cmd);
				c = (char)in.read();
			}
			else if(isCommand((char)c)) // no problem, the char define explicitly the command
			{
				if((char)c == 'z') // special case, z command could be the last of the path (z doesn't take arguments so we could exceed the end of path char " )
				{
					cmd = extractImplicitCommand((char) in.read(),c,in);
					p.addCommand(cmd);
					c = (char)in.read();
				}
				else //normal case: command values
				{
					cmd = extractCommand(c,in);
					p.addCommand(cmd);
					c = (char)in.read();
				}			
			}
			else if(c=='p') //id of the path (id attribute) just skip it
			{
				while(c!='"')
				{
					c = (char)in.read();
				}
			}
			else // space
			{
				c = (char)in.read();
			}
		}
		//p.printCommands();
		this.paths.add(p);
		
	}
	
	//type of command, filestream
	public Command extractCommand(char c,FileReader in) throws IOException
	{
		//get the type of the command
		Command cmd = Command.getType(c);
		//extract the point and skip a space of one
		in.skip(1);
		cmd.extractPoints(in,(char)in.read());
		return cmd;
	}
	
	
	// first read number, type of command, filestream
	public Command extractImplicitCommand(char n, char c,FileReader in) throws IOException
	{
		//get the type of the command
		Command cmd = Command.getType(c);
		//extract the point and do not skip space
		cmd.extractPoints(in,n);
		return cmd;
	}
	
	public boolean isCommand(char c)
	{
		for(int i=0;i<VALUES.length;i++)
		{
			if(c==VALUES[i])
			{
				return true;
			}
		}
		return false;
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

	public ArrayList<Path> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<Path> paths) {
		this.paths = paths;
	}
}
