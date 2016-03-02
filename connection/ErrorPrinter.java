package connection;

public class ErrorPrinter {
	
	public static void printErrorMessage(String message, Exception e, boolean printType, boolean printStackTrace) {
		System.err.println(message);
		if (printType) {
			System.err.println("\tType: " + e.getClass().getName());
		}
		System.err.println("\tMessage: " + e.getMessage());
		if (printStackTrace) {
		System.err.println("\tStackTrace:");
			StackTraceElement[] stackTrace = e.getStackTrace();
			for (StackTraceElement element: stackTrace)
				System.err.println("\t\t" + element);
		}
	}
}
