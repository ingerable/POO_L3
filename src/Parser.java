import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
	
	private static final char[] VALUES = new char[] {'c','m','l','z'};
	
	private Parser()
	{
		
	}
	
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
			if(isFound(in,0,pattern))
			{
				result+=1;
			}
		}
		in.close();
		return result;
	}
	
	//return true if the given pattern was found 
	public boolean isFound(FileInputStream b, int index, String pattern) throws IOException
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
		
	
	//extract path that start at the beginning of FileInputStream in
	public void extractPathCommands(FileInputStream in) throws IOException
	{	
		//initialize command and path
		Path p = new Path(false);
		Command cmd = null;
		
		//look for the first occurence of a path
		while(isFound(in,0,"<path")==false);
		while(isFound(in,0,"d=\"")==false);
		char c = (char)in.read();
		
		//while we do not reach the end of the path
		while(c!='"')
		{
			if(Character.isDigit(c) || c=='-') //gotta find the implicit command
			{
				cmd = extractImplicitCommand(c,cmd.getImplicitCommand(),in);
				p.addCommand(cmd);
				c = (char)in.read();
			}
			else if(isCommand((char)c)) // no problem, the char define explicitly the command
			{
				cmd = extractCommand(c,in);
				p.addCommand(cmd);
				c = (char)in.read();
			}
			else
			{
				c = (char)in.read();
			}	
		}
		in.close();
		p.printCommands();
	}
	
	//type of command, filestream
	public Command extractCommand(char c,FileInputStream in) throws IOException
	{
		//get the type of the command
		Command cmd = Command.getType(c);
		//extract the point and skip a space of one
		in.skip(1);
		in = cmd.getPoint(in,(char)in.read());
		return cmd;
	}
	
	
	// first read number, type of command, filestream
	public Command extractImplicitCommand(char n, char c,FileInputStream in) throws IOException
	{
		//get the type of the command
		Command cmd = Command.getType(c);
		//extract the point and do not skip space
		in = cmd.getPoint(in,n);
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
}
