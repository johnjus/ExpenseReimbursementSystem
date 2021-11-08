package main.services;

import java.util.ArrayList;
import java.util.HashMap;

import main.dao.ReimbursementDao;
import main.dao.ReimbursementDaoImpl;
import main.models.reimbursement.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService
{
	ReimbursementDao myReimbursementDaoLayer = new ReimbursementDaoImpl();
	
	//ArrayList<Reimbursement> myReimbursements = new ArrayList<Reimbursement>();
//	HashMap<Integer, Reimbursement> myReimbursements = new HashMap<Integer, Reimbursement>(); // remove this. DAO layer will return the data we need to put in the server
	
//	public static void main(String[] args)
//	{
//		ReimbursementService it = new ReimbursementServiceImpl();
//		
//		it.updateReimbursementTicket(7, 1, 3);
//		
//		
//	}
	
	@Override
	public boolean createNewReimbursementTicket(float reimbAmount, String reimbDescription, int reimbAuthor, 
			int reimbStatusId, int reimbTypeId) {
		if(myReimbursementDaoLayer.createNewReimbursementTicket(reimbAmount, reimbDescription, 
				reimbAuthor, reimbStatusId, reimbTypeId))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	@Override
	public boolean updateReimbursementTicket(int reimbId, int reimbStatusId, int reimbResolver) {
		if(myReimbursementDaoLayer.updateReimbursementTicket(reimbId, reimbStatusId, reimbResolver))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public HashMap<Integer, Reimbursement> getMyReimbursementTickets(int userId) 
	{
		ArrayList<Reimbursement> temp = myReimbursementDaoLayer.getMyReimbursementTickets(userId);
		HashMap<Integer, Reimbursement> myReimbursements = new HashMap<Integer, Reimbursement>();
		if(temp == null)
		{
			return null;
		}
		else
		{
			for(Reimbursement r: temp)
			{
				myReimbursements.put(r.getReimbId(), r);
			}
			
			return myReimbursements;
		}
		
	}

	@Override
	public HashMap<Integer, Reimbursement> getAllReimbursementTickets() 
	{
		ArrayList<Reimbursement> temp = myReimbursementDaoLayer.getAllReibursementTickets();
		HashMap<Integer, Reimbursement> myReimbursements = new HashMap<Integer, Reimbursement>();
		if(temp == null)
		{
			return null;
		}
		else
		{
			for(Reimbursement r: temp)
			{
				myReimbursements.put(r.getReimbId(), r);
			}
//			System.out.println(myReimbursements);
			return myReimbursements;
		}
	}
}
