package menu;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.PossibleExceptions;
import id3tag.Id3TagMp3;
import userinput.UserInput;

public class ID3TagDisplayerSubmenu
{
	public void printMenu()
	{
			System.out.println();
			System.out.println("Which mp3 file you would like to see ID3 info?");
			Id3TagMp3 testId3Tag = Id3TagMp3.parse(new File(UserInput.getInputFromUser()));
			System.out.println(testId3Tag.toString());
			System.out.println("1. Back to main menu");
	}
	
	
	public void selectableMenuPoints()
	{	
		MainMenu mM = new MainMenu();
		
		switch (userInput())
		{
		case "1":
			mM.printMenu();
			mM.selectableMenuPoints();
			break;
		default:
			PossibleExceptions.menuPointException();
			printMenu();
			selectableMenuPoints();
			break;
		}
	}
	
	public String userInput()
	{
		BufferedReader bufferRead;
		System.out.println();
    	System.out.print("Choose: ");
		System.out.println();
	    String s = "";
	    
		try
		{
			bufferRead = new BufferedReader(new InputStreamReader(System.in));
			s = bufferRead.readLine();
		}
		catch (IOException e)
		{
			System.out.println(e.toString());
		}
	      
		return s;
	}
}
