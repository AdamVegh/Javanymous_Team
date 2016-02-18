package menu;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import exceptions.PossibleExceptions;

public class ModifySubmenu
{
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
	
	
	public void selectMenuPoint()
	{
		int menuPoint = userInput();
		MainMenu mM = new MainMenu();
		SaveTagMenu doYouWantToChange = new SaveTagMenu();
		
		switch (menuPoint)
		{
		case 1:
			System.out.println("Title submenu");
			doYouWantToChange.printMenu();
			doYouWantToChange.selectMenuPoint();
			break;
		case 2:
			System.out.println("Artist submenu");
			doYouWantToChange.printMenu();
			doYouWantToChange.selectMenuPoint();
			break;
		case 3:
			System.out.println("Album submenu");
			doYouWantToChange.printMenu();
			doYouWantToChange.selectMenuPoint();
			break;
		case 4:
			System.out.println("Year submenu");
			doYouWantToChange.printMenu();
			doYouWantToChange.selectMenuPoint();
			break;
		case 5:
			System.out.println("Comment submenu");
			doYouWantToChange.printMenu();
			doYouWantToChange.selectMenuPoint();
			break;
		case 6:
			System.out.println("Genre submenu");
			doYouWantToChange.printMenu();
			doYouWantToChange.selectMenuPoint();
			break;
		case 7:
			mM.printMenu();
			mM.selectMenuPoint();
			break;
		default:
			PossibleExceptions.menuPointException();
			printMenu();
			selectMenuPoint();
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
}