package mp3_joiner;

import java.io.File;
import java.util.List;

public class MP3File {

	// member variables:
	private byte[] content;
	private byte[] tail;
	
	// constructors:
	private MP3File(byte[] content, byte[] tail) {
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
	
	private static byte[] concat(byte[] left, byte[] right) {
		byte[] result = new byte[left.length + right.length];
		
		int i = 0;
		
		for (byte b : left) {
			result[i++] = b;
		} 
		for (byte b : right) {
			result[i++] = b;
		}
		
		return result;
	}
	
	MP3File join(MP3File other) {
		
		byte[] content = concat(this.content, other.content);
		return new MP3File(content, other.tail);
	}
	
	static MP3File join(List<MP3File> listOfMP3Files) {
		
		MP3File joinedObject = null; // new MP3File(mp3);
		
		
		for (MP3File mp3File : listOfMP3Files) {
			if (joinedObject == null) {
				joinedObject = mp3File;
			}
			else {
				joinedObject = joinedObject.join(mp3File);  
			}
		}
		
		return joinedObject;
	}

}
