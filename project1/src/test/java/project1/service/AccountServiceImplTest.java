package project1.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.models.accounts.Account;
import main.services.AccountService;
import main.services.AccountServiceImpl;


public class AccountServiceImplTest 
{
	AccountService accountServ= null;
	
	
	/**
	 * runs before each test to reset the account service object
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void setUpAndIntializeAccountServ() throws Exception {
		System.out.println("--------------------before each--------------------");
		accountServ = new AccountServiceImpl();
	}
	
	/**
	 * Test if the login attempt works properly
	 * 
	 * @throws Exception
	 */
	@Test
	void credentialsCorrect() throws Exception {
		boolean test = accountServ.attemptLoginServiceLayer("bubbles", "hello");
		assertTrue(test);
	}
	
	
	/**
	 * Test to see that it should return false if someone without credentials in the database attempts to login
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	void tryingFaultyCredentials() throws Exception {
		boolean test = accountServ.attemptLoginServiceLayer("mannyMachato", "BigM@nOnCampus");
		assertFalse(test);
	}
	
	
	/**
	 * Test to make sure that when a user logins in with correct credentials that the account object is not null
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	void checkingReturnedDataNotNull() throws Exception {
		Account test = accountServ.getMyAccountFromDatabase("bubbles", "hello");
		assertNotNull(test);
	}


	
	
	
	
	
	
}
