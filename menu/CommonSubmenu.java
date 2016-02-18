import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import exceptions.PossibleExceptions;

public class CommonSubmenu
{
	
	public void printMenu()
	{
//			Runtime.getRuntime().exec("cls");
			System.out.println();
			System.out.println("Which tag would you like to modify?");
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
		
		switch (menuPoint)
		{
		case 1:
			System.out.println("Title submenu");
			break;
		case 2:
			System.out.println("Artist submenu");
			break;
		case 3:
			System.out.println("Album submenu");
			break;
		case 4:
			System.out.println("Year submenu");
			break;
		case 5:
			System.out.println("Comment submenu");
			break;
		case 6:
			System.out.println("Genre submenu");
			break;
		case 7:
			//back to smwhr
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
		System.out.println();
    	System.out.print("Choose: ");
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
