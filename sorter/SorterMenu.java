package sorter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ID3Tag.Id3TagMp3;

public class SorterMenu {
	
	List<File> mp3List;
	static String[] listProperty = {"title", "artist", "album", "year", "genre"};
	
	public SorterMenu(List<File> mp3List) {
		this.mp3List = mp3List;
		Boolean ascending = true;
		
		String propertyStr = "";
		boolean propertyOK = false;
		while (!propertyOK) {
			System.out.print("Please type in the property: ");
			for (String string : listProperty) {
				System.out.print(" | " + string);
			}
			System.out.println(" | ");
			Scanner input = new Scanner(System.in);
			propertyStr = propertyChecker(input.nextLine().toLowerCase());
			propertyOK = (propertyStr != "");
		}
		
		String ascendingStr = "";
		boolean ascendingOK = false;
		while (!ascendingOK) {
			System.out.println("Direction of soring: [A]scending or [D]escending");
			Scanner input = new Scanner(System.in);
			ascendingStr = input.nextLine().toLowerCase();
			ascendingOK = (ascendingStr.equals("a") || ascendingStr.equals("d"));
		}
		ascending = ascendingStr.equals("a") ? true : false;
		
		Sorter sortedBy = sortedBy(propertyStr, ascending);
		
		mp3List.sort(sortedBy);
		
		System.out.print("absoulte path");
		for (String string : listProperty) {
			System.out.print(" | " + string);
		}
		System.out.println(" | ");
		for (File mp3 : mp3List) {
			System.out.println(mp3.getAbsolutePath() + " | " + Id3TagMp3.parse(mp3).getTitle() + 
					" | " + Id3TagMp3.parse(mp3).getArtist() + " | " + Id3TagMp3.parse(mp3).getAlbum() + 
					" | " + Id3TagMp3.parse(mp3).getYear() + " | " + Id3TagMp3.parse(mp3).getGenre());
		}
		
	}
	
	public Sorter sortedBy(String property, boolean ascending) {
		switch (property) {
		case "title":
			return new TitleSorter(ascending);
		case "artist":
			return new ArtistSorter(ascending);
		case "album":
			return new AlbumSorter(ascending);
		case "year":
			return new YearSorter(ascending);
		case "genre":
			return new GenreSorter(ascending);
		}
		return null;
	}
	
	public String propertyChecker(String property) {
		for (String string : listProperty) {
			if (property.equals(string)) return property;
		}
		System.out.println("This property doesn't exist.");
		return "";
	}
	
	public static void main(String[] args) {
		File track1 = new File("E:" + File.separator + "MUSIC" + File.separator + 
				"Stricken - Disturbed.mp3");
		File track2 = new File("E:" + File.separator + "MUSIC" + File.separator +
				"Indestructible - Disturbed.mp3");
		File track3 = new File("E:" + File.separator + "MUSIC" + File.separator +
				"End of all hope - Nightwish.mp3");
//		System.out.println(track1.toString());
//		System.out.println(track2.toString());

		List<File> sorterMP3 = new ArrayList<>();
		sorterMP3.add(track1);
		sorterMP3.add(track2);
		sorterMP3.add(track3);
		//System.out.println(sorterMP3.toString());
		
		//Scanner in = new Scanner(System.in);
		//String s = in.nextLine();
		//System.out.println(s);
		
		new SorterMenu(sorterMP3);
	}
	
}
