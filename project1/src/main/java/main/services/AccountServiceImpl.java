package main.services;

import main.dao.AccountDao;
import main.dao.AccountDaoImpl;
import main.models.accounts.Account;

public class AccountServiceImpl implements AccountService
{
	AccountDao myAccountDaoLayer = new AccountDaoImpl();
	
	private Account myAccount;
	
	
	@Override
	public boolean attemptLoginServiceLayer(String username, String password) {
		
		if(myAccountDaoLayer.attempLoginToDatabase(username, password))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}

	@Override
	public Account getMyAccountFromDatabase(String username, String password) {
		Account myAccount = myAccountDaoLayer.getMyAccountFromDatabase(username, password);
		
		if(myAccount == null)
		{
			return null;
		}
		else
		{
//			this.myAccount = myAccount;
			
			return myAccount;
		}
	}



//	@Override
//	public Account getMyAccountFromServiceLayer() {
//		return this.myAccount;
//	}
//
//	@Override
//	public void nullMyAccountInServiceLayer() {
//		this.myAccount = null;
//		
//	}
//
//	@Override
//	public int getMyAccountId() {
//		return myAccount.getAccountId();
//	}
//
//	@Override
//	public boolean canIResolveTicket() {
//		if(myAccount.getRoleId() == 0)
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
//	}
	
	
	
	
	
	
	
	
	

}
