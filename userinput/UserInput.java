package userinput;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput
{
	public static String getInputFromUser()
	{
		
		BufferedReader bufferRead;
        String input = "";
        boolean gotBadLoc = true;
        try
    	{	while (gotBadLoc) {
    		System.out.println("Add the absolute path of the file(e.g C:\\\\example\\\\padlo.mp3):");
            bufferRead = new BufferedReader(new InputStreamReader(System.in));
    		input = bufferRead.readLine();
    		if (testFileExistance(input)) {
				gotBadLoc = false;
			}
		}
        	
    	} 
        catch (IOException e)
    	{
            System.out.println(e.toString());
    	}
        return input;	
		}
        
	
	public static boolean testFileExistance(String pathname) 
    {
    	File file = new File(pathname);
    	if (!file.exists()) 
    	{
			System.out.println("File doesnt exists.");
			return false;
		}
    	else
    	{
	    	System.out.println("File exists.");
	    	return true;
	    }
	}
    
    
	
	
}
