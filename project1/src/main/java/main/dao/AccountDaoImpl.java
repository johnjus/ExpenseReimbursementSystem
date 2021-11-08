package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Logging;
import main.models.accounts.Account;

public class AccountDaoImpl implements AccountDao
{
		
	/**
	 * Checks to see if the username and password entered are the correct combination in the database
	 * This method requests to get the data from the database associated to the username and password
	 * 
	 *if the return result set is empty and does not have next() then return false
	 *Otherwise, it will return true... Meaning that combination is in the database
	 * 
	 * @author justin Johnson
	 */
	@Override
	public boolean attempLoginToDatabase(String username, String password) {

		try (Connection conn = CustomConnectionFactory.getConnection()) {

			String sql = "SELECT eu.ers_user_id FROM ers_users eu "
					+ "WHERE eu.ers_username = ? AND eu.ers_password = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				Logging.info(username + ": succsefully logged into the database");
				return true;
			}
			else
			{
				Logging.info("Username: " + username + ", Password: " + password + " tried to log into the system and failed");
				return false;
				
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			Logging.error(e);
			return false;
		}		
	}
	/**
	 * This method attempts to get all the details from the database associated with the username and password
	 * 
	 *If it is approved it will return an Account object with all the details associated to it  
	 * 
	 * if it is not approved it will return null...
	 * 
	 * @author justin johnson
	 */
	@Override
	public Account getMyAccountFromDatabase(String username, String password) {
		Account myAccount = null;
		try(Connection conn = CustomConnectionFactory.getConnection())
		{
			String sql = "SELECT eu.ers_user_id, eu.ers_username, eu.ers_password, eu.user_first_name, eu.user_last_name, eu.user_email, eu.user_role_id, eur.user_role "
					+ "	FROM ers_users eu JOIN ers_user_roles eur ON eu.user_role_id = eur.ers_user_role_id "
					+ "		WHERE eu.ers_username = ? AND eu.ers_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
//int accountId, String username, String password, String firstName, String lastName, String email,int roleId, String roleName
				myAccount = new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
				Logging.info(username + ": succsefully grabbed their account data from the database");
				return myAccount;
			}
			else
			{
				Logging.info(username + ": failed to grabbed their account data from the database");
				return null;
			}
		}
		catch(SQLException e)
		{
//			e.printStackTrace();
			Logging.error(e);
			return null;
		}
		
	}

}
