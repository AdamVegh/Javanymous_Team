package userinput;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput
{
	public static String getInputFromUser()
	{
		BufferedReader bufferRead;
        String input = "";
        try
		{
        	System.out.println("Add the absolute path of the file(e.g C:\\\\example\\\\padlo.mp3):");
        	bufferRead = new BufferedReader(new InputStreamReader(System.in));
			input = bufferRead.readLine();
		} 
        catch (IOException e)
		{
        	System.out.println(e.toString());
		}
        return input;
	}
	
	
}
