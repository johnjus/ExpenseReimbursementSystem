package project1.service;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.models.reimbursement.Reimbursement;
import main.services.ReimbursementService;
import main.services.ReimbursementServiceImpl;



public class ReimbursementServiceImpTest 
{
	ReimbursementService reimbursementServ = null;
	
	
	/**
	 * Runs before each test to reset the reimbursement service object 
	 * 
	 * 
	 * @throws Exception
	 */
	@BeforeEach
	void IntializeReimbursementServ() throws Exception {
		System.out.println("--------------------before each--------------------");
		reimbursementServ = new ReimbursementServiceImpl();
	}
	
	/**
	 * Test to see that if the function is given a valid user id it will return their tickets and not a null object
	 * 
	 * ONLY tested on accounts that have reimbursement tickets. Otherwise, it would be null as there are no tickets
	 * associated to that user id
	 * 
	 */
	@Test
	void checkUserTicketsAreReal() {
		HashMap<Integer,Reimbursement> storeReimbursementTest = reimbursementServ.getMyReimbursementTickets(1);
		assertNotNull(storeReimbursementTest);
	}


	
	
	
	
}
