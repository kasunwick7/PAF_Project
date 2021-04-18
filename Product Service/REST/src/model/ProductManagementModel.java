package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ProductManagementModel {
	
	private Connection connect(){
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "1234");
		}
		catch (Exception e)
		{
			e.printStackTrace();}
		return con;
	}
	
	public boolean addProducts (int products_id, int research_id, String name, String description, int stock_quantity, double price, String added_date) {

		try {

			Connection con = connect();
			
			if (con == null) {
				return false;
			}
			
			// create a prepared statement
			String query = " insert into products values(? , ? , ? , ? , ? , ? , ?) ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, products_id);
			preparedStmt.setInt(2, research_id);
			preparedStmt.setString(3, name);
			preparedStmt.setString(4, description);
			preparedStmt.setInt(5, stock_quantity);
			preparedStmt.setDouble(6, price);
			preparedStmt.setString(7, added_date);
			preparedStmt.executeUpdate();

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return false;

		}

		return true;
	}
	
	
	
	

}
