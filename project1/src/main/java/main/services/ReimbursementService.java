package main.services;

import java.util.HashMap;

import main.models.reimbursement.Reimbursement;

public interface ReimbursementService 
{
	
	//create new reimbursement ticket for employee
	public boolean createNewReimbursementTicket(float reimbAmount, String reimbDescription, int reimbAuthor, int reimbStatusId, int reimbTypeId);
	
	//update reimbursement tickets by manager
	public boolean updateReimbursementTicket(int reimbId, int reimbStatusId, int reimbResolver);
	
	
	//get reimbursement tickets for the employee
	public HashMap<Integer, Reimbursement> getMyReimbursementTickets(int userId);
	
	
	//get reimbursement tickets for the manager
	public HashMap<Integer, Reimbursement> getAllReimbursementTickets();
		
}
