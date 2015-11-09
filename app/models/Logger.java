package models;

public class Logger {

	private Logger() {
	}

	public static void info(Exception e){
		System.out.println(e);
	}
}
