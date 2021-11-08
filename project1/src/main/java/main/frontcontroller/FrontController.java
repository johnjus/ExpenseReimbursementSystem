package main.frontcontroller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import main.models.accounts.Account;
import main.Logging;

public class FrontController 
{
	Javalin app;
	Dispatcher dispatcher;
	
	
	public FrontController(Javalin app)
	{
		this.app = app;
		
		app.before("/*", FrontController::checkAllRequests);
		
		this.dispatcher = new Dispatcher(app);
	}
	
	/**
	 * This is where the middlewear logic is contained. 
	 * It checks every uri that is passed through the server to make sure that who/whom is trying to obtain/create/send information
	 * is allowed to do so. It also prevents hard coding of the uri paths in the dispatcher class. As well as, prevents hard coding
	 * in the html documents themselves
	 * 
	 * 
	 * 
	 * 
	 * @param context
	 */
	public static void checkAllRequests(Context context)
	{
		//System.out.println("In front controller.... " + context.path());
		//checks to see if the user is null but if they are at the home page it doesnt go into an endless checking loop
		if(context.sessionAttribute("currentUser") == null & context.path().equals("/index.html") | context.path().equals("/css/login-page.css") | context.path().equals("/project1-frontend/background-pictures/vintage-camera.jpg"))
		{
			//System.out.println(context.path());
			return;
		}
		//checks to see if the user is trying to access the login uri... if they are just return. that is fine
		if(context.path().equals("/api/user"))
		{
			//System.out.println(context.path() + " chicken!!!!!!!!!!!!!!!");
			return;
		}
		

//		
//		if the current session "currentUser" is null then no one is logged into the system. Therfore, no one should be able to log in without providing their credentials
		if(context.sessionAttribute("currentUser") == null)
		{
			//System.out.println("no user " + context.path());
			context.redirect("/index.html");
		}

//checks to see if the currentuser session is null and if the uri is the one that returns all the reimbursement tickets for a user
		if((context.sessionAttribute("currentUser") == null & context.path().equals("/api/user/manager/allreimbursementTicket")) | (context.sessionAttribute("currentUser") == null & context.path().equals("/api/user/manager/allreimbursementTicket/updateTicket")))
		{
			context.redirect("/index.html");
		}
		else if(((((Account)context.sessionAttribute("currentUser")).getRoleId() == 1) & context.path().equals("/api/user/manager/allreimbursementTicket")) | ((((Account)context.sessionAttribute("currentUser")).getRoleId() == 1) & context.path().equals("/api/user/manager/allreimbursementTicket/updateTicket")))
		{
			//System.out.println("user is employee not manager");
			context.redirect("/html/employee-page.html");
		}
		
		
		
		if((context.sessionAttribute("currentUser") == null & context.path().equals("/api/user/employee/allreimbursementTicket")) | (context.sessionAttribute("currentUser") == null &context.path().equals("/api/user/employee/allreimbursementTicket/newTicketCreation")))
		{
			//System.out.println("user is manager not employee--------------------------------");
			context.redirect("/index.html");
		}
		else if(((((Account)context.sessionAttribute("currentUser")).getRoleId() == 0) & context.path().equals("/api/user/employee/allreimbursementTicket")) | ((((Account)context.sessionAttribute("currentUser")).getRoleId() == 0) &context.path().equals("/api/user/employee/allreimbursementTicket/newTicketCreation")))
		{
			//System.out.println("user is manager not employee");
			context.redirect("/html/employee-page.html");
		}

	
		
	}
}
