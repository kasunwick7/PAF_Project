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
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_project", "root", "Sasitha@2020"); 
		} 
		catch (Exception e) 
		{e.printStackTrace();} 
		return con; 
	} 
	
	
	public String insertItem(String funderName, String amount, String fundingDate,String fundStatus) 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			// create a prepared statement
			String query = " insert into funds (`fundID`,`funderName`,`amount`,`fundingDate`,`fundStatus`)"
					+ " values (?, ?, ? ,? ,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, funderName); 
			preparedStmt.setDouble(3, Double.parseDouble(amount)); 
			preparedStmt.setString(4, fundingDate);
			preparedStmt.setString(5, fundStatus); 
			// execute the statement3
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} 
		catch (Exception e) 
		{ 
			output = "Error while inserting the fund details."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	
	public String readItems() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for reading."; } 
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Item Code</th><th>Item Name</th>" +
					"<th>Item Price</th>" + 
					"<th>Item Description</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
 
			String query = "select * from funds"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
			// iterate through the rows in the result set
			while (rs.next()) 
			{ 
				String fundID = Integer.toString(rs.getInt("fundID")); 
				String funderName = rs.getString("funderName"); 
				String amount = Double.toString(rs.getDouble("amount")); 
				String fundingDate = rs.getString("fundingDate"); 
				String fundStatus = rs.getString("fundStatus"); 
				// Add into the html table
				output += "<tr><td>" + fundID + "</td>"; 
				output += "<td>" + funderName + "</td>"; 
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + fundingDate + "</td>"; 
				output += "<td>" + fundStatus + "</td>"; 
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"
						+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + fundID 
						+ "'>" + "</form></td></tr>"; 
			} 
			con.close(); 
			// Complete the html table
			output += "</table>"; 
			
			
		} 
		catch (Exception e) 
		{ 
			output = "Error while reading the fund details."; 
			System.err.println(e.getMessage()); 
			
		} 
		return output;
		 
	} 
	public String updateItem(String fundID, String funderName, String amount, String fundingDate, String fundStatus)
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for updating."; } 
			// create a prepared statement
			String query = "UPDATE funds SET funderName=?,amount=?,fundingDate=?,fundStatus=? WHERE fundID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			// binding values
			preparedStmt.setString(1, funderName); 
			preparedStmt.setDouble(2,Double.parseDouble(amount)); 
			preparedStmt.setString(3,fundingDate); 
			preparedStmt.setString(4, fundStatus); 
			preparedStmt.setInt(5, Integer.parseInt(fundID)); 
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
	
	
	public String deleteItem(String fundID) 
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
