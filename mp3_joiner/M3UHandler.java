package mp3_joiner;

import java.io.File;

// This class should create mp3 file object list and then join them to one object. 

public class M3UHandler {

	// main method:
	public static File joinFiles(File m3u) {
		M3UFile m3uFile = new M3UFile(m3u);
		return m3uFile.process();
	}
	
}
