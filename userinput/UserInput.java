package userinput;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import sorter.SorterMenu;

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
    
    
	
	public static String propertyChecker(String property) {
		for (String string : SorterMenu.listProperty) {
			if (property.equals(string)) return property;
		}
		System.out.println("This property doesn't exist.");
		return "";
	}
	
	public static String getPropertyInput() {
		String propertyStr = "";
		boolean propertyOK = false;
		while (!propertyOK) {
			System.out.print("Please type in the property: ");
			for (String string : SorterMenu.listProperty) {
				System.out.print(" | " + string);
			}
			System.out.println(" | ");
			Scanner input = new Scanner(System.in);
			propertyStr = propertyChecker(input.nextLine().toLowerCase());
			propertyOK = (propertyStr != "");
		}
		return propertyStr;
	}
	
	
	public static boolean getAscendingInput() {
		String ascendingStr = "";
		boolean ascendingOK = false;
		while (!ascendingOK) {
			System.out.println("Direction of soring: [A]scending or [D]escending");
			Scanner input = new Scanner(System.in);
			ascendingStr = input.nextLine().toLowerCase();
			ascendingOK = (ascendingStr.equals("a") || ascendingStr.equals("d"));
		}
		return ascendingStr.equals("a") ? true : false; 
	};
	
	
}
