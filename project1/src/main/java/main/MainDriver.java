package main;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import main.frontcontroller.FrontController;
import main.services.AccountService;
import main.services.AccountServiceImpl;
import main.services.ReimbursementService;
import main.services.ReimbursementServiceImpl;


/////////////////THIS IS PROJECT 1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class MainDriver {
	
	final static Logger loggy = Logger.getLogger(MainDriver.class);
//	public static AccountService myAccountServices = new AccountServiceImpl();
//	public static ReimbursementService myReimbursementServices = new ReimbursementServiceImpl();
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		loggy.setLevel(Level.ALL);
		
//		myReimbursementServices.getAllReimbursementTickets();
//		System.out.println(myReimbursementServices.getReimbursementTicketsFromServiceLayer());
		Javalin app = Javalin.create(
				config->{
					config.addStaticFiles(
							staticFiles->{
								staticFiles.directory="/resources";
								staticFiles.hostedPath="/";
								staticFiles.location = Location.CLASSPATH;
							}
							);
					
				}
			
			).start(9001);
		
		
		
		FrontController frontCont = new FrontController(app);
	}

}
