package main.controller;

import io.javalin.http.Context;
import main.models.accounts.Account;
import main.models.reimbursement.Reimbursement;
import main.services.ReimbursementService;
import main.services.ReimbursementServiceImpl;
import main.Logging;


public class ReimbursementController 
{
	private static ReimbursementService myReimbursementServ = new ReimbursementServiceImpl();
	
	
	/**
	 * This method is used to get all the reimbursement tickets from the database and send them off to the webpage
	 * 
	 * @param context
	 */
	public static void getAllReimbursementTickets(Context context) //send out all reimbursement tickets
	{
//		System.out.println(myReimbursementServ.getAllReimbursementTickets());
		try
		{
			context.json(myReimbursementServ.getAllReimbursementTickets());
		}
		catch(Exception e)
		{
			Logging.error(e);
		}
	}
	
	/**
	 * This method is used to get the specific reimbursement tickets to a user and send them off to the webpage
	 * 
	 * 
	 * @param context
	 */
	public static void getUserReimbursementTickets(Context context) //send out the users reimbursement tickets to the webpage
	{
		try
		{
		int reimbAuthor = ((Account)context.sessionAttribute("currentUser")).getAccountId();
				
		context.json(myReimbursementServ.getMyReimbursementTickets(reimbAuthor));
		}
		catch(Exception e)
		{
			Logging.error(e);
		}
	}
	
	/**
	 * This method is used to receive data from the webpage and then call the service layer -> dao layer to create
	 * a new ticket in the database that is linked to the user who created it
	 * 
	 * @param context
	 */
	public static void createReimbursmentTicket(Context context) //employee create new reimbursement ticket 
	{	
		try
		{
			
			Reimbursement myNewTicket = context.bodyAsClass(Reimbursement.class);
	//		System.out.println(context.body()+ "I am the body of the sent request");
			
	//		System.out.println(myNewTicket);
			
	//		System.out.println("hello there, I have worked");
			if(myReimbursementServ.createNewReimbursementTicket(myNewTicket.getReimbAmount(), //amount
					myNewTicket.getReimbDescription(), //description
					((Account)context.sessionAttribute("currentUser")).getAccountId(), //author id
					2, //status id
					myNewTicket.getReimbTypeId() //
					))
			{
				System.out.println("Created new ticket");
			}
		}
		catch(Exception e)
		{
			Logging.error(e);
		}
	}
	
	
	/**
	 * This method is used to update a ticket. It will recieve data from the page that will contain new information that
	 * is needed to be added to the database at a certain ticket.
	 * 
	 * 
	 * 
	 * @param context
	 */
	public static void updateReimbursementTicket(Context context) //manager updates a reimburesement ticket from the webpage
	{
		try
		{
			Reimbursement myUpdatedTicket = context.bodyAsClass(Reimbursement.class);
			
			System.out.println(myUpdatedTicket);
			
			if(myReimbursementServ.updateReimbursementTicket(myUpdatedTicket.getReimbId(),
					myUpdatedTicket.getReimbStatusId(),
					((Account)context.sessionAttribute("currentUser")).getAccountId()))
			{
				//refresh page so the get the newly modified reimbursment ticket
				System.out.println("updated the reimbursement ticket ");
			}
		}
		catch(Exception e)
		{
			Logging.error(e);
		}
	}
}
