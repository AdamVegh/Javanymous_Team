package mp3_joiner;

import java.io.File;
import java.util.List;

public class MP3File {

	// member variables:
	private char[] content;
	private char[] tail;
	
	// constructors:
	private MP3File(char[] content, char[] tail) {
		this.content = content;
		this.tail = tail;
	}
	
	MP3File(File mp3) {
		// mp3 file reading comes here
	}
	
	// other methods:
	
	boolean toFile(File destination) {
		// mp3 file-writing comes here
		return false;
	}
	
	MP3File join(MP3File other) {
		// joiner algorythn comes here
		return new MP3File(new char[1000], new char[128]);
	}
	
	static MP3File join(List<MP3File> listOfMP3Files) {
		// multi-joiner algorythm comes here
		return new MP3File(new char[1000], new char[128]);
	}

}
