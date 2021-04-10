package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AuthenticationModel {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_security_management", "paf_user",
					"^paf_user_pw_000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String[] userValidation(String usename, String password) {
		String output[] = new String[2];
		output[0] = ""; // validation status
		output[1] = ""; // key
		try {
			Connection con = connect();
			if (con == null) {
				output[0] = "Error while connecting to the database for inserting.";
				return output;
			}
			// create a prepared statement
			String query = " SELECT * FROM user_credentials where username = ? and password = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, usename);
			preparedStmt.setString(2, password);

			// execute the statement

			ResultSet rs = preparedStmt.executeQuery();
			int userID = 0;

			int result = 0;
			if (rs.next()) {
				result++;
				userID = rs.getInt(1);

			}
			if (result == 1) {
				output[0] = "valid";
				output[1] = this.addUserToCurrentyLoggedInTable(userID);
			} else {
				output[0] = "invalid";
			}
			con.close();

		} catch (Exception e) {
			output[0] = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String addUserToCurrentyLoggedInTable(int userID) {
		String key = "";
		key = getSaltString(userID);

		try {

			Connection con = connect();
			if (con == null) {
				return "null";
			}
			// create a prepared statement
			String query = " insert into logged_in_users values(? , ? , ?) ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			preparedStmt.setInt(1, userID);
			preparedStmt.setString(2, key);
			preparedStmt.setString(3, formatter.format(date));
			preparedStmt.executeUpdate();

		} catch (Exception e) {

			System.err.println(e.getMessage());
			return "null";

		}

		return key;
	}

	private String getSaltString(int userID) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) {
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		saltStr = Integer.toString(userID) + saltStr;
		return saltStr;

	}

	public String requestValidation(String key) {
		String output = "invalid";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " SELECT * FROM logged_in_users where auth_key = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, key);

			// execute the statement

			ResultSet rs = preparedStmt.executeQuery();

			int result = 0;
			if (rs.next()) {
				result++;

			}
			if (result == 1) {
				output = "valid";

			}
			con.close();

		} catch (Exception e) {
			output = "Error while prosessing the request.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String logOut(String key) {
		String output = "error";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = " delete FROM logged_in_users where auth_key = ? ";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, key);

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
