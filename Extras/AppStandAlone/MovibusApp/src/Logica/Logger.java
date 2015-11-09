package logica;

public class Logger {

	private Logger() {
	}

	public static void info(Exception e){
		System.out.println(e.getMessage());
	}
	public static void info2(String e){
		System.out.println(e);
	}
}
