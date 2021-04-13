package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

//delete one one one per user
	public String removeItemFromCart(int user_id, int product_id) {
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
			output = "Exception";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// delete all per user
	public String removeItemFromCart(int user_id) {
		String output = "error";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " delete FROM shopping_cart where user_id = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, user_id);

			// execute the statement

			int result = preparedStmt.executeUpdate();

			if (result >= 1) {
				output = "done";

			}
			con.close();

		} catch (Exception e) {
			output = "Exception";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String returnCartDetails(int user_id) {
		JsonObject cartData = new JsonObject();

		try {

			Connection con = connect();
			if (con == null) {
				return cartData.toString();
			}
			// create a prepared statement
			String query = " select * from shopping_cart where user_id = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setInt(1, user_id);
			ResultSet rs = preparedStmt.executeQuery();

			JsonArray array = new JsonArray();
			JsonObject innerItems = new JsonObject();
			while (rs.next()) {

				innerItems.addProperty("product_id", rs.getInt(2));
				innerItems.addProperty("quantity", rs.getInt(3));
				array.add(innerItems);

			}
			cartData.add("items", array);

		} catch (Exception e) {

			e.printStackTrace();
			cartData.addProperty("status", "error");
			return cartData.toString();

		}

		return cartData.toString();
	}

}
