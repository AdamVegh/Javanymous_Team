package mp3_joiner;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class MP3JoinerUI {
	
	public void mp3JoinerGetInputFromUser()
	{
		Scanner consoleScanner = new Scanner(System.in);
		File m3uFile;
		String line;
		while (true) {
			System.out.println("Which M3U need to be progressed?:");
			line = consoleScanner.nextLine();
			m3uFile = new File(line);
			if (M3UFile.checkIfValidM3U(m3uFile))
				break;
			else
				System.out.println("Invalid file!");
		}
		
		try {
			System.out.println("Beginning process...");
			File destinationFile = M3UHandler.joinMP3FilesFromM3U(m3uFile);
			System.out.println("Process finished.");
			System.out.println("Destination file path:");
			System.out.println(destinationFile.getAbsolutePath());
		}
		catch (IOException exception) {
			System.out.println("An exception occoured during the process:");
			System.out.println(exception.getMessage());
		}
	}
	
}
