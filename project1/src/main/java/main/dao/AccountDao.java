package main.dao;

import main.models.accounts.Account;

public interface AccountDao 
{
	public boolean attempLoginToDatabase(String username, String password);
	
	public Account getMyAccountFromDatabase(String username, String password);
}
