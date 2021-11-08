package main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import main.Logging;
import main.models.reimbursement.Reimbursement;

public class ReimbursementDaoImpl implements ReimbursementDao
{
//	public static void main(String[] args)
//	{
////		Reimbursement r = new Reimbursement(15, "I had dinner at mcdonalds", 4, 2, 1);
////		System.out.println(r);
//		ReimbursementDao it = new ReimbursementDaoImpl();
////		System.out.println(it.getMyReimbursementTickets(4));
//		System.out.println(it.getAllReibursementTickets());
//		
//	}

	//INSERT
	@Override
	public boolean createNewReimbursementTicket(float reimbAmount, String reimbDescription, int reimbAuthor, int reimbStatusId, int reimbTypeId) {
		
		try(Connection conn = CustomConnectionFactory.getConnection())
		{
			String sql = "INSERT INTO ers_reimbursement "
					+ "	(reimb_amount, reimb_submitted, reimb_resolved, reimb_description, reimb_author, reimb_resolver,reimb_status_id,reimb_type_id) "
					+ "		VALUES "
					+ "			(?, current_timestamp, NULL, ?, ?, NULL, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setFloat(1, reimbAmount);
			ps.setString(2, reimbDescription);
			ps.setInt(3, reimbAuthor);
			ps.setInt(4, reimbStatusId);
			ps.setInt(5, reimbTypeId);
			ps.executeUpdate();
			Logging.info("Author id: " + reimbAuthor + " created a new ticket request");
		}
		catch(SQLException e)
		{
//			e.printStackTrace();
			Logging.error(e);
			return false;
		}
		return true;
	}
	
	
	//SELECT
	@Override
	public ArrayList<Reimbursement> getMyReimbursementTickets(int userId) 
	{
		ArrayList<Reimbursement> myReimbursementTickets = new ArrayList<Reimbursement>();
		try (Connection conn = CustomConnectionFactory.getConnection()) {

			String sql = "SELECT er.reimb_id, er.reimb_amount, er.reimb_submitted, er.reimb_resolved, er.reimb_description, "
					+ "	er.reimb_author, er.reimb_resolver, er.reimb_status_id, ers.reimb_status, er.reimb_type_id, ert.reimb_type "
					+ "		FROM ers_reimbursement er JOIN ers_reimbursement_status ers "
					+ "			ON ers.reimb_status_id = er.reimb_status_id JOIN ers_reimbursement_type ert "
					+ "				ON ert.reimb_type_id = er.reimb_type_id WHERE er.reimb_author = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			
			ResultSet rs = ps.executeQuery();
			
			
			
//			int reimbId, int reimbAmount, String reimbSubmitted, String reimbResolved,
//			String reimbDescription, int reimbAuthor, int reimbResolver, int reimbStatusId, String reimbStatus,
//			int reimbTypeId, String reimbType
			while(rs.next())
			{
				myReimbursementTickets.add( new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getString(9), rs.getInt(10), rs.getString(11)));
			}
			return myReimbursementTickets;

			
		} catch (SQLException e) {
//			e.printStackTrace();
			Logging.error(e);
			return null;
		}
		
	}	
	
	@Override
	public ArrayList<Reimbursement> getAllReibursementTickets() {
		ArrayList<Reimbursement> allReimbursementTickets = new ArrayList<Reimbursement>();
		try(Connection conn = CustomConnectionFactory.getConnection())
		{
			String sql = "SELECT * FROM all_reimb_tickets";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				/*
				 * int reimbId, int reimbAmount, String reimbSubmitted, String reimbResolved,
					String reimbDescription, int reimbAuthor, String reimbAuthorName,  
					int reimbResolver, String reimbResolverName, int reimbStatusId, String reimbStatus,
					int reimbTypeId, String reimbTyp
				 * 
				 * 
				 * 
				 */
				String reimbAuthorName = rs.getString(10) + " "  + rs.getString(11);
				String reimbResolverName = rs.getString(12) + " " + rs.getString(13);
				allReimbursementTickets.add(
						new Reimbursement(
								rs.getInt(1),  //int reimbId -- id 1
								rs.getInt(2), // int reimbAmount -- amount 2
								rs.getString(3), // String reimbSubmitted -- submitted 3
								rs.getString(4), // String reimbResolved -- resolved 4
								rs.getString(5), // String reimbDescription -- description 5
								rs.getInt(6), // int reimbAuthor - author 6
								reimbAuthorName,//  String reimbAuthorName 10 11
								rs.getInt(7), // int reimbResolver resolver 7
								reimbResolverName,// String reimbResolverName 12 13
								rs.getInt(8), // int reimbStatusId 8
								rs.getString(15),//  String reimbStatus 15
								rs.getInt(9), // int reimbTypeId 9
								rs.getString(14))); // String reimbTyp 14
			}
			return allReimbursementTickets;
		}
		catch(SQLException e)
		{
//			e.printStackTrace();
			
			Logging.error(e);
			return null;
		}
	}
	
	@Override
	public ArrayList<Reimbursement> unresolvedReimbursementTicketsOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> deniedReimbursementTicketsOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> acceptedReimbursementTicketsOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Reimbursement> openReimbursementTicketsOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	//UPDATE
	@Override
	public boolean updateReimbursementTicket(int reimbId, int reimbStatusId, int reimbResolver) {
		try(Connection conn = CustomConnectionFactory.getConnection())
		{
			String sql = "UPDATE ers_reimbursement SET reimb_resolved = current_timestamp, reimb_status_id = ?, reimb_resolver = ? "
					+ "	WHERE reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, reimbStatusId);
			ps.setInt(2, reimbResolver);
			ps.setInt(3, reimbId);
			
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
//			e.printStackTrace();
			
			Logging.error(e);
			return false;
		}
		return true;
	}

	
	
	
	
}
