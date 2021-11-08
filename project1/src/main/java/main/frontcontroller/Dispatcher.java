package main.frontcontroller;

import io.javalin.Javalin;
import main.controller.UserAccountController;
import main.controller.ReimbursementController;
import static io.javalin.apibuilder.ApiBuilder.*; //this is where "path" came from


public class Dispatcher 
{
	public Dispatcher(Javalin app)
	{
		setUpAllPaths(app);
	}
	
	
	/**
	 * Modularization of setting up all the paths here
	 * instead of doing them all inside the dispatcher contructor
	 * 
	 * 
	 * @param app
	 */
	public static void setUpAllPaths(Javalin app)
	{
		
		setUpUserControllerPaths(app);
		setUpReimbursementControllerPaths(app);
		
	}
	
	
	
	/**
	 * This contains all the uri paths for the user controller class
	 * Each uri has a specific used and should be used correctly
	 * 
	 * 
	 * @param app
	 */
	public static void setUpUserControllerPaths(Javalin app)
	{
		app.routes(()->{
			path("/api/user", ()->{ // used to check login and login into the system
				post(UserAccountController::userLogin);
			
			});
			
			path("/api/user/info", ()->{ //gets the user data stored in the session key... for employeee
				post(UserAccountController::userData);
			});
			
			path("/logout", ()->{
				post(UserAccountController::logout);//endpoint: /api/user/logout
			});
		});
		
	}
	
	/**
	 * This contains all the uri paths for the reimbursement controller class
	 * Each uri has a specifc use case and should be used correctly
	 * 
	 * @param app
	 */
	public static void setUpReimbursementControllerPaths(Javalin app)
	{
		app.routes(()->{
			path("/api/user/employee/reimbursementTickets",()->{
				get(ReimbursementController::getUserReimbursementTickets); // gets users reimbursement tickets
				path("/newTicketCreation", ()->{
					post(ReimbursementController::createReimbursmentTicket);// creates a new reimbursement ticket by user
				});
			});
			path("/api/user/manager/allreimbursementTicket", ()->{ // gets all reimbursement tickets in the database
				get(ReimbursementController::getAllReimbursementTickets);
				
				path("/updateTicket", ()->{ // updates an existing reimbursement ticket by manager (user)
					post(ReimbursementController::updateReimbursementTicket);
				});
			});
			
			
		});
	}
}
