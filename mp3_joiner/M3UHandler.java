package mp3_joiner;

import java.io.File;
import java.io.IOException;

// This class should create mp3 file object list and then join them to one object. 

public class M3UHandler {

	// main method:
	public static File joinMP3FilesFromM3U(File m3uFile) throws IOException {
			M3UFile m3uData = new M3UFile(m3uFile);
			return m3uData.joinMP3Files();
	}
	
}
