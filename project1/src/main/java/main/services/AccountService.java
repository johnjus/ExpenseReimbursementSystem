package main.services;

import main.models.accounts.Account;

public interface AccountService 
{
	
	public boolean attemptLoginServiceLayer(String username, String password);
	
	public Account getMyAccountFromDatabase(String username, String password);
	
//	public Account getMyAccountFromServiceLayer();
	
//	public void nullMyAccountInServiceLayer();
	
//	public int getMyAccountId();
	
	
//	public boolean canIResolveTicket();
}
