package main.controller;

import io.javalin.http.Context;
import main.models.accounts.Account;
import main.services.AccountService;
import main.services.AccountServiceImpl;
import main.Logging;

public class UserAccountController 
{
	private static AccountService myAccountServ = new AccountServiceImpl();
	
	/**
	 * 
	 * When called this method will invoke a method in the service layer that inovkes a method in the dao layer
	 * to attempt to verify if the credentials are in the database
	 * 
	 * @param context
	 */
	public static void userLogin(Context context)
	{
		try
		{
			
		
			String username = context.formParam("myUsername");
			String password = context.formParam("myPassword");
			
			if(myAccountServ.attemptLoginServiceLayer(username, password)) //if true then get user account from DB
			{
	//			System.out.println(myAccountServ.getMyAccountFromDatabase(username, password));
				context.sessionAttribute("currentUser", myAccountServ.getMyAccountFromDatabase(username, password)); //set session to user account info
	//			System.out.println(((Account)context.sessionAttribute("currentUser")));
	//			System.out.println(((Account)context.sessionAttribute("currentUser")).getRoleId() == 1);
				if(((Account)context.sessionAttribute("currentUser")).getRoleId() == 1)//check user role id if they employeee
				{
					//take me to the employee page
					context.redirect("/html/employee-page.html");
				}
				else if(((Account)context.sessionAttribute("currentUser")).getRoleId() == 0) // or if they manager
				{
					context.redirect("/html/manager-page.html");
				}
				
			}
			else//if we failed login attempt... send us back to login page aka "/index.html"
			{
				System.out.println("not correct credentials");
				context.redirect("/index.html");
			}
		}
		catch(Exception e)
		{
			Logging.error(e);
		}
		
//		System.out.println("username: " + username);
//		System.out.println("pssword: " + password);
	}
	
	/**
	 * 
	 * This method logs sets the currentUser session attribute to null, because the user has logout of the webpage
	 * 
	 * 
	 * 
	 * 
	 * @param context
	 */
	public static void logout(Context context) 
	{	
		try
		{
			
			context.sessionAttribute("currentUser", null);
			context.redirect("/index.html");
			//context.json(new MyCustomResponseMessage("You've successfully logged out", "success"));
		}
		catch(Exception e)
		{
			Logging.error(e);
		}
	}
	
	/**
	 * This method is used to retrieve the data from the session attribute currentUser and send it off to the webpage
	 * to be used there
	 * 
	 * 
	 * 
	 * @param context
	 */
	public static void userData(Context context)
	{
		try {
			context.json(((Account)context.sessionAttribute("currentUser")));
		}
		catch(Exception e)
		{
			Logging.error(e);
			context.redirect("/index.html");
		}
	}
	
	
	
	
	
}
