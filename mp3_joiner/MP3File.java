package mp3_joiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.List;

public class MP3File {

	// member variables:
	private byte[] body;
	private byte[] tail;
	
	// constructors:
	private MP3File(byte[] body, byte[] tail) {
		this.body = body;
		this.tail = tail;
	}
	
	MP3File(File mp3) throws FileNotFoundException {
		
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
		
		byte[] body = concat(this.body, other.body);
		return new MP3File(body, other.tail);
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
