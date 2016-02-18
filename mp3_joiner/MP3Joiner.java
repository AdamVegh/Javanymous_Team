package mp3_joiner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

public class MP3Joiner {
	
	private static final int SIZE_OF_BUFFER = (1 << 20);

	// checker used for mp3 files:
	static boolean checkIfValidMP3(File mp3File) {
		if (!mp3File.isFile())
			return false;
		String name = mp3File.getName();
		return name.endsWith(".mp3") || name.endsWith(".MP3");
	}

	// joiner function:
	static void join(List<File> sourceFileList, File destinationFile) throws IOException {
		RandomAccessFile writer = new RandomAccessFile(destinationFile, "rws");
		for (File sourceFile: sourceFileList) {
			append(sourceFile, writer);
		}
		writer.close();
	}
	
	private static void append(File sourceFile, RandomAccessFile writerOfDestinationFile) throws IOException {
		long lengthOfDestinationFile = writerOfDestinationFile.length();
		if (lengthOfDestinationFile > 128 && checkIfID3Tagged(writerOfDestinationFile)) {
			writerOfDestinationFile.setLength(lengthOfDestinationFile - 128);
			lengthOfDestinationFile -= 128;
		}
		writerOfDestinationFile.seek(lengthOfDestinationFile);
		RandomAccessFile readerOfSourceFile = new RandomAccessFile(sourceFile, "r");
		long lengthOfSourceFile = readerOfSourceFile.length();
		readerOfSourceFile.seek(0);
		{
			long positionInDestinationFile = lengthOfDestinationFile;
			long positionInSourceFile = 0;
			byte[] bytesToCopy = new byte[SIZE_OF_BUFFER];
			int numberOfBytesRead;
			while (positionInSourceFile < lengthOfSourceFile) {
				numberOfBytesRead = readerOfSourceFile.read(bytesToCopy, 0, SIZE_OF_BUFFER);
				positionInSourceFile += numberOfBytesRead;
				writerOfDestinationFile.write(bytesToCopy, 0, numberOfBytesRead);
				positionInDestinationFile += numberOfBytesRead;
			}
		}
		readerOfSourceFile.close();
	}
	
	private static boolean checkIfID3Tagged(RandomAccessFile writerOfFile) throws IOException {
		return getBeginningOfTail(writerOfFile).equals("TAG");
	}
	
	private static String getBeginningOfTail(RandomAccessFile writerOfFile) throws IOException {
		long currentPosition = writerOfFile.getFilePointer();
		writerOfFile.seek(writerOfFile.length() - 128);
		byte[] bytes = new byte[3];
		writerOfFile.read(bytes, 0, 3);
		writerOfFile.seek(currentPosition);
		return new String(bytes);
	}
	
}
