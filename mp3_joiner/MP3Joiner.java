package mp3_joiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class MP3Joiner {

	// checker used for mp3 files:
	static boolean checkIfValidMP3(File mp3File) {
		if (!mp3File.isFile())
			return false;
		String name = mp3File.getName();
		return name.endsWith(".mp3") || name.endsWith(".MP3");
	}

	// joiner function:
	static void join(List<File> sourceFileList, File destinationFile) throws IOException {
		
	}
	
}
