package main;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Logging {


	
	final static Logger loggy = Logger.getLogger(MainDriver.class);

	public static void error(Exception e)
	{
		loggy.setLevel(Level.ERROR);
		
		loggy.error(e);
	}
	
	public static void info(String s)
	{
		loggy.setLevel(Level.ALL);
		
		loggy.info(s);
	}
}
