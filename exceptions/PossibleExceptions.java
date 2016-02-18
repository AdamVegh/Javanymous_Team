package exceptions;

public class PossibleExceptions
{
	private static Throwable error;
	
	public static void titleException()
	{
		error = new Throwable("Title is too long! The program will slice the title at the 30th charachter!");
		System.out.println(error);
	}
	
	public static void albumException()
	{
		error = new Throwable("Album name is too long! The program will slice the album at the 30th charachter!");
		System.out.println(error);
	}
	
	public static void artistException()
	{
		error = new Throwable("Artist is too long! The program will slice the artist at the 30th charachter!");
		System.out.println(error);
	}
	
	public static void commentException()
	{
		error = new Throwable("Comment is too long! The program will slice the comment at the 30th charachter!");
		System.out.println(error);
	}
	
	public static void genreException()
	{
		error = new Throwable("Genre is too long! The program will slice the genre at the 30th charachter!");
		System.out.println(error);
	}
	
	public static void yearException()
	{
		error = new Throwable("Year is too long! Maximum digit length is 4(e.g 2016)!");
		System.out.println(error);
	}
	
	public static void menuPointException()
	{
		error = new Throwable("Warning ,this menu option is out of range!");
		System.out.println(error);
	}
	
}
