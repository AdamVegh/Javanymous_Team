package sorter;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class SorterMenu {
	
	List<File> mp3List;
	boolean ascending = true;
	static String[] listProperty = {"title", "artist", "album", "year", "genre"};
	
	public SorterMenu(List<File> mp3List) {
		this.mp3List = mp3List;
		
		String property;
		boolean propertyOK = false;
		while (!propertyOK) {
			System.out.println("Please type in the property: ");
			Scanner input = new Scanner(System.in);
			property = SorterMenu.propertyChecker(input.nextLine());
			propertyOK = (property != "");
		}
		
		String ascending;
		boolean ascendingOK = false;
		while (!ascendingOK) {
			System.out.println("Direction of soring: [A]scending or [D]escending");
			Scanner input = new Scanner(System.in);
			ascending = input.nextLine().toLowerCase();
			ascendingOK = (ascending.equals("a") || ascending.equals("d"));
		}
		
	}
	
	public static String propertyChecker(String property) {
		for (String string : listProperty) {
			if (property.toLowerCase().equals(string)) return property;
		}
		System.out.println("This property doesn't exist.");
		return "";
	}
	
}
