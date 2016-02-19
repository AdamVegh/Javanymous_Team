package menu;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import exceptions.PossibleExceptions;
import id3tag.Id3TagMp3;
import userinput.UserInput;

public class ModifySubmenu
{
	static Id3TagMp3 id3tag;
	static String filePath;
	
	
	
	public void printMenu()
	{
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			System.out.println("Which tag would like to modify?");
			System.out.println("1. Title");
			System.out.println("2. Artist");
			System.out.println("3. Album");
			System.out.println("4. Year");
			System.out.println("5. Comment");
			System.out.println("6. Genre");
			System.out.println("7. Back");
			System.out.println("#-----------------------------------#");
			System.out.println();
	}
	
	
	public void selectableMenuPoints()
	{	
		getLocationAndSetId3Tag();
		int menuPoint = userInput();
		MainMenu mM = new MainMenu();
		SaveTagMenu doYouWantToChange = new SaveTagMenu();
		
		switch (menuPoint)
		{
		case 1:
			System.out.println("Title submenu");
			String newTag = userInputNewData();
			id3tag.setTitle(newTag);
			doYouWantToChange.printMenu();
			doYouWantToChange.selectableMenuPoints();
			break;
		case 2:
			System.out.println("Artist submenu");
			String newTag2 = userInputNewData();
			id3tag.setArtist(newTag2);
			doYouWantToChange.printMenu();
			doYouWantToChange.selectableMenuPoints();
			break;
		case 3:
			System.out.println("Album submenu");
			String newTag3 = userInputNewData();
			id3tag.setAlbum(newTag3);
			doYouWantToChange.printMenu();
			doYouWantToChange.selectableMenuPoints();
			break;
		case 4:
			System.out.println("Year submenu");
			String newTag4 = userInputNewData();
			id3tag.setYear(newTag4);
			doYouWantToChange.printMenu();
			doYouWantToChange.selectableMenuPoints();
			break;
		case 5:
			System.out.println("Comment submenu");
			String newTag5 = userInputNewData();
			id3tag.setComment(newTag5);
			doYouWantToChange.printMenu();
			doYouWantToChange.selectableMenuPoints();
			break;
		case 6:
			System.out.println("Genre submenu");
			String newTag6 = userInputNewData();
			id3tag.setGenre(newTag6);
			doYouWantToChange.printMenu();
			doYouWantToChange.selectableMenuPoints();
			break;
		case 7:
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
	
	public int userInput()
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
	      
		return Integer.parseInt(s);
	}
	
	public void getLocationAndSetId3Tag(){
		filePath = UserInput.getInputFromUser();
		id3tag = Id3TagMp3.parse(new File(filePath));
	}
	
	public String userInputNewData()
	{
		BufferedReader bufferRead;
		System.out.println();
    	System.out.print("NewTag: ");
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
