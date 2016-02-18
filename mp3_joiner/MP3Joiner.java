package mp3_joiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class MP3Joiner {

	// checker used for mp3 files:
	static boolean checkIfValidMP3(File mp3) {
		if (!mp3.isFile())
			return false;
		String name = mp3.getName();
		return name.endsWith(".mp3") || name.endsWith(".MP3");
	}

	// joiner function:
	static void join(List<File> source, File destination) throws IOException {
		
	}
	
}
