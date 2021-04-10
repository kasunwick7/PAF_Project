package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PaymentManagementModel {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_payment_management", "paf_user",
					"^paf_user_pw_000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public boolean addItemToCart(int user_id, int product_id, int quantity) {

		try {

			Connection con = connect();
			if (con == null) {
				return false;
			}
			// create a prepared statement
			String query = " insert into shopping_cart values(? , ? , ?) ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, user_id);
			preparedStmt.setInt(2, product_id);
			preparedStmt.setInt(3, quantity);
			preparedStmt.executeUpdate();

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return false;

		}

		return true;
	}
	
	public String removeItemFromCart(int user_id , int product_id) {
		String output = "error";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " delete FROM shopping_cart where user_id = ? and product_id = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, user_id);
			preparedStmt.setInt(2, product_id);
			// execute the statement

			int result = preparedStmt.executeUpdate();

			if (result >= 1) {
				output = "done";

			}
			con.close();

		} catch (Exception e) {
			output = "Error while prosessing the request.";
			System.err.println(e.getMessage());
		}
		return output;
	}


}
