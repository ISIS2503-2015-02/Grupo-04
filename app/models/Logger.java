package models;

public class Logger {

	private Logger() {
	}

	public static void info(Exception e){
		System.out.println(e);
	}

    public static void infoJSON(Exception e){
        System.out.println(e);
    }

}
