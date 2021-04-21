package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Funds 
{ //A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
 
			//Provide the correct details: DBServer/DBName, username, password 
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_fund_management", "paf_user", "^paf_user_pw_000"); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	
	public boolean addFundPayment(int researchID, String funderName, String amount, String fundingDate,String fundStatus) 
	{ 
//		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return false; } 
			// create a prepared statement
			String query = " insert into funds (`fundID`,`researchID`,`funderName`,`amount`,`fundingDate`,`fundStatus`)"
					+ " values (?, ?, ?, ?, ?, ?)"; 
//			(`researchID`,`funderName`,`amount`,`fundingDate`,`fundStatus`)
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setInt(2, researchID); 
			preparedStmt.setString(3, funderName); 
			preparedStmt.setDouble(4, Double.parseDouble(amount)); 
			preparedStmt.setString(5, fundingDate);
			preparedStmt.setString(6, fundStatus); 
			// execute the statement3
			preparedStmt.execute(); 
			con.close(); 
//			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
//			output = "Error while inserting the fund details."; 
			System.err.println(e.getMessage()); 
			return false;
		} 
		return true; 
	} 
	
	
	public String readFundPayment() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			
			output = "<table border='1'><tr><th>fund ID</th><th>Research ID</th>" +
					"<th>funder name</th>" + 
					"<th>Amount</th>" +
					"<th>funing date</th>" +
					"<th>fund status</th>" +
					"</tr>"; 
 
			String query = "select * from funds"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String fundID = Integer.toString(rs.getInt("fundID")); 
				String researchID = Integer.toString(rs.getInt("researchID")); 
				String funderName = rs.getString("funderName"); 
				String amount = Double.toString(rs.getDouble("amount")); 
				String fundingDate = rs.getString("fundingDate"); 
				String fundStatus = rs.getString("fundStatus"); 
				
				output += "<tr><td>" + fundID + "</td>"; 
				output += "<tr><td>" + researchID + "</td>"; 
				output += "<td>" + funderName + "</td>"; 
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + fundingDate + "</td>"; 
				output += "<td>" + fundStatus + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='fundID' type='hidden' value='" + fundID 
						+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
		
			output += "</table>"; 
			
			
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the fund details."; 
			System.err.println(e.getMessage()); 
			
		} 
		return output;
		 
	} 
	public String updateFundPayment(String fundID, int researchID, String funderName, String amount, String fundingDate, String fundStatus)
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE funds SET researchID=?,funderName=?,amount=?,fundingDate=?,fundStatus=? WHERE fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, researchID);
			preparedStmt.setString(2, funderName); 
			preparedStmt.setDouble(3,Double.parseDouble(amount)); 
			preparedStmt.setString(4,fundingDate); 
			preparedStmt.setString(5, fundStatus); 
			preparedStmt.setInt(6, Integer.parseInt(fundID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while updating the fund details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	
	public String deleteFundPayment(String fundID) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for deleting."; } 
			// create a prepared statement
			String query = "delete from funds where fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundID)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while deleting the fund details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
} 
