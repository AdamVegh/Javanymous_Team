package sorter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import userinput.*;

import id3tag.Id3TagMp3;

public class SorterMenu {
	
	List<File> mp3List;
	public static String[] listProperty = {"title", "artist", "album", "year", "genre"};
	
	public SorterMenu(List<File> mp3List) {
		this.mp3List = mp3List;
		
		Sorter sortedBy = sortedBy(UserInput.getPropertyInput(), UserInput.getAscendingInput());
		
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
