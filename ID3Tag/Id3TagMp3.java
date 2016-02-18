package ID3Tag;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Id3TagMp3 {
	
    private String title;
    private String artist;
    private String album;
    private int year;
    private String comment;
    private String genre;
    
    private static final int MAXLENGTH = 30;
    private static final int MAXLENGTHYEAR = 4;
    private static final String HEADER = "TAG";
    
    
    
    private Id3TagMp3() 
    {
    }
    
    private static byte[] readXBytes(byte[] byteArray, int fromPos, int toPos) 
    {
        byte[] resultArray = new byte[toPos - fromPos];
        for (int i = fromPos; i < toPos; i++) 
        {
            resultArray[i - fromPos] = byteArray[i];
        }
        return resultArray;
    }
    
    public static Id3TagMp3 parse(File file) 
    {
        byte[] last128 = tail(file);
        byte[] baTitle = readXBytes(last128, 3, 33);
        String title = new String(baTitle).trim();
        byte[] baArtist = readXBytes(last128, 33, 63);
        String artist = new String(baArtist).trim();
        byte[] baAlbum = readXBytes(last128, 63, 93);
        String album = new String(baAlbum).trim();
        byte[] baYear = readXBytes(last128, 93, 97);
        int year = Integer.parseInt(new String(baYear).trim());
        byte[] baComment = readXBytes(last128, 97, 125);
        String comment = new String(baComment).trim();
        byte[] baGenre = readXBytes(last128, 127, 128);
        String genre = new String(baGenre).trim();
        Id3TagMp3 tag = new Id3TagMp3();
        tag.setTitle(title);
        tag.setArtist(artist);
        tag.setAlbum(album);
        tag.setYear(year);
        tag.setComment(comment);
        tag.setGenre(genre);
        return tag;
    }
    public static byte[] tail(File file) 
    {
        try {
            RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
            long fileLength = fileHandler.length() - 1;
            byte[] buffer = new byte[128];
            for (int i = 0; i < buffer.length; i++) {
                fileHandler.seek(fileLength - 127 + i);
                buffer[i] = fileHandler.readByte();
            }
            fileHandler.close();
            return buffer;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
    	if(title.length() > MAXLENGTH)
    	{
    		this.title = title.substring(0, MAXLENGTH);
    	}
    	else {
    		this.title = fixTagLen(title);
		}
        
    }
    
    public String getArtist(){
        return artist;
    }
    public void setArtist(String artist){
    	if (artist.length() > MAXLENGTH) {
    		this.artist = artist.substring(0,MAXLENGTH);
		}
    	else {
    		this.artist = fixTagLen(artist);
		}
    }
    
    public String getAlbum() 
    {
        return album;
    }
    public void setAlbum(String album) 
    {
        if (album.length() > MAXLENGTH) {
			this.album = album.substring(0,MAXLENGTH);
		}
        else {
        	this.album = fixTagLen(album);
		}
        
    }
    
    public int getYear() 
    {
        return year;
    }
    public void setYear(int year){
        if (((int) Math.log10(year) + 1) > MAXLENGTHYEAR) {
        	this.year = Integer.parseInt(String.valueOf(year).substring(0, MAXLENGTHYEAR));
		}
        else {
        	this.year = Integer.parseInt(fixYearLen(String.valueOf(year)));
		}
        
    }

    public String getComment() 
    {
        return comment;
    }
    public void setComment(String comment) {
        if (comment.length() > 30) {
			this.comment = comment.substring(0,MAXLENGTH);
		}
        else {
        	this.comment = fixTagLen(comment);
		}
        
    }
    
    public String getGenre() 
    {
        return genre;
    }
    public void setGenre(String genre) 
    {
        this.genre = genre;
    }
    
    @Override
    public boolean equals(Object o) 
    {
        Id3TagMp3 tag = (Id3TagMp3) o;
        return ((title == null && tag.title == null) || title.equals(tag.title))
                && ((artist == null && tag.artist == null) || artist.equals(tag.artist))
                && ((album == null && tag.album == null) || album.equals(tag.album)) && (year == tag.year)
                && ((comment == null && tag.comment == null) || comment.equals(tag.comment)) && (genre == tag.genre);
    }
    
    @Override
    public int hashCode() 
    {
        return -1;
    }
    
    @Override
    public String toString() 
    {
        return "Artist: " + (artist == null ? "NULL" : artist).trim() + "\nAlbum: " + album + "\nTitle: " + title.trim()
                + "\nYear: " + year + "\nComment: " + comment + "\nGenre: " + genre;
    }
    
    public static File TestFileExistance(String pathname) 
    {
    	File file = new File(pathname);
    	if (!file.exists()) 
    	{
			System.out.println("File doesnt exists.");
		}
    	else
    	{
	    	System.out.println("File exists.");
	    	return file;
	    }
    	return null;
	}
    
    public static String getPathnameFromUser() 
    {
    	System.out.println("Give the absolute path of the file u want to modify or check the id3tag:");
    	Scanner sc = new Scanner(System.in);
    	while(sc.hasNext() ) System.out.println(sc.nextLine());
    	sc.close();
    	return sc.toString();
	}
    
    protected static String fixTagLen(String tag) {
		if (tag.length() < 30) {
			String newString = tag;
			int diff = 30 - tag.length();
			while (diff > 0) {
				newString += '\u0000';
				diff -= 1;
			}
			return newString;
		}
		return tag;
	}
    public static String fixYearLen(String year) {
		if (year.length() < 4) {
			String newYear = year;
			int diff = 4 - year.length();
			while (diff > 0) {
				newYear += '\u0000';
				diff -= 1;
			}
			return newYear;
		}
		return year;
	}
    
    public String getNewId3Tag() {
		StringBuilder builder = new StringBuilder();
		builder.append(HEADER);
		builder.append(getTitle());
		builder.append(getArtist());
		builder.append(getAlbum());
		builder.append(getYear());
		builder.append(getComment());
//		builder.append("\u0000");
//		builder.append("\u0000");
		builder.append(getGenre());
		String newID3Tag = builder.toString();
		return newID3Tag;
		}
    
    public void writeNewId3ToMp3(){
    	RandomAccessFile raff;
    	try 
    	{
    		raff = new RandomAccessFile("C:\\Test\\The_Flamin_Groovies_-_05_-_Dont_Put_Me_On.mp3", "rw");
    		raff.seek(raff.length()-128);
    		raff.writeBytes(getNewId3Tag());
    		raff.close();
		}
    	catch (IOException e) 
    	{
			System.out.println(e.toString());
		}
    }
    
    
    public static void main(String[] args) 
    {
    	File bözsiFile = TestFileExistance("C:\\Test\\The_Flamin_Groovies_-_05_-_Dont_Put_Me_On.mp3");
    	Id3TagMp3 bözsi = Id3TagMp3.parse(bözsiFile);
    	bözsi.setComment("Anyad picshajaa");
    	bözsi.setTitle("Zene nagyon zene nagyon nagyon zeneeeeeeeeeeeeeeeeeeeeeeeeeeeee");
    	System.out.println(new String(bözsi.getNewId3Tag()).length());
    	System.out.println(new String(bözsi.getNewId3Tag()));
    	bözsi.writeNewId3ToMp3();;
    }
}