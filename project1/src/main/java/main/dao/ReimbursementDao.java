package main.dao;

import java.util.ArrayList;

import main.models.reimbursement.Reimbursement;

public interface ReimbursementDao 
{
	
	//for employee user use
	public ArrayList<Reimbursement> getMyReimbursementTickets(int userId);
	
	//for anyones use
	public boolean createNewReimbursementTicket(float reimbAmount, String reimbDescription, int reimbAuthor, int reimbStatusId, int reimbTypeId);
	
	
	//manager user
	public ArrayList<Reimbursement> getAllReibursementTickets();
	
	//UPDATE
	public boolean updateReimbursementTicket(int reimbId, int reimbStatusId, int reimbResolver);
	
	public ArrayList<Reimbursement> unresolvedReimbursementTicketsOrder();
	
	public ArrayList<Reimbursement> deniedReimbursementTicketsOrder();
	
	public ArrayList<Reimbursement> acceptedReimbursementTicketsOrder();
	
	public ArrayList<Reimbursement> openReimbursementTicketsOrder();
	

}
